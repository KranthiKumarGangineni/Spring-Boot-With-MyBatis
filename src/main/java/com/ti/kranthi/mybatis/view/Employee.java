package com.ti.kranthi.mybatis.view;

import java.util.HashSet;
import java.util.Set;

public class Employee {

	private Integer employeeId;
	private String employeeName;
	private Double employeeSalary;
	private String employeeLocation;
	private Integer employeeCompanyId;
	private String employeeCompanyName;
	private Integer phoneCode;
	private Long phoneNumber;
	private String employeeRole;
	private Set<OldCompanyDetails> employeeOldCompanyDetails = new HashSet<>();

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	public Integer getEmployeeCompanyId() {
		return employeeCompanyId;
	}

	public void setEmployeeCompanyId(Integer employeeCompanyId) {
		this.employeeCompanyId = employeeCompanyId;
	}

	public String getEmployeeCompanyName() {
		return employeeCompanyName;
	}

	public void setEmployeeCompanyName(String employeeCompanyName) {
		this.employeeCompanyName = employeeCompanyName;
	}

	public Integer getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public Set<OldCompanyDetails> getEmployeeOldCompanyDetails() {
		return employeeOldCompanyDetails;
	}

	public void setEmployeeOldCompanyDetails(Set<OldCompanyDetails> employeeOldCompanyDetails) {
		this.employeeOldCompanyDetails = employeeOldCompanyDetails;
	}

	public Employee() {
		// Default Constructor
	}

}
