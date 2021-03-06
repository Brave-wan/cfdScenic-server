package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class HotelOrderDao extends BaseDao<HotelOrder,Integer>{

	public void saveHotelOrder(HotelOrder hotelOrder) {
		getSqlSession().insert(getStatementName("saveHotelOrder"),hotelOrder);
	}

	public Map getHotelOrder(Long gotelOrderId) {
		return getSqlSession().selectOne(getStatementName("getHotelOrder"),gotelOrderId);
	}

	public int getCountNumberHotelOrder(Map map) {
		return getSqlSession().selectOne(getStatementName("getCountNumberHotelOrder"),map);
	}

	public void updateHotelOrder(Map map) {
		getSqlSession().update(getStatementName("updateHotelOrder"),map);
	}

	public void updateUndoHotelOrder(Long id) {
		getSqlSession().update(getStatementName("updateUndoHotelOrder"),id);
	}
	//获取酒店订单
	public Page findHotelOrder(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findHotelOrder"), pageRequest);
		
	}
	//获取酒店订单
	public Integer findHotelOrderCount(Map map) {
		return getSqlSession().selectOne(getStatementName("findHotelOrderCount"),map);
		
	}
	//获取订单详情
	public List<Map<String,Object>> findHotelOrderDetail(Long orderId) {
		return getSqlSession().selectList(getStatementName("findHotelOrderDetail"),orderId);
	}

	public void hotelBackMoney(Map map) {
		getSqlSession().update(getStatementName("hotelBackMoney"),map);
	}
	
	public Page findOrderList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findOrderList"), pageRequest);
	}
	public Page findall(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findall"), pageRequest);
	}

	public HotelOrder findOrder(String orderCode) {
		return getSqlSession().selectOne(getStatementName("findOrder"),orderCode);
	}
	
	public List<Map<String,Object>> selectOrderBycode(Map map) {
		return getSqlSession().selectList(getStatementName("selectOrderBycode"),map);
	}

	public Integer deleteMessage(String orderCode) {
		return getSqlSession().delete(getStatementName("deleteMessage"),orderCode);
	}

	public void updateOrderState(HotelOrder hotelOrder) {
		getSqlSession().update(getStatementName("updateOrderState"),hotelOrder);
	}
	public void updateHotelOrderById(HotelOrder hotelOrder) {
		getSqlSession().update(getStatementName("updateHotelOrderById"),hotelOrder);
	}
	public void updateState(Map go) {
		getSqlSession().update(getStatementName("updateState"),go);
	}

	public Double getHotelOrderById(Long hotelOrderId) {
		return getSqlSession().selectOne(getStatementName("getHotelOrderById"),hotelOrderId);
	}

	public void updateHotelOrderByMap(Map map) {
		getSqlSession().update(getStatementName("updateHotelOrderByMap"),map);
	}

	public int getGoodsCount(Map para) {
		return getSqlSession().selectOne(getStatementName("getGoodsCount"),para);
	}

	public HotelOrder getHotelOrderDetail(Long hotelOrderId) {
		return getSqlSession().selectOne(getStatementName("getHotelOrderDetail"),hotelOrderId);
	}

	public void updateHotelOrderState(Map order) {
		getSqlSession().update(getStatementName("updateHotelOrderState"),order);
	}

	public Map<String, Object> getHotelOrderByOrderIdyl(Long id) {
		return getSqlSession().selectOne(getStatementName("getHotelOrderByOrderIdyl"),id);
	}
	public HotelOrder getHotelOrderByOrderId(Long id) {
		return getSqlSession().selectOne(getStatementName("getHotelOrderByOrderId"),id);
	}
	public void deleteHotelOrder(Long orderCode) {
		getSqlSession().delete(getStatementName("deleteHotelOrder"),orderCode);
	}

	public void updateHotelOrderByOrderCode(Long orderCode) {
		getSqlSession().update(getStatementName("updateHotelOrderByOrderCode"),orderCode);
	}

	public List<Map<String, Object>> informationHotelOrderDetail(Map paraMap) {
		return getSqlSession().selectList(getStatementName("informationHotelOrderDetail"),paraMap);
	}

	public void deleteState(Map go) {
		getSqlSession().delete(getStatementName("deleteState"),go);
	}

	public List<Map<String, Object>> shopFindHotelOrder(Map para) {
		return getSqlSession().selectList(getStatementName("shopFindHotelOrder"),para);
	}

	public List<Map<String, Object>> shopFindHotelOrderDetail(Map para) {
		return getSqlSession().selectList(getStatementName("shopFindHotelOrderDetail"),para);
	}

	public Map<String, Object> getTodayMoney(Map para) {
		return getSqlSession().selectOne(getStatementName("getTodayMoney"),para);
	}

	public List<Map<String, Object>> findHotelOrderNotIn(Map map) {
		return getSqlSession().selectList(getStatementName("findHotelOrderNotIn"),map);
	}

	public List<Map<String, Object>> selectHotelOrder(Map para) {
		return getSqlSession().selectList(getStatementName("selectHotelOrder"),para);
	}

	public int updatekaipiao(HotelOrder hotelOrder) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatementName("updatekaipiao"),hotelOrder);
	}

	public int updateBillingByOrderCode(HotelOrder hotelOrder) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatementName("updateBillingByOrderCode"),hotelOrder);
	}

	public Map<String, Object> getHotelOrderByFp(Long code) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatementName("getHotelOrderByFp"),code);
	}

    public Double getTurnover(Long shopInformationId) {
        return getSqlSession().selectOne(getStatementName("getTurnover"),shopInformationId);
    }

    public Double getTodayTurnover(Long shopInformationId) {
        return getSqlSession().selectOne(getStatementName("getTodayTurnover"),shopInformationId);
    }

	public void updateHotelOverdueOrder(Long userId) {
		getSqlSession().update(getStatementName("updateHotelOverdueOrder"),userId);
	}
}
