package com.prashant.main.model;

import java.util.Date;

public class Employee {
	private int employeeId;
    private String name;
    private String bank;
    private Long bankAccountNo;
    private Date doj;
    private int lopDays;
    private String pan;
    private int stdDays;
    private String location;
    private int workedDays;
    private String department;
    private String entity;
    private Long pfUan;
    private double basic;
    private double houseRentAllowance;
    private double specialAllowance;
    private double onCallAllowance;
    private double variablePay;
    private double providentFund;
    private double professionalTax;
    private double esi;
    private double incomeTax;
    private double grossEarnings;
    private double grossDeductions;
    private double netPay;
    private String Email;
    
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeId, String name, String bank, Long bankAccountNo, Date doj, int lopDays, String pan,
			int stdDays, String location, int workedDays, String department, String entity, Long pfUan, double basic,
			double houseRentAllowance, double specialAllowance, double onCallAllowance, double variablePay,
			double providentFund, double professionalTax, double esi, double incomeTax, double grossEarnings,
			double grossDeductions, double netPay, String Email) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.bank = bank;
		this.bankAccountNo = bankAccountNo;
		this.doj = doj;
		this.lopDays = lopDays;
		this.pan = pan;
		this.stdDays = stdDays;
		this.location = location;
		this.workedDays = workedDays;
		this.department = department;
		this.entity = entity;
		this.pfUan = pfUan;
		this.basic = basic;
		this.houseRentAllowance = houseRentAllowance;
		this.specialAllowance = specialAllowance;
		this.onCallAllowance = onCallAllowance;
		this.variablePay = variablePay;
		this.providentFund = providentFund;
		this.professionalTax = professionalTax;
		this.esi = esi;
		this.incomeTax = incomeTax;
		this.grossEarnings = grossEarnings;
		this.grossDeductions = grossDeductions;
		this.netPay = netPay;
		this.Email = Email;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Long getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(Long bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public int getLopDays() {
		return lopDays;
	}

	public void setLopDays(int lopDays) {
		this.lopDays = lopDays;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public int getStdDays() {
		return stdDays;
	}

	public void setStdDays(int stdDays) {
		this.stdDays = stdDays;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getWorkedDays() {
		return workedDays;
	}

	public void setWorkedDays(int workedDays) {
		this.workedDays = workedDays;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Long getPfUan() {
		return pfUan;
	}

	public void setPfUan(Long pfUan) {
		this.pfUan = pfUan;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getHouseRentAllowance() {
		return houseRentAllowance;
	}

	public void setHouseRentAllowance(double houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}

	public double getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(double specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public double getOnCallAllowance() {
		return onCallAllowance;
	}

	public void setOnCallAllowance(double onCallAllowance) {
		this.onCallAllowance = onCallAllowance;
	}

	public double getVariablePay() {
		return variablePay;
	}

	public void setVariablePay(double variablePay) {
		this.variablePay = variablePay;
	}

	public double getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}

	public double getProfessionalTax() {
		return professionalTax;
	}

	public void setProfessionalTax(double professionalTax) {
		this.professionalTax = professionalTax;
	}

	public double getEsi() {
		return esi;
	}

	public void setEsi(double esi) {
		this.esi = esi;
	}

	public double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public double getGrossEarnings() {
		return grossEarnings;
	}

	public void setGrossEarnings(double grossEarnings) {
		this.grossEarnings = grossEarnings;
	}

	public double getGrossDeductions() {
		return grossDeductions;
	}

	public void setGrossDeductions(double grossDeductions) {
		this.grossDeductions = grossDeductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	
	
    
}
