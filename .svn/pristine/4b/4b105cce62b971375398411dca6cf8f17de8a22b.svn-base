package com.htkj.cfdScenic.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.PushMessageDao;
import com.htkj.cfdScenic.app.model.PushMessage;
import com.htkj.cfdScenic.app.model.Visitors;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.htrj.common.utils.GenerateSequenceUtil;
import com.htrj.common.utils.JPushUtils;

@Service
@Transactional
public class PuchMessageService {
	@Autowired
	private PushMessageDao puchMessageDao;
	
	public DataGrid pressPCList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = puchMessageDao.pressPCList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	
	public DataGrid getPushMessageList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = puchMessageDao.getPushMessageList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	
	public void insert(PushMessage pm,String ids,int sendType) {
		
		String[] strArray = ids.split(",");   
		List<String> pushIds = new ArrayList<String>();
		List<PushMessage> pms = new ArrayList<PushMessage>();
		//组装对象
		for(int i = 0;i<strArray.length;i++){
			PushMessage pushMessage = new PushMessage(); 
			pushIds.add(strArray[i]);
			pushMessage.setId(GenerateSequenceUtil.getUniqueId());
			pushMessage.setUserId(Long.parseLong(strArray[i]));
			pushMessage.setDetailId(pm.getDetailId());
			pushMessage.setImage(pm.getImage());
			pushMessage.setOrderCode("0");
			pushMessage.setType(0);
			pushMessage.setCreateDate(pm.getCreateDate());
			pushMessage.setTitle(pm.getTitle());
			pms.add(pushMessage);
		}
		
		//批量插入
		Map<String, Object> map = new HashMap<String, Object>();  
	    map.put("pushMessageList", pms);  
		puchMessageDao.insert(map);
		
		//推送消息
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("content","您有新的系统消息，请注意查收！");
		extras.put("goodsType","3");//0单品，1套餐
		extras.put("orderCode","0");
		extras.put("siId","0");
		extras.put("type","0");//0商户，1用户
		if(sendType ==0){//商铺
			JPushUtils.sendPushNotification(pushIds,extras);
		}else{//用户
			JPushUtils.sendPushNotificationForUser(pushIds,extras);
		}
		
	}
	
	
	
}
