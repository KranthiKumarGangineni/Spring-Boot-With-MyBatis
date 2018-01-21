package com.ti.kranthi.mybatis.service;

import java.util.List;

import com.ti.kranthi.mybatis.view.Employee;

public interface MyBatisService {

	/**
	 * Fetch EmployeeDetails based on input userId
	 * 
	 * @param userId
	 * @return Employee
	 */
	Employee getEmployeeDetailByEmpId(Integer userId);

	/**
	 * Fetch Employee Details by userIds Query Param/If not present fetch all
	 * 
	 * @param userIdsList
	 * @return
	 */
	List<Employee> getEmployeeDetailsByUserIds(List<Integer> userIdsList);

}
