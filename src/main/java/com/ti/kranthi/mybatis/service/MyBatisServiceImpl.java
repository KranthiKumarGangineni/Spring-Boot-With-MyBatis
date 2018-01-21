package com.ti.kranthi.mybatis.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ti.kranthi.mybatis.dao.MyBatisDAO;
import com.ti.kranthi.mybatis.view.Employee;

@Service
public class MyBatisServiceImpl implements MyBatisService {

	@Autowired
	MyBatisDAO myBatisDao;

	private static final Logger logger = Logger.getLogger(MyBatisServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getEmployeeDetailByEmpId(Integer userId) {
		Employee employeeDetail = null;
		try {
			employeeDetail = myBatisDao.getEmployeeDetailByEmpId(userId, "EN");
		} catch (DataAccessException dae) {
			logger.error("Exception occured while getting employee details by userId", dae);
		}
		return employeeDetail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getEmployeeDetailsByUserIds(List<Integer> userIdsList) {
		List<Employee> employeeDetails = null;
		try {
			employeeDetails = myBatisDao.getEmployeeDetailByEmpId(userIdsList, "EN");
		} catch (DataAccessException dae) {
			// Always log the messages properly
			logger.error("Exception occured while getting employee details by userIds", dae);
		}
		return employeeDetails;
	}

}
