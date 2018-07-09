package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.UserBank;
import com.htrj.common.base.BaseDao;

@Repository
public class UserBankDao extends BaseDao<UserBank,Integer>{

	public List getBank(Long userId) {
		return getSqlSession().selectList(getStatementName("getBank"),userId);
	}

	public void saveBank(UserBank userBank) {
		getSqlSession().insert(getStatementName("saveBank"),userBank);
	}

	public Map getBankByIdCardAndCardNumber(Map map) {
		return getSqlSession().selectOne(getStatementName("getBankByIdCardAndCardNumber"),map);
	}
	
}
