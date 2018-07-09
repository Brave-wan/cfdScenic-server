package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.Advertisement;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class AdvertisDao extends BaseDao<Advertisement, Integer>{

	public Page pageGetAdvertis(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pageGetAdvertisList"), pageRequest);
	}

	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete(getStatementName("deleteByPrimaryKey"),id);
	}

	public Advertisement selectByPrimanrKey(Long id) {
		  return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
	}

	public int insert(Advertisement advertisement) {
		return getSqlSession().insert(getStatementName("insertSelective"),advertisement);
	}

	public int updateByPrimaryKeySelective(Advertisement advertisement) {
		 return  getSqlSession().update(getStatementName("updateByPrimaryKeySelective"),advertisement);
	}

	public List<Map<String, Object>> scenicSpotLimitPage(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> scenicSpotParticulars(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> selectPictureLibrary(Long cId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, String>> obscureSelect(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
