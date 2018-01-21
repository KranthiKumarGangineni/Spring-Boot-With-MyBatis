package com.ti.kranthi.mybatis.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

/**
 * Class is used to Configure the Database
 * 
 * @author Kranthi
 */

/**
 * To Configure all the Beans we use below Annotation, Unlike using
 * <bean id="" class="">
 */
@Configuration

// Property Sources are used to keep multiple Property Sources like DB
// properties or
// we can keep multiple Constant files and use them here..
@PropertySources({ @PropertySource("file:${mybatis.home}/db.properties"),
		// If ignoreResourceNotFound is used, No error will be thrown even
		// though Properties file is Not Present
		@PropertySource(value = "file:${mybatis.home}/environment.properties", ignoreResourceNotFound = true) })

// Scanning the Components
@ComponentScan(value = "com.ti.kranthi")
// Scanning Mapper Classes
@MapperScan("com.ti.kranthi.mybatis.dao")
public class MyBatisConfiguration {

	private static final String MYBATIS_DB_PREFIX = "mybatis.db.";

	/**
	 * Autowired will automatically sets the Required Values No need to call
	 * setters explicitly When Class MyBatisConfiguration is Created,Spirng
	 * looks for "env" [Below] & Injects it.. Autowired can be used on
	 * Properties,Setters,Constrcutors
	 */
	@Autowired

	/**
	 * To get the Properties file, we will use Environment as per Spring
	 * Recomendation
	 */
	private Environment env;

	/**
	 * DataSource Creation
	 * 
	 * @throws Exception
	 */
	@Bean
	public DataSource getDataSourceDetails() throws Exception {
		// Creating DataSource with the DB properties..
		return BasicDataSourceFactory.createDataSource(getDBProperties());
	}

	/**
	 * Creates a SqlSessionFactoryBean used by the MyBatis mapper.
	 * 
	 * @return A SqlSessionFactoryBean object
	 * @throws Exception
	 *             on error
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSourceDetails());
		return sessionFactory;
	}

	/**
	 * Creating DB properties from db.properties file
	 * 
	 * @return Properties
	 */
	@SuppressWarnings("rawtypes")
	private Properties getDBProperties() {
		Properties dbProps = new Properties();

		// Return the PropertySources for this Environment in mutable form,
		// allowing for manipulation of the set of PropertySource objects that
		// should be searched when resolving properties against this Environment
		// object. The various MutablePropertySources methods such as addFirst,
		// addLast, addBefore and addAfter allow for fine-grained control over
		// property source ordering. This is useful, for example, in ensuring
		// that certain user-defined property sources have search precedence
		// over default property sources such as the set of system properties or
		// the set of system environment variables.
		MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
		for (org.springframework.core.env.PropertySource ps : sources) {
			if (ps instanceof MapPropertySource) {
				// Getting all the Property Names from Property file..
				String[] names = ((MapPropertySource) ps).getPropertyNames();
				iteratePropertyNames(names, dbProps, ps, MYBATIS_DB_PREFIX);
			}
		}
		return dbProps;
	}

	/**
	 * Added to resolve Sonar Issue if more > 3 If's are present
	 * 
	 * @param names
	 * @param dbProps
	 * @param ps
	 * @param mybatisPrefix
	 */
	@SuppressWarnings("rawtypes")
	private void iteratePropertyNames(String[] names, Properties dbProps,
			org.springframework.core.env.PropertySource ps, String prefix) {
		for (String propertyName : names) {
			// If Property is starting with mybatis.db then only setting the DB
			// properties..
			if (StringUtils.startsWith(propertyName, prefix)) {
				dbProps.put(StringUtils.substringAfter(propertyName, prefix), ps.getProperty(propertyName));
			}
		}
	}

}
