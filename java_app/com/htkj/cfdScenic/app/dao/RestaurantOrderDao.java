package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RestaurantOrderDao extends BaseDao<RestaurantOrder,Integer>{

	/**
	 * 保存饭店订单
	 */
	public void saveRestaurantOrder(RestaurantOrder restaurantOrder){
		getSqlSession().insert(getStatementName("saveRestaurantOrder"),restaurantOrder);
	}

	public Map selectRestaurantOrderById(String orderCode) {
		return getSqlSession().selectOne(getStatementName("selectRestaurantOrderById"),orderCode);
	}

	public void updateRestaurantOrder(Map map) {
		getSqlSession().update(getStatementName("updateRestaurantOrder"),map);
	}

	public void updateUndoRestaurantOrder(Long id) {
		getSqlSession().update(getStatementName("updateUndoRestaurantOrder"),id);
	}
	//获取饭店为使用订单
	public Page findRestaurantOrder(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findRestaurantOrder"), pageRequest);
	}

	public List<Map<String,Object>> findRestaurantOrderDetail(Long orderId) {
		return getSqlSession().selectList(getStatementName("findRestaurantOrderDetail"),orderId);
	}

	public void restaurantBackMoney(Map map) {
		getSqlSession().update(getStatementName("restaurantBackMoney"),map);
	}

	public Page findOrderList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findOrderList"),pageRequest);
	}

	public Map<String, Object> findOrderFp(String orderCode) {
		return getSqlSession().selectOne(getStatementName("findOrderFp"),orderCode);
	}
	public List<RestaurantOrder> findOrder(String orderCode) {
		return getSqlSession().selectList(getStatementName("findOrder"),orderCode);
	}
	public List<Map<String,Object>> selectOrderBycode(Map<String,Object> map) {
		return getSqlSession().selectList(getStatementName("selectOrderBycode"),map);
	}
	
	public void updateState(Map ro) {
		getSqlSession().update(getStatementName("updateState"),ro);
	}

	public void updateOrderState(RestaurantOrder restaurantOrder) {
		getSqlSession().update(getStatementName("updateOrderState"),restaurantOrder);
	}

	public RestaurantOrder getRestaurantOrderByOrderId(Long id) {
		return getSqlSession().selectOne(getStatementName("getRestaurantOrderByOrderId"),id);
	}

	public void updateRestaurantOrderState(Map order) {
		getSqlSession().update(getStatementName("updateRestaurantOrderState"),order);
	}

	public void deleteRestaurantOrder(Long orderCode) {
		getSqlSession().delete(getStatementName("deleteRestaurantOrder"),orderCode);
	}

	public void updateRestaurantOrderByOrderCode(Long orderCode) {
		getSqlSession().update(getStatementName("updateRestaurantOrderByOrderCode"),orderCode);
	}

	public List<Map<String, Object>> informationRestaurantOrderDetail(Map paraMap) {
		return getSqlSession().selectList(getStatementName("informationRestaurantOrderDetail"),paraMap);
	}

	public List<Map<String, Object>> getOrderDetailByShop(Map goods) {
		return getSqlSession().selectList(getStatementName("getOrderDetailByShop"),goods);
	}

	public Map getRestaurantOrderByOrderCode(Map goods) {
		return getSqlSession().selectOne(getStatementName("getRestaurantOrderByOrderCode"),goods);
	}

	public void deleteState(Map ro) {
		getSqlSession().update(getStatementName("deleteState"),ro);
	}

	public List<Map<String, Object>> shopFindRestaurantOrder(Map para) {
		return getSqlSession().selectList(getStatementName("shopFindRestaurantOrder"),para);
	}

	public List<Map<String, Object>> shopFindRestaurantOrderDetail(Map para) {
		return getSqlSession().selectList(getStatementName("shopFindRestaurantOrderDetail"),para);
	}

	public Map<String, Object> getTodayMoney(Map para) {
		return getSqlSession().selectOne(getStatementName("getTodayMoney"),para);
	}

	public List<Map<String, Object>> findRestaurantOrderNotIn(Map map) {
		return getSqlSession().selectList(getStatementName("findRestaurantOrderNotIn"),map);
	}

	public List<Map<String, Object>> selectRestaurantOrder(Map para) {
		return getSqlSession().selectList(getStatementName("selectRestaurantOrder"),para);
	}

    public Page getRestaurantOrderList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getRestaurantOrderList"),pageRequest);
    }

    public Map<String, Object> selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
    }

    public void deleteVisitorsOrder(Long id) {
        getSqlSession().update(getStatementName("deleteByPrimaryKey"),id);
    }

    public void updateByOrderCode(RestaurantOrder restaurantOrder) {
        getSqlSession().update(getStatementName("updateByOrderCode"),restaurantOrder);
    }

	public Integer findRestaurantOrderCount(Map map) {
		return getSqlSession().selectOne(getStatementName("findRestaurantOrderCount"),map);
	}

	public int updateBybilling(RestaurantOrder restaurantOrder) {
		// TODO Auto-generated method stub
		return  getSqlSession().update(getStatementName("updateBybilling"),restaurantOrder);
	}

	public int updateBillingByOrderCode(RestaurantOrder restaurantOrder) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatementName("updateBillingByOrderCode"),restaurantOrder);
	}

    public Double getTurnover(Long shopInformationId) {
        return getSqlSession().selectOne(getStatementName("getTurnover"),shopInformationId);
    }

    public Double getTodayTurnover(Long shopInformationId) {
        return getSqlSession().selectOne(getStatementName("getTodayTurnover"),shopInformationId);
    }

	public Page getgroupOrderList(PageRequest<Map<String, Object>> pageRequest) {
		 return pageQuery(getStatementName("getgroupOrderList"),pageRequest);
	}

	public void updateRestaurantOverdueOrder(Long userId) {
		getSqlSession().update(getStatementName("updateRestaurantOverdueOrder"),userId);
	}

	public void updateToFinshOrder(RestaurantOrder ro) {
		getSqlSession().update(getStatementName("updateToFinshOrder"),ro);
	}
}
