package com.htkj.cfdScenic.app.dao;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.SysVerification;
import com.htrj.common.base.BaseDao;

@Repository
public class SysVerificationDao extends BaseDao<SysVerification, Integer>{

	public SysVerification getMessage(String mobileNo) {
		return getSqlSession().selectOne(getStatementName("getMessage"),mobileNo);
	}

	public void insertMessage(SysVerification sysVerification) {
		getSqlSession().insert(getStatementName("insertMessage"),sysVerification);
	}

	public void updateMessage(SysVerification message) {
		getSqlSession().insert(getStatementName("updateMessage"),message);
	}
}
