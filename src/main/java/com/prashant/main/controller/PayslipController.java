package com.prashant.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prashant.main.model.Employee;
import com.prashant.main.service.ExcelService;
import com.prashant.main.service.PdfService;

@RestController
@RequestMapping("/api/payslip")
public class PayslipController {
	@Autowired
	private final ExcelService excelService;
	@Autowired
    private final PdfService pdfService;
	@Autowired
    private final ResourceLoader resourceLoader;

    public PayslipController(ExcelService excelService, PdfService pdfService, ResourceLoader resourceLoader) {
        this.excelService = excelService;
        this.pdfService = pdfService;
        this.resourceLoader = resourceLoader; // Inject ResourceLoader
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Integer>> uploadFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("month") String month,
                                                    @RequestParam("year") String year) throws Exception {
        try {
            List<Employee> employees = excelService.readExcelData(file); // Read employees from Excel
            List<Integer> generatedEmployeeIds = new ArrayList<>();

            // Check if the employee list is empty
            if (employees.isEmpty()) {
                System.out.println("No employees found in the uploaded Excel file.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
            }

            // Generate payslip for each employee
            for (Employee employee : employees) {
                System.out.println("Generating payslip for Employee ID: " + employee.getEmployeeId());
                pdfService.generatePayslip(employee, month, year); // Pass month and year to PdfService
                generatedEmployeeIds.add(employee.getEmployeeId()); // Add to list of generated IDs
            }

            if (generatedEmployeeIds.isEmpty()) {
                System.out.println("No payslips generated.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
            }

            return ResponseEntity.ok(generatedEmployeeIds); // Return generated employee IDs
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @GetMapping("/download/{employeeId}")
    public ResponseEntity<Resource> downloadPayslip(@PathVariable int employeeId) {
        try {
            String filePath = "payslips/payslip_" + employeeId + ".pdf";
            Resource resource = resourceLoader.getResource("file:" + filePath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
