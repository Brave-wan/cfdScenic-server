package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.Express;
import com.htkj.cfdScenic.app.model.RefundCause;
import com.htrj.common.base.BaseDao;

@Repository
public class ExpressDao extends BaseDao<Express, Integer>{
	
	public void saveExpress(Express express) {
		getSqlSession().insert(getStatementName("saveExpress"),express);
	}

	public Map<String,String> getExpressInfo(Map exMap) {
		return getSqlSession().selectOne(getStatementName("getExpressInfo"),exMap);
	}
}
