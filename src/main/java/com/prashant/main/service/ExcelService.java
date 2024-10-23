package com.prashant.main.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prashant.main.model.Employee;

@Service
public class ExcelService {
	public List<Employee> readExcelData(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row

            Employee employee = new Employee();
            employee.setEmployeeId((int) getNumericCellValue(row.getCell(0)));
            employee.setName(getStringCellValue(row.getCell(1)));
            employee.setBank(getStringCellValue(row.getCell(2)));
            employee.setBankAccountNo((long) getNumericCellValue(row.getCell(3)));
            employee.setDoj(row.getCell(4).getDateCellValue()); // Assuming cell contains a date
            employee.setLopDays((int) getNumericCellValue(row.getCell(5)));
            employee.setPan(getStringCellValue(row.getCell(6)));
            employee.setStdDays((int) getNumericCellValue(row.getCell(7)));
            employee.setLocation(getStringCellValue(row.getCell(8)));
            employee.setWorkedDays((int) getNumericCellValue(row.getCell(9)));
            employee.setDepartment(getStringCellValue(row.getCell(10)));
            employee.setEntity(getStringCellValue(row.getCell(11)));
            employee.setPfUan((long) getNumericCellValue(row.getCell(12)));
            employee.setBasic(getNumericCellValue(row.getCell(13)));
            employee.setHouseRentAllowance(getNumericCellValue(row.getCell(14)));
            employee.setSpecialAllowance(getNumericCellValue(row.getCell(15)));
            employee.setOnCallAllowance(getNumericCellValue(row.getCell(16)));
            employee.setVariablePay(getNumericCellValue(row.getCell(17)));
            employee.setProvidentFund(getNumericCellValue(row.getCell(18)));
            employee.setProfessionalTax(getNumericCellValue(row.getCell(19)));
            employee.setEsi(getNumericCellValue(row.getCell(20)));
            employee.setIncomeTax(getNumericCellValue(row.getCell(21)));
            employee.setGrossEarnings(getNumericCellValue(row.getCell(22)));
            employee.setGrossDeductions(getNumericCellValue(row.getCell(23)));
            employee.setNetPay(getNumericCellValue(row.getCell(24)));

            employees.add(employee); // Add employee to list
        }

        workbook.close();
        System.out.println("Total employees found: " + employees.size());
        return employees;
    }

    // Helper method for getting string values from cells
    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Helper method for getting numeric values from cells
    private double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.00;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return Double.parseDouble(cell.getStringCellValue());
            default:
                return 0.00;
        }
    }
}