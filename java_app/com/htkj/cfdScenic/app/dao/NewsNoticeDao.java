package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.NewsNotice;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class NewsNoticeDao  extends BaseDao<NewsNotice, Integer>{

	public Page pressList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pressList"), pageRequest);
	}

	public Map<String, String> pressDetails(Long id) {
		return getSqlSession().selectOne(getStatementName("pressDetails"),id);
	}

	public int insert(NewsNotice newsNotice) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(getStatementName("insertSelective"),newsNotice);
	}

	public int update(NewsNotice newsNotice) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(getStatementName("updateByPrimaryKey"),newsNotice);
	}
	public int delete(Long id){
		return getSqlSession().delete(getStatementName("delete"),id);
	}

}
