package com.htkj.cfdScenic.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.OrderPerson;
import com.htrj.common.base.BaseDao;

@Repository
public class OrderPersonDao extends BaseDao<OrderPerson,Integer>{
	/**
	 * 保存酒店入住信息
	 */
	public void saveOrderPerson(OrderPerson orderPerson){
		getSqlSession().insert(getStatementName("saveOrderPerson"),orderPerson);
	}

	public List<String> getPersonName(Long hotelOrderId) {
		return getSqlSession().selectList(getStatementName("getPersonName"),hotelOrderId);
	}
}
