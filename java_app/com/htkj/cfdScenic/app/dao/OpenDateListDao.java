package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.OpenDateList;
import com.htrj.common.base.BaseDao;

@Repository
public class OpenDateListDao extends BaseDao<OpenDateList,Integer>{

	public List<Map<String, Object>> selectOpenDateListByVisitorsId(Long visitorsId) {
		return getSqlSession().selectList(getStatementName("selectOpenDateListByVisitorsId"),visitorsId);
	}

	public void saveOpenDateList(OpenDateList odl) {
		getSqlSession().insert(getStatementName("saveOpenDateList"),odl);
	}

	public void updateOpenDateList(OpenDateList openDateList) {
		getSqlSession().update(getStatementName("updateOpenDateList"),openDateList);
	}

	public OpenDateList selectOneOpenDateListByVisitorsId(Long id) {
		return getSqlSession().selectOne(getStatementName("selectOneOpenDateListByVisitorsId"),id);
	}
	
}
