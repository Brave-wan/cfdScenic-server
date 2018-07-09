package com.htkj.cfdScenic.app.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.ShopsGroup;
import com.htrj.common.base.BaseDao;

@Repository
public class ShopsGroupDao extends BaseDao<ShopsGroup, Integer>{
	
	/**
	 * 查询全部分类
	 */
	public List<Map<String, Object>> getType(){
		return getSqlSession().selectList(getStatementName("getType"));
	}
}
