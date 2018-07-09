package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.RefundCause;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class RefundCauseDao extends BaseDao{

	public void saveRefundCause(RefundCause refundCause) {
		getSqlSession().insert(getStatementName("saveRefundCause"),refundCause);
	}

	public RefundCause getRefundCause(Map para) {
		return getSqlSession().selectOne(getStatementName("getRefundCause"),para);
	}
	
	public Page getRefundGoodsOrderList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getRefundGoodsOrderList"), pageRequest);
	}
	
	public Page getRefundHotelOrderList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getRefundHotelOrderList"), pageRequest);
	}
	
	public Page getRefundRestaurantOrderList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getRefundRestaurantOrderList"), pageRequest);
	}
}
