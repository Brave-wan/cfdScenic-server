package com.htkj.cfdScenic.app.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.RestaurantPackage;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class RestaurantPackageDao extends BaseDao<RestaurantPackage,Integer>{

	public List findPackageGoodsById(Long id) {
		return getSqlSession().selectList(getStatementName("findPackageGoodsById"),id);
	}

	public Map<String, Object> findPackageByPackageId(Long id) {
		return getSqlSession().selectOne(getStatementName("findPackageByPackageId"),id);
	}

	public List selectRestaurantOrderByOrderCode(String orderCode) {
		return getSqlSession().selectList(getStatementName("selectRestaurantOrderByOrderCode"),orderCode);
	}
	public int updateByPrimaryKeySelective(RestaurantPackage restaurantPackage) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatementName("updateByPrimaryKeySelective"),restaurantPackage);
	}
	public int insert(RestaurantPackage restaurantPackage) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(getStatementName("insertByPrimaryKey"),restaurantPackage);
	}
	public int delete(RestaurantPackage restaurantPackage) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(getStatementName("deleteSelective"),restaurantPackage);
	}

	public Page getList(PageRequest<Map<String, Object>> pageRequest) {
		  return pageQuery(getStatementName("getList"),pageRequest);
	}

	public Map<String, Object> getshopInformationList(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatementName("getshopInformationList"),id);
	}

	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(getStatementName("deleteSelective"),id);
	}
	
}
