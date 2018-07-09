package com.htkj.cfdScenic.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.PushMessage;
import com.htkj.cfdScenic.app.model.Visitors;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class PushMessageDao extends BaseDao<PushMessage,Integer>{

	public Page getMyMessage(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getMyMessage"), pageRequest);
		
	}

	public Page pressPCList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pressPCList"), pageRequest);
	}
	
	public Page getPushMessageList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getPushMessageList"), pageRequest);
	}
	
	public int insert(Map<String, Object> map){
		int i = 0;
		i = getSqlSession().insert(getStatementName("insertPushMessageBatch"), map);
		return i;
	}
	
}
