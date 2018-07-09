package com.htrj.base.configuration.app;


import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.ibatis.session.SqlSessionFactory;

@Import({ApplicationConfigDataSource.class})
@Configuration
@ComponentScan(basePackages  = "com.**.dao")
public class ApplicationConfigDao {
	@Autowired
	@Qualifier("dataSource")
	public DataSource	dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-config.xml"));
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/com/ht*/**/dao/**/*.xml"));
		factoryBean.setDataSource(dataSource);
		return factoryBean.getObject();
	}
	 
}
