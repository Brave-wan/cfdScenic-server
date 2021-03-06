package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VisitorsOrderDao  extends BaseDao<VisitorsOrder, Integer>{

	public Integer insertMessage(VisitorsOrder visitorsOrder) {
		return getSqlSession().insert(getStatementName("insertMessage"),visitorsOrder);
	}

	public VisitorsOrder selectByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("selectByUserId"),userId);
	}

	public Map selectVisitorsOrder(Long id) {
		return getSqlSession().selectOne(getStatementName("selectVisitorsOrder"),id);
	}
	public Map selectVisitorsOrderFinsh(Long id) {
		return getSqlSession().selectOne(getStatementName("selectVisitorsOrderFinsh"),id);
	}
	
	public void updateStateMessage(Map<String, Object> map) {
		getSqlSession().selectOne(getStatementName("updateStateMessage"),map);
	}
	
	public void updatePayStatus(Map<String, Object> map) {
		getSqlSession().update(getStatementName("updatePayStatus"),map);
	}

	public Page selectOrderList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("selectOrderList"), pageRequest);
	}

	public Map<String, Object> selectById(Long id) {
		return getSqlSession().selectOne(getStatementName("selectById"),id);
	}

	public List selectMyAllVisitorsOrder(Long userId) {
		return getSqlSession().selectList(getStatementName("selectMyAllVisitorsOrder"),userId);
	}

	public Map selectVisitorsOrderDetail(Long id) {
		return getSqlSession().selectOne(getStatementName("selectVisitorsOrderDetail"),id);
	}

	public Integer getPersonCount(Map para) {
		return getSqlSession().selectOne(getStatementName("getPersonCount"),para);
	}

	public Page getVisitorsOrderWeiList(PageRequest<Map<String, Object>> pageRequest) {
		// TODO Auto-generated method stub
		return pageQuery(getStatementName("pageVisitorsOrderListWei"), pageRequest);
	}
	
	public Page getVisitorsOrderYiList(PageRequest<Map<String, Object>> pageRequest) {
		// TODO Auto-generated method stub
		return pageQuery(getStatementName("pageVisitorsOrderListYi"), pageRequest);
	}

	public Map<String, Object> getVisitorsOrderById(Long orderCode) {
		return getSqlSession().selectOne(getStatementName("getVisitorsOrderById"),orderCode);
	}

	public List<Map<String, Object>> findOrderByOrderId(Long orderCode) {
		return getSqlSession().selectList(getStatementName("findOrderByOrderId"),orderCode);
	}
	
	public Map<String, Object> selectByPrimaryKey(Long id) {
		return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
	}
	
	public int updateVisitorsOrder(VisitorsOrder visitorsOrder){
		return getSqlSession().update(getStatementName("updateByOrderCode"), visitorsOrder);
	}

	public int deleteVisitorsOrder(Long id) {
		return getSqlSession().update(getStatementName("deleteByPrimaryKey"), id);
	}

	public List<Long> getMyTickets(Long userId) {
		return getSqlSession().selectList(getStatementName("getMyTickets"), userId);
	}

	public void deleteMyTickets(Long orderCode) {
		getSqlSession().delete(getStatementName("deleteMyTickets"), orderCode);
	}

	public void updateMyTickets(Long orderCode) {
		getSqlSession().update(getStatementName("updateMyTickets"), orderCode);
	}

	public void updateOrderState(Long orderCode) {
		getSqlSession().update(getStatementName("updateOrderState"), orderCode);
	}

	public List<Map<String,Object>> getOrderDetail(Long orderCode) {
		return getSqlSession().selectList(getStatementName("getOrderDetail"), orderCode);
	}

	public List<Long> getMyTicketsByType(Map map) {
		return getSqlSession().selectList(getStatementName("getMyTicketsByType"), map);
	}

	public void updateOrderStateByOrderCode(Long orderCode) {
		getSqlSession().update(getStatementName("updateOrderStateByOrderCode"), orderCode);
	}

	public Integer getWaitPayByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("getWaitPayByUserId"), userId);
	}

	public Integer getAlreadyByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("getAlreadyByUserId"), userId);
	}

	public List<Map<String, Object>> getMyTicketsByOrderCode(Long orderCode) {
		return getSqlSession().selectList(getStatementName("getMyTicketsByOrderCode"), orderCode);
	}

    public Page getVisitorsOrderList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("pageVisitorsOrderList"), pageRequest);
    }

	public void updateTicketsOverDue(Long orderCodeLong) {
		getSqlSession().update(getStatementName("updateByOrderCode"), orderCodeLong);
	}

    public Double getTodayTurnover() {
        return getSqlSession().selectOne(getStatementName("getTodayTurnover"));
    }

    public Double getTurnover() {
        return getSqlSession().selectOne(getStatementName("getTurnover"));
    }

    public VisitorsOrder selectByOrderCode(String orderCode) {
        return getSqlSession().selectOne(getStatementName("selectByOrderCode"),orderCode);
    }

    public Map<String, Object> selectByCode(String orderCode) {
        return getSqlSession().selectOne(getStatementName("selectByCode"),orderCode);
    }

    public Page getInvoiceList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getInvoiceList"),pageRequest);
    }

	public String getOrderCodeByOrderId(Long orderId) {
		return getSqlSession().selectOne(getStatementName("getOrderCodeByOrderId"),orderId);
	}
}
