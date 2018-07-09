package com.htkj.cfdScenic.app.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.ShopCart;
import com.htrj.common.base.BaseDao;

@Repository
public class ShopCartDao extends BaseDao<ShopCart, Integer>{
	
	/**
	 * 根据userId查询购物车
	 */
	public List getShopCartByUserId(Long userId){
		return getSqlSession().selectList(getStatementName("getShopCartByUserId"),userId);
	}
	
	public List getShopCartShopGroupByUserId(Long userId){
		return getSqlSession().selectList(getStatementName("getShopCartShopGroupByUserId"),userId);
	}
	
	public List getShopCartGoodsByUserId(Map map){
		return getSqlSession().selectList(getStatementName("getShopCartGoodsByUserId"),map);
	}

	public void deleteShopCartById(Map map) {
		getSqlSession().delete(getStatementName("deleteShopCartById"),map);
	}

	public void saveShopCart(ShopCart shopCart) {
		getSqlSession().insert(getStatementName("saveShopCart"),shopCart);
	}

	public void updateShopCart(Map map) {
		getSqlSession().update(getStatementName("updateShopCart"),map);
	}

	public Long getGoodsIdById(Long id) {
		return getSqlSession().selectOne(getStatementName("getGoodsIdById"),id);
	}

	public Integer getNumber(Long userId) {
		return getSqlSession().selectOne(getStatementName("getNumber"),userId);
	}
	
}
