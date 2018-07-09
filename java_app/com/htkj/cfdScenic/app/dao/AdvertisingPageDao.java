package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.AdvertisingPage;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class AdvertisingPageDao extends BaseDao<AdvertisingPage, Integer>{

	public Page getAdvertisingPage(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getAdvertisingPage"),pageRequest);
	}

	public Map getAdvertisingPageDetail(Long id) {
		return getSqlSession().selectOne(getStatementName("getAdvertisingPageDetail"),id);
	}

	public void saveAdvertisingPage(AdvertisingPage ap) {
		getSqlSession().insert(getStatementName("saveAdvertisingPage"),ap);
	}

	public void updateAdvertisingPage(AdvertisingPage ap) {
		getSqlSession().update(getStatementName("updateAdvertisingPage"),ap);
	}

	public void deleteById(Long id) {
		getSqlSession().delete(getStatementName("deleteById"),id);
	}

	public String getAdvertisingPageById(Long id) {
		return getSqlSession().selectOne(getStatementName("getAdvertisingPageById"),id);
	}
	
}
