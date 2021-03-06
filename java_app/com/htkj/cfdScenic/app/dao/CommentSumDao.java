package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.CommentSum;
import com.htrj.common.base.BaseDao;

@Repository
public class CommentSumDao extends BaseDao<CommentSum,Integer>{

	public void saveShare(CommentSum commentSum) {
		getSqlSession().insert(getStatementName("saveShare"),commentSum);
	}

	public int getTraveLogsCount(String id) {
		return getSqlSession().selectOne(getStatementName("getTraveLogsCount"),id);
	}
	
	public Integer getFavorUserIdCount(Map<String,Object> map) {
		return getSqlSession().selectOne(getStatementName("getFavorUserIdCount"),map);
	}

	public int getCommentCount(String id) {
		return getSqlSession().selectOne(getStatementName("getCommentCount"),id);
	}

	public int getshareCount(String id) {
		return getSqlSession().selectOne(getStatementName("getshareCount"),id);
	}
	
	public void deleteFavor(CommentSum commentSum)
	{
		getSqlSession().delete(getStatementName("deleteFavor"), commentSum);
	}
	
}
