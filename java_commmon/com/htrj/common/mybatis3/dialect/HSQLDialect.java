package com.htrj.common.mybatis3.dialect;

/**
 * Dialect for HSQLDB
 * @author badqiu
 */
public class HSQLDialect extends Dialect {
	
	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	@Override
	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		boolean hasOffset = offset > 0;
		
		return new StringBuffer( sql.length() + 10 )
		.append( sql )
		.insert( sql.toLowerCase().indexOf( "select" ) + 6, hasOffset ? " limit " + offsetPlaceholder + " " + limitPlaceholder : " top " + limitPlaceholder )
		.toString();
	}
    
}
