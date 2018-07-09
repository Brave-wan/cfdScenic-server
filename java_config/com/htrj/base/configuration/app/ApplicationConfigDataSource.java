package com.htrj.base.configuration.app;

import javax.inject.Inject;

import net.sf.log4jdbc.DataSourceSpyInterceptor;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

@Configuration
@EnableSpringConfigured
@Import({ ApplicationConfigProperties.class })
public class ApplicationConfigDataSource {
	@Inject
	Environment	env;

	@Bean
	public DataSource dataSource() {
		PoolProperties pool = new PoolProperties();
		pool.setName(env.getProperty("jdbc.alias"));
		pool.setUrl(env.getProperty("jdbc.url"));
		pool.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		pool.setUsername(env.getProperty("jdbc.username"));
		pool.setPassword(env.getProperty("jdbc.password"));
		pool.setJmxEnabled(Boolean.valueOf(env.getProperty("jdbc.jmxEnabled")));
		pool.setTestWhileIdle(Boolean.valueOf(env.getProperty("jdbc.testWhileIdle")));
		pool.setTestOnBorrow(Boolean.valueOf(env.getProperty("jdbc.testOnBorrow")));
		pool.setTestOnReturn(Boolean.valueOf(env.getProperty("jdbc.testOnReturn")));
		pool.setValidationInterval(Long.valueOf(env.getProperty("jdbc.validationInterval")));
		pool.setValidationQuery(env.getProperty("jdbc.validationQuery"));
		pool.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("jdbc.timeBetweenEvictionRunsMillis")));
		pool.setMaxIdle(Integer.valueOf(env.getProperty("jdbc.maxIdle")));
		pool.setMinIdle(Integer.valueOf(env.getProperty("jdbc.minIdle")));
		pool.setMaxActive(Integer.valueOf(env.getProperty("jdbc.maxActive")));
		pool.setInitialSize(Integer.valueOf(env.getProperty("jdbc.initialSize")));
		pool.setMaxWait(Integer.valueOf(env.getProperty("jdbc.maxWait")));
		pool.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("jdbc.minEvictableIdleTimeMillis")));
		pool.setLogAbandoned(Boolean.valueOf(env.getProperty("jdbc.logAbandoned")));
		pool.setRemoveAbandoned(Boolean.valueOf(env.getProperty("jdbc.removeAbandoned")));
		pool.setRemoveAbandonedTimeout(Integer.valueOf(env.getProperty("jdbc.removeAbandonedTimeout")));
		pool.setJdbcInterceptors(env.getProperty("jdbc.jdbcInterceptors"));
		DataSource dataSource = new DataSource(pool);

		return dataSource;
	}

	/**
	 * log4jdbc可以将数据源执行的sql将占位符?替换成字符,并以日志打印出来. logback配置: <logger name="jdbc.sqltiming"><level value="INFO" /></logger>
	 * 
	 * @return
	 */
	@Bean(name = "log4jdbcInterceptor")
	public DataSourceSpyInterceptor log4jdbcInterceptor() {
		return new DataSourceSpyInterceptor();
	}

	@Bean(name = "dataSourceLog4jdbcAutoProxyCreator")
	public BeanNameAutoProxyCreator dataSourceLog4jdbcAutoProxyCreator() {
		BeanNameAutoProxyCreator proxy = new BeanNameAutoProxyCreator();
		proxy.setBeanNames(new String[] { "dataSource" });
		proxy.setInterceptorNames(new String[] { "log4jdbcInterceptor" });
		return proxy;

	}
}
