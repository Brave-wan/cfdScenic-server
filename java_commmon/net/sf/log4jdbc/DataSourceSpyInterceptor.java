package net.sf.log4jdbc;

import java.sql.Connection;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceSpyInterceptor implements MethodInterceptor {  
	public final Logger	logger	= LoggerFactory.getLogger(getClass());

    private RdbmsSpecifics rdbmsSpecifics = null;  
      
    private RdbmsSpecifics getRdbmsSpecifics(Connection conn) {  
        if(rdbmsSpecifics == null) {  
            rdbmsSpecifics = DriverSpy.getRdbmsSpecifics(conn);  
        }  
        return rdbmsSpecifics;  
    }  
      
    @Override
	public Object invoke(MethodInvocation invocation) throws Throwable {  
    	logger.info("--------输出拦截到的SQL----------");
    	logger.info(invocation.getClass().getName());
    	logger.info(invocation.getMethod().getName());
        Object result = invocation.proceed();  
        if(SpyLogFactory.getSpyLogDelegator().isJdbcLoggingEnabled()) {  
            if(result instanceof Connection) {  
                Connection conn = (Connection)result;  
                return new ConnectionSpy(conn,getRdbmsSpecifics(conn));  
            }  
        }  
        return result;  
    }  
  
}
