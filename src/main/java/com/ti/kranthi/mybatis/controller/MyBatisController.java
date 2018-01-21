package com.ti.kranthi.mybatis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ti.kranthi.mybatis.service.MyBatisService;
import com.ti.kranthi.mybatis.view.Employee;

import javax.ws.rs.QueryParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Constants
import static com.ti.kranthi.mybatis.util.MyBatisConstants.NUMBER_REG_EXP;

// Rest Controller Annotation [A Specialized version of the @Controller annotation]
// It is a combination of @Controller+ @ResponseBody
// So that we no need to add @ResponseBody annotation to Request Mapping methods.
@RestController
public class MyBatisController {

	// Autowired annotation is a new style of Dependency Injection Drive
	@Autowired
	MyBatisService myBatisService;

	private static final Logger logger = Logger.getLogger(MyBatisController.class);

	private static final String EMP_DETAILS_OBJECT = "employeeDetails";
	private static final String EMP_DETAILS_VIEW = "employeeDetails";
	private static final String ERROR_PAGE_VIEW = "errorpage";

	/**
	 * Fetching Employee Details by employeeId
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/getEmployee/{userId}", method = RequestMethod.GET)
	public ModelAndView getEmployeeDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") String userId) {
		ModelAndView model = new ModelAndView();
		model.setViewName(ERROR_PAGE_VIEW);

		if (!(StringUtils.isNotBlank(userId) && userId.matches(NUMBER_REG_EXP))) {
			model.addObject("userIdInvalid", true);
			return model;
		}
		// Fetching EmployeeDetails Object
		Employee employeeDetails = myBatisService.getEmployeeDetailByEmpId(Integer.parseInt(userId));
		if (employeeDetails == null) {
			model.addObject("userId", userId);
			model.addObject("userObjectNull", true);
			return model;
		}
		logger.info("Empl Details are not null");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employeeDetails);
		model.addObject(EMP_DETAILS_OBJECT, employeeList);
		model.setViewName(EMP_DETAILS_VIEW);
		return model;
	}

	/**
	 * Fetch Employee Details by userIds passed as Query Param/If not passed
	 * anything, fetch all
	 * 
	 * @param request
	 * @param response
	 * @param userIds
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/getEmployee/users", method = RequestMethod.GET)
	public ModelAndView getEmployeeDetailsByEmpIds(HttpServletRequest request, HttpServletResponse response,
			@QueryParam("userId") String userIds) {
		ModelAndView model = new ModelAndView();
		model.setViewName(ERROR_PAGE_VIEW);

		List<String> userIdsList;
		List<Integer> userIdIntList = new ArrayList<>();
		// Iterating and Checking if userId is a valid Integer
		// & Adding them to Integer List
		if (StringUtils.isNotBlank(userIds)) {
			userIdsList = Arrays.asList(userIds.split(";"));
			userIdIntList = new ArrayList<>();
			for (String userId : userIdsList) {
				if (!(StringUtils.isNotBlank(userId) && userId.matches(NUMBER_REG_EXP))) {
					model.addObject("userIdInvalid", true);
					return model;
				} else {
					userIdIntList.add(Integer.parseInt(userId));
				}
			}
		}
		List<Employee> employeeDetailsList = myBatisService.getEmployeeDetailsByUserIds(userIdIntList);

		if (CollectionUtils.isEmpty(employeeDetailsList)) {
			model.addObject("userId", userIds);
			model.addObject("userObjectNull", true);
			return model;
		}
		model.addObject(EMP_DETAILS_OBJECT, employeeDetailsList);
		model.setViewName(EMP_DETAILS_VIEW);
		return model;
	}

	/**
	 * Inserting/Updating Employee Details
	 */

}
