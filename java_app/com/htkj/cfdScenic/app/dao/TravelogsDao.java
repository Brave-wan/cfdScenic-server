package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.Travelogs;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class TravelogsDao extends BaseDao<Travelogs,Integer>{

	public Page getTraveLogs(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getTraveLogs"),pageRequest);
	}

	public Page getWonderful(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getWonderful"),pageRequest);
	}

	public List getCommentById(Map map) {
		return getSqlSession().selectList(getStatementName("getCommentById"),map);
	}
	
	public void writeCollect(Travelogs travelogs) {
		getSqlSession().insert(getStatementName("writeCollect"),travelogs);
	}

	public Page getTravelsByUserId(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getTravelsByUserId"),pageRequest);
	}

	public Page getCommentTravelsByUserId(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getCommentTravelsByUserId"),pageRequest);
	}

	public Map<String,Object> checkTravelLogDetail(Long travelLogId) {
		return getSqlSession().selectOne(getStatementName("checkTravelLogDetail"),travelLogId);
	}

	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(getStatementName("deleteById"),id);
	}

	public void deleteMyTravelLog(Long id) {
		getSqlSession().delete(getStatementName("deleteById"),id);
	}
}
