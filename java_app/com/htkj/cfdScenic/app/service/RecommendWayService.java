/**  
* @Title: RecommendWayService.java
* @Package com.htkj.cfdScenic.app.service
* @Description: TODO(用一句话描述该文件做什么)
* @author 张伟烁 
* @date 2016年10月28日 上午10:45:31
*/ 
package com.htkj.cfdScenic.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.RecommendWayDao;
import com.htkj.cfdScenic.app.dao.VisitorsDao;
import com.htkj.cfdScenic.app.model.RecommendWay;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

/**
* @ClassName: RecommendWayService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 张伟烁
* @date 2016年10月28日 上午10:45:31
*
*/
@Service
@Transactional
public class RecommendWayService {
	@Autowired
	private RecommendWayDao recommendWayDao;

	@Autowired
	private VisitorsDao visitorsDao;
	/**
	 * @param pageRequest 
	* @Title: findGroupWay
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @author 张伟烁
	* @return List<Map<String,Object>>    返回类型
	* @throws
	*/ 
	public DataGrid findWayList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page<Map<String,Object>> page = recommendWayDao.findGroupWay(pageRequest);
			for(Map<String,Object> temp : page.getResult()){
				temp.put("listData", recommendWayDao.findWayByGroupId((Long)temp.get("group_id")));
			}
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询推荐路线出错", e);
		}
		return data;
	}

	public List<Map<String,Object>> getVisitorsInfo() {
		return visitorsDao.getVisitorsInfo();
	}

	public void saveRecommendWay(RecommendWay rw) {
		recommendWayDao.saveRecommendWay(rw);
	}

	public DataGrid getWayList(PageRequest<Map<String, Object>> pageRequest) {
		return DataGrid.pageToDataGrid(recommendWayDao.getWayList(pageRequest));
	}

	public List<Map<String,Object>> getWayDetail(Long groupId) {
		return recommendWayDao.getWayDetail(groupId);
	}

	public void toDeleteWay(Long groupId) {
		recommendWayDao.toDeleteWay(groupId);
	}
	
	
}
