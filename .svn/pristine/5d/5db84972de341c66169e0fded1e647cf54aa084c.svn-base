package com.htkj.cfdScenic.app.dao;


import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.AlipayInfo;
import com.htrj.common.base.BaseDao;

@Repository
public class AlipayInfoDao extends BaseDao<AlipayInfo, Integer>{

	public void updateAlipayInfo(AlipayInfo ai) {
		getSqlSession().update(getStatementName("updateAlipayInfo"),ai);
	}

	public void saveAlipayInfo(AlipayInfo ai) {
		getSqlSession().insert(getStatementName("saveAlipayInfo"),ai);
	}

	public AlipayInfo getAlipayInfo(Long id) {
		return getSqlSession().selectOne(getStatementName("getAlipayInfo"),id);
	}
	//通过店铺ID获取支付宝信息
	public AlipayInfo getAlipayInfoBySiId(Long siId) {
		return getSqlSession().selectOne(getStatementName("getAlipayInfoBySiId"),siId);
	}

}
