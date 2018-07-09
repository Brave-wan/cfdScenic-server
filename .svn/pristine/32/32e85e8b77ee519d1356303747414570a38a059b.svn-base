package com.htrj.base.configuration.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;

@Configuration
@Import({ ApplicationConfigDao.class })
@ComponentScan(basePackages = { "com.**.service"})
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationConfigServer {
	@Autowired
	@Qualifier("dataSource")
	public DataSource	dataSource;

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
		AnnotationTransactionAspect.aspectOf().setTransactionManager(txManager);
		return txManager;
	}
}
