package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.WithdrawalApply;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class WithdrawalApplyDao extends BaseDao<WithdrawalApply,Integer>{

	public void saveWithdrawalApply(WithdrawalApply withdrawalApply) {
		getSqlSession().insert(getStatementName("saveWithdrawalApply"),withdrawalApply);
	}
	
	public Page getWithdrawList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getWithdrawList"), pageRequest);
	}
	public int updateByPrimaryKeySelective(WithdrawalApply withdraApply) {
		int i = 0;
		i = getSqlSession().update(getStatementName("updateByPrimaryKeySelective"), withdraApply);
		return i;
	}
	
}
