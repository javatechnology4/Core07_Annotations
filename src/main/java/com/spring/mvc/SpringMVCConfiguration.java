package com.spring.mvc;

import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.spring.mvc")
@PropertySource(value = "classpath:hibernate.properties")
@EnableTransactionManagement
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {// <bean>
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
		dataSource.setUrl(env.getProperty("hibernate.connection.url"));
		dataSource.setUsername(env.getProperty("hibernate.connection.username"));
		dataSource.setPassword(env.getProperty("hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(getDataSource());
		factory.setMappingResources("Customer.hbm.xml"); 
		Properties hibernateProperties = new Properties(); 
		hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		hibernateProperties.put("hibernate.default_schema", env.getProperty("hibernate.default_schema"));
		hibernateProperties.put("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
		//hibernateProperties.put("hibernate.transaction.factory_class", env.getProperty("hibernate.transaction.factory_class"));
		factory.setHibernateProperties(hibernateProperties);
		return factory;
	}

	@Bean
	public HibernateTemplate getHibernateTemplate(SessionFactory factory) {
		HibernateTemplate template = new HibernateTemplate();
		template.setSessionFactory(factory);
		return template;
	}
	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager manager=new HibernateTransactionManager();
		manager.setSessionFactory(sessionFactory);
		return manager;
	}
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source=new ResourceBundleMessageSource();
		source.setBasename("message");
		return source;
	}
	public Validator getValidator() {
		LocalValidatorFactoryBean validator=new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("javatechnology4@gmail.com");
		mailSender.setPassword("JAVAtechnology");
		Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		return mailSender;
	}

	
}
