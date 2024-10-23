package com.prashant.main.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prashant.main.model.Employee;

@Service
public class PdfService {
	private static final String DIRECTORY = "payslips/";

	// Inside your PdfService class
	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); // Format for two decimal places

	public void generatePayslip(Employee employee, String month, String year) throws Exception {
		// Create the directory if it doesn't exist
		new File(DIRECTORY).mkdirs();

		// Create the PDF document
		Document document = new Document();
		String filePath = DIRECTORY + "payslip_" + employee.getEmployeeId() + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(filePath));
		document.open();

		// Load the logo from the classpath (assuming the logo is in src/main/resources)
		ClassPathResource resource = new ClassPathResource("logo.png"); // Ensure logo.png is in src/main/resources
		InputStream logoStream = resource.getInputStream();
		Image logo = Image.getInstance(logoStream.readAllBytes());
		logo.scaleToFit(100, 200); // Adjust the size of the logo
		logo.setAlignment(Image.ALIGN_LEFT); // Align the logo to the top-right

		// Add the logo to the document (before the table)
		document.add(logo);

		// Set font size to 10 for all text
		Font smallFont = new Font(Font.FontFamily.HELVETICA, 9.5f, Font.NORMAL);
		Font smallBoldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

		// Employee Details Table
		PdfPTable employeeDetailsTable = new PdfPTable(4); // 4 columns for employee details
		employeeDetailsTable.setWidthPercentage(100);
		employeeDetailsTable.setSpacingBefore(10f);
		employeeDetailsTable.setSpacingAfter(10f);

		// First row: "RJAY Technologies Pvt Ltd" with light blue background, white
		// text, and centered
		PdfPCell headerCell = new PdfPCell(new Paragraph("RJAY Technologies Pvt Ltd",
				new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.WHITE)));
		headerCell.setColspan(4); // Merge all 4 columns
		headerCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align to left
		headerCell.setBackgroundColor(new BaseColor(145, 88, 178)); // Light blue background
		headerCell.setPadding(5f); // Padding for the cell
		employeeDetailsTable.addCell(headerCell);

		// Second row: "Payslip For Month Year"
		PdfPCell payslipCell = new PdfPCell(new Paragraph("Payslip For " + month + " " + year, smallFont));
		payslipCell.setColspan(4); // Merge all 4 columns
		payslipCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Align to center
		payslipCell.setPadding(5f); // Padding for the cell
		employeeDetailsTable.addCell(payslipCell);

		// Add Employee Details
		PdfPCell employeeIdCell = new PdfPCell(new Paragraph("Employee ID", smallFont));
		employeeIdCell.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		employeeIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(employeeIdCell);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(String.valueOf(employee.getEmployeeId()), smallFont)));

		PdfPCell nameCell = new PdfPCell(new Paragraph("Name", smallFont));
		nameCell.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(nameCell);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(employee.getName(), smallFont)));

		PdfPCell bank = new PdfPCell(new Paragraph("Bank", smallFont));
		bank.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		bank.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(bank);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(employee.getBank(), smallFont)));

		PdfPCell acno = new PdfPCell(new Paragraph("A/C No", smallFont));
		acno.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		acno.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(acno);
		employeeDetailsTable
				.addCell(new PdfPCell(new Paragraph(String.valueOf(employee.getBankAccountNo()), smallFont)));

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");

		PdfPCell doj = new PdfPCell(new Paragraph("DOJ", smallFont));
		doj.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		doj.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(doj);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(dateFormatter.format(employee.getDoj()), smallFont)));

		PdfPCell lop = new PdfPCell(new Paragraph("LOP Days", smallFont));
		lop.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		lop.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(lop);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(String.valueOf(employee.getLopDays()), smallFont)));

		PdfPCell pan = new PdfPCell(new Paragraph("PAN", smallFont));
		pan.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		pan.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(pan);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(employee.getPan(), smallFont)));

		PdfPCell std = new PdfPCell(new Paragraph("STD Days", smallFont));
		std.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		std.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(std);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(String.valueOf(employee.getStdDays()), smallFont)));

		PdfPCell location = new PdfPCell(new Paragraph("Location", smallFont));
		location.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		location.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(location);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(employee.getLocation(), smallFont)));

		PdfPCell wday = new PdfPCell(new Paragraph("Worked Days", smallFont));
		wday.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		wday.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(wday);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(String.valueOf(employee.getWorkedDays()), smallFont)));

		PdfPCell dept = new PdfPCell(new Paragraph("Department", smallFont));
		dept.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		dept.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(dept);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(employee.getDepartment(), smallFont)));

		PdfPCell entity = new PdfPCell(new Paragraph("Entity", smallFont));
		entity.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		entity.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(entity);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(employee.getEntity(), smallFont)));

		PdfPCell pf = new PdfPCell(new Paragraph("PF - UAN", smallFont));
		pf.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		pf.setVerticalAlignment(Element.ALIGN_MIDDLE);
		employeeDetailsTable.addCell(pf);
		employeeDetailsTable.addCell(new PdfPCell(new Paragraph(String.valueOf(employee.getPfUan()), smallFont)));

		PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
		emptyCell.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		employeeDetailsTable.addCell(emptyCell);

		employeeDetailsTable.addCell(""); // Empty cell

		// Add the Employee Details Table to the document
		document.add(employeeDetailsTable);

		// Earnings and Deductions Table
		PdfPTable earningsTable = new PdfPTable(4); // 4 columns for earnings and deductions
		earningsTable.setWidthPercentage(100);
		earningsTable.setSpacingBefore(10f);

		// Add headers for earnings and deductions
		PdfPCell earningsHeader = new PdfPCell(new Paragraph("Earnings", smallFont));
		earningsHeader.setHorizontalAlignment(Element.ALIGN_CENTER); // Center the text
		earningsHeader.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		earningsHeader.setPadding(5f); // Optional: Add padding for better look
		earningsTable.addCell(earningsHeader);

		PdfPCell earningsAmountHeader = new PdfPCell(new Paragraph("Amount in Rs.", smallFont));
		earningsAmountHeader.setHorizontalAlignment(Element.ALIGN_CENTER); // Center the text
		earningsAmountHeader.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		earningsAmountHeader.setPadding(5f); // Optional: Add padding
		earningsTable.addCell(earningsAmountHeader);

		PdfPCell deductionsHeader = new PdfPCell(new Paragraph("Deductions", smallFont));
		deductionsHeader.setHorizontalAlignment(Element.ALIGN_CENTER); // Center the text
		deductionsHeader.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		deductionsHeader.setPadding(5f); // Optional: Add padding
		earningsTable.addCell(deductionsHeader);

		PdfPCell deductionsAmountHeader = new PdfPCell(new Paragraph("Amount in Rs.", smallFont));
		deductionsAmountHeader.setHorizontalAlignment(Element.ALIGN_CENTER); // Center the text
		deductionsAmountHeader.setBackgroundColor(new BaseColor(243, 243, 243)); // Set light gray background
		deductionsAmountHeader.setPadding(5f); // Optional: Add padding
		earningsTable.addCell(deductionsAmountHeader);

		// Add earnings and deductions data
		PdfPCell basicLabelCell = new PdfPCell(new Paragraph("BASIC", smallFont));
		basicLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		basicLabelCell.setBorderWidthRight(0);
		basicLabelCell.setBorderWidthBottom(0);
		earningsTable.addCell(basicLabelCell);

		PdfPCell basic = new PdfPCell(new Paragraph(decimalFormat.format(employee.getBasic()), smallFont));
		basic.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		basic.setBorderWidthBottom(0);
		basic.setBorderWidthLeft(0);
		earningsTable.addCell(basic);

		PdfPCell pf1 = new PdfPCell(new Paragraph("PROVIDENT FUND", smallFont));
		pf1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		pf1.setBorderWidthLeft(0);
		pf1.setBorderWidthBottom(0);
		pf1.setBorderWidthRight(0);
		earningsTable.addCell(pf1);

		PdfPCell providentFundCell = new PdfPCell(
				new Paragraph(decimalFormat.format(employee.getProvidentFund()), smallFont)); // Format Provident Fund
		providentFundCell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		providentFundCell.setBorderWidthBottom(0);
		providentFundCell.setBorderWidthLeft(0);
		earningsTable.addCell(providentFundCell);

		PdfPCell hra1 = new PdfPCell(new Paragraph("HOUSE RENT ALLOWANCE", smallFont));
		hra1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		hra1.setBorderWidthRight(0);
		hra1.setBorderWidthBottom(0);
		hra1.setBorderWidthTop(0);
		earningsTable.addCell(hra1);

		PdfPCell hra = new PdfPCell(new Paragraph(decimalFormat.format(employee.getHouseRentAllowance()), smallFont));
		hra.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		hra.setVerticalAlignment(Element.ALIGN_MIDDLE);
		hra.setNoWrap(true);
		hra.setBorderWidthBottom(0);
		hra.setBorderWidthLeft(0);
		hra.setBorderWidthTop(0);
		earningsTable.addCell(hra);

		PdfPCell pt1 = new PdfPCell(new Paragraph("PROFESSIONAL TAX", smallFont));
		pt1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		pt1.setBorderWidthLeft(0);
		pt1.setBorderWidthBottom(0);
		pt1.setBorderWidthRight(0);
		pt1.setBorderWidthTop(0);
		earningsTable.addCell(pt1);

		PdfPCell pt = new PdfPCell(new Paragraph(decimalFormat.format(employee.getProfessionalTax()), smallFont));
		pt.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		pt.setBorderWidthBottom(0);
		pt.setBorderWidthLeft(0);
		pt.setBorderWidthTop(0);
		earningsTable.addCell(pt);

		PdfPCell sa1 = new PdfPCell(new Paragraph("SPECIAL ALLOWANCE", smallFont));
		sa1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		sa1.setBorderWidthRight(0);
		sa1.setBorderWidthBottom(0);
		sa1.setBorderWidthTop(0);
		earningsTable.addCell(sa1);

		PdfPCell sa = new PdfPCell(new Paragraph(decimalFormat.format(employee.getSpecialAllowance()), smallFont));
		sa.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		sa.setBorderWidthBottom(0);
		sa.setBorderWidthLeft(0);
		sa.setBorderWidthTop(0);
		earningsTable.addCell(sa);

		PdfPCell esi1 = new PdfPCell(new Paragraph("ESI", smallFont));
		esi1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		esi1.setBorderWidthLeft(0);
		esi1.setBorderWidthBottom(0);
		esi1.setBorderWidthRight(0);
		esi1.setBorderWidthTop(0);
		earningsTable.addCell(esi1);

		PdfPCell esi = new PdfPCell(new Paragraph(decimalFormat.format(employee.getEsi()), smallFont));
		esi.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		esi.setBorderWidthBottom(0);
		esi.setBorderWidthLeft(0);
		esi.setBorderWidthTop(0);
		earningsTable.addCell(esi);

		PdfPCell oca1 = new PdfPCell(new Paragraph("ON CALL ALLOWANCE", smallFont));
		oca1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		oca1.setBorderWidthRight(0);
		oca1.setBorderWidthBottom(0);
		oca1.setBorderWidthTop(0);
		earningsTable.addCell(oca1);

		PdfPCell oca = new PdfPCell(new Paragraph(decimalFormat.format(employee.getOnCallAllowance()), smallFont));
		oca.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		oca.setBorderWidthBottom(0);
		oca.setBorderWidthLeft(0);
		oca.setBorderWidthTop(0);
		earningsTable.addCell(oca);

		PdfPCell it1 = new PdfPCell(new Paragraph("INCOME TAX", smallFont));
		it1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		it1.setBorderWidthLeft(0);
		it1.setBorderWidthBottom(0);
		it1.setBorderWidthRight(0);
		it1.setBorderWidthTop(0);
		earningsTable.addCell(it1);

		PdfPCell it = new PdfPCell(new Paragraph(decimalFormat.format(employee.getIncomeTax()), smallFont));
		it.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		it.setBorderWidthBottom(0);
		it.setBorderWidthLeft(0);
		it.setBorderWidthTop(0);
		earningsTable.addCell(it);

		PdfPCell vp1 = new PdfPCell(new Paragraph("VARIABLE PAY", smallFont));
		vp1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		vp1.setBorderWidthRight(0);
		vp1.setBorderWidthBottom(0);
		vp1.setBorderWidthTop(0);
		earningsTable.addCell(vp1);

		PdfPCell vp = new PdfPCell(new Paragraph(decimalFormat.format(employee.getVariablePay()), smallFont));
		vp.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		vp.setBorderWidthBottom(0);
		vp.setBorderWidthLeft(0);
		vp.setBorderWidthTop(0);
		earningsTable.addCell(vp);

		PdfPCell vp2 = new PdfPCell(new Paragraph(""));
		vp2.setBorderWidthLeft(0);
		vp2.setBorderWidthBottom(0);
		vp2.setBorderWidthRight(0);
		vp2.setBorderWidthTop(0);
		earningsTable.addCell(vp2);// Empty cell
		
		PdfPCell vp3 = new PdfPCell(new Paragraph(""));
		vp3.setBorderWidthBottom(0);
		vp3.setBorderWidthLeft(0);
		vp3.setBorderWidthTop(0);
		earningsTable.addCell(vp3);// Empty cell

		// Add Gross Earnings and Deductions
		PdfPCell ge1 = new PdfPCell(new Paragraph("GROSS EARNINGS", smallFont));
		ge1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		ge1.setBorderWidthRight(0);
		ge1.setBorderWidthBottom(0);
		earningsTable.addCell(ge1);

		PdfPCell ge = new PdfPCell(new Paragraph(decimalFormat.format(employee.getGrossEarnings()), smallFont));
		ge.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		ge.setBorderWidthBottom(0);
		ge.setBorderWidthLeft(0.5f);
		earningsTable.addCell(ge);

		PdfPCell gd1 = new PdfPCell(new Paragraph("GROSS DEDUCTIONS", smallFont));
		gd1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align label to the left
		gd1.setBorderWidthLeft(0);
		gd1.setBorderWidthBottom(0);
		gd1.setBorderWidthRight(0.5f);
		earningsTable.addCell(gd1);

		PdfPCell gd = new PdfPCell(new Paragraph(decimalFormat.format(employee.getGrossDeductions()), smallFont));
		gd.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align right
		gd.setBorderWidthBottom(0);
		gd.setBorderWidthLeft(0);
		earningsTable.addCell(gd);

		PdfPCell netpay = new PdfPCell(new Paragraph("NET PAY", smallFont));
		netpay.setColspan(2);
		netpay.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align label to the left
		earningsTable.addCell(netpay);

		PdfPCell netpay1 = new PdfPCell(new Paragraph(decimalFormat.format(employee.getNetPay()), smallFont));
		netpay1.setColspan(2);
		netpay1.setHorizontalAlignment(Element.ALIGN_LEFT); // Align right
		earningsTable.addCell(netpay1);

        // Add the Earnings and Deductions Table to the document
		document.add(earningsTable);

		// Footer
		Paragraph footer = new Paragraph(
				"** This is a computer-generated payslip and does not require a signature or stamp.", smallFont);
		footer.setAlignment(Element.ALIGN_CENTER); // Center the footer text
		footer.setSpacingBefore(30f);
		document.add(footer);

		// Close the document
		document.close();
	}
}