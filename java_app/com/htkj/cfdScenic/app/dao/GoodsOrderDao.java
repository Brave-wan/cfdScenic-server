package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GoodsOrderDao extends BaseDao<GoodsOrder,Integer>{
	
	/**
	 * 保存购物车订单
	 */
	public void saveGoodsOrder(GoodsOrder goodsOrder){
		getSqlSession().insert(getStatementName("saveGoodsOrder"),goodsOrder);
	}

	public List findGoodsOrderById(Map map) {
		return getSqlSession().selectList(getStatementName("findGoodsOrderById"),map);
	}

	public void updateGoodsOrder(Map map) {
		getSqlSession().update(getStatementName("updateGoodsOrder"),map);
	}

	public void updateUndoGoodsOrder(Long id) {
		getSqlSession().update(getStatementName("updateUndoGoodsOrder"),id);
	}
	
	public void shopOrderChange(Map map) {
		getSqlSession().update(getStatementName("shopOrderChange"),map);
	}
	
	//获取特产订单
	public Page findGoodsOrder(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findGoodsOrder"),pageRequest);
	}
	
	//获取特产订单
	public Page findShopGoodsOrder(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findShopGoodsOrder"), pageRequest);
	}

	public List<Map<String,Object>> findGoodsOrderDetail(Long orderId) {
		return getSqlSession().selectList(getStatementName("findGoodsOrderDetail"),orderId);
	}

	public void goodsBackMoney(Map map) {
		getSqlSession().update(getStatementName("goodsBackMoney"),map);
	}

	public GoodsOrder getGoodsOrderByOrderId(Long id) {
		return getSqlSession().selectOne(getStatementName("getGoodsOrderByOrderId"),id);
	}

	public void updateGoodsOrderState(Map order) {
		getSqlSession().update(getStatementName("updateGoodsOrderState"),order);
	}

	public List<Map<String, Object>> getPayOrderFinshByOrderCode(Long orderCode) {
		return getSqlSession().selectList(getStatementName("getPayOrderFinshByOrderCode"),orderCode);
	}

	public void deleteGoodsOrder(Long orderCode) {
		getSqlSession().delete(getStatementName("deleteGoodsOrder"),orderCode);
	}

	public void updateGoodsOrderByOrderCode(Long orderCode) {
		getSqlSession().update(getStatementName("updateGoodsOrderByOrderCode"),orderCode);
	}

	public List<Map<String, Object>> informationGoodsOrderDetail(Map paraMap) {
		return getSqlSession().selectList(getStatementName("informationGoodsOrderDetail"),paraMap);
	}

	public void updateGoodsOrderByorderCodeAll(GoodsOrder goodsOrder) {
		getSqlSession().update(getStatementName("updateGoodsOrderByorderCodeAll"),goodsOrder);
	}

	public List<Map<String, Object>> shopFindGoodsOrder(Map paraMap) {
		return getSqlSession().selectList(getStatementName("shopFindGoodsOrder"),paraMap);
	}

	public List<Map<String, Object>> shopFindGoodsOrderDetail(Map para) {
		return getSqlSession().selectList(getStatementName("shopFindGoodsOrderDetail"),para);
	}

	public Long getShopInformation(Long orderCode) {
		return getSqlSession().selectOne(getStatementName("getShopInformation"),orderCode);
	}

	public Map<String, Object> getTodayMoney(Map para) {
		return getSqlSession().selectOne(getStatementName("getTodayMoney"),para);
	}

	public List<Map<String, Object>> findShopGoodsOrderNotIn(Map map) {
		return getSqlSession().selectList(getStatementName("findShopGoodsOrderNotIn"),map);
	}

	public List<Map<String, Object>> selectGoodsOrder(Map para) {
		return getSqlSession().selectList(getStatementName("selectGoodsOrder"),para);
	}
	public Map<String, Object> getIsDeliverFeeByOrderCode(String orderCode) {
		return getSqlSession().selectOne(getStatementName("getIsDeliverFeeByOrderCode"),orderCode);
	}

	public Integer findGoodsOrderCount(Map goodsMap) {
		return getSqlSession().selectOne(getStatementName("findGoodsOrderCount"),goodsMap);
	}

    public Page getGoodsOrderList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getGoodsOrderList"),pageRequest);
    }

    public Map<String, Object> selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
    }

    public void deleteGoodsOrderById(Long id) {
        getSqlSession().update(getStatementName("deleteGoodsOrderById"),id);
    }

    public void updateByOrderCode(GoodsOrder goodsOrder) {
        getSqlSession().update(getStatementName("updateByOrderCode"),goodsOrder);
    }

	public int updateByOrder(GoodsOrder goodsOrder) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatementName("updateByOrder"),goodsOrder);
	}

	public int updateBillingByOrderCode(GoodsOrder goodsOrder) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatementName("updateBillingByOrderCode"),goodsOrder);
	}

	public Map<String, Object> selectorderCode(long code) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatementName("selectorderCode"),code);
	}

    public Double getTurnover(Long shopInformationId) {
        return getSqlSession().selectOne(getStatementName("getTurnover"),shopInformationId);
    }

    public Double getTodayTurnover(Long shopInformationId) {
        return getSqlSession().selectOne(getStatementName("getTodayTurnover"),shopInformationId);
    }

	public Integer findShopGoodsOrderCount(Map map) {
		return getSqlSession().selectOne(getStatementName("findShopGoodsOrderCount"),map);
	}

	public GoodsOrder getGoodsOrderByOrderCode(String orderCode) {
		return getSqlSession().selectOne(getStatementName("getGoodsOrderByOrderCode"),orderCode);
	}

	public Integer findRefundGoodsOrderCount(Map goodsMap) {
		return getSqlSession().selectOne(getStatementName("findRefundGoodsOrderCount"),goodsMap);
	}

	public void updateDeleverFeeByOrderCode(Map map) {
		getSqlSession().update(getStatementName("updateDeleverFeeByOrderCode"),map);
	}

	public void updateGoodsOverdueOrder(Long userId) {
		getSqlSession().update(getStatementName("updateGoodsOverdueOrder"),userId);
	}

	public Integer getOrderCountByAddressId(Long id) {
		return getSqlSession().update(getStatementName("getOrderCountByAddressId"),id);
	}
	public Page pageGetorder(PageRequest<Map<String, Object>> pageRequest) {
		// TODO Auto-generated method stub
		return pageQuery(getStatementName("pageGetOrderList"), pageRequest);
	}
	public Page pageGetHotelOrder(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pageGetHotelOrder"), pageRequest);
	}
	public Page pageGetRestaurantOrder(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pageGetRestaurantOrder"), pageRequest);
	}
}
