package com.ti.kranthi.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ti.kranthi.mybatis.view.Employee;

@Mapper
public interface MyBatisDAO {

	/**
	 * Get Employee Details by userId
	 */
	Employee getEmployeeDetailByEmpId(@Param("userId") Integer userId, @Param("langCode") String langCode)
			throws DataAccessException;

	/**
	 * 
	 * @param userIdsList
	 * @param string
	 * @return List<Employee>
	 */
	List<Employee> getEmployeeDetailByEmpId(@Param("userIds") List<Integer> userIdsList,
			@Param("langCode") String langCode) throws DataAccessException;

}
