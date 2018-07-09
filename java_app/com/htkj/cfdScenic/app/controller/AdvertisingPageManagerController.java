package com.htkj.cfdScenic.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.htkj.cfdScenic.app.model.AdvertisingPage;
import com.htkj.cfdScenic.app.service.AdvertisingPageService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/background/advertisingPage")
@Controller
public class AdvertisingPageManagerController extends BaseController {

    @Autowired
    private AdvertisingPageService advertisingPageService;

    public final Logger	logger	= LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/toAdvertisingPage")
    public String toShopType() {
        return "/background/advertisingPage/Manager";
    }
    @RequestMapping("/getAdvertisingPage")
    @ResponseBody
    public DataGrid getAdvertisingPage(String name,String type,PagerForm page){
    	DataGrid dg = new DataGrid();
    	try {
    		Map map = new HashMap();
    		map.put("name",name);
    		map.put("type",type);
    		dg = advertisingPageService.getAdvertisingPage(page.getPageRequest(map));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return dg;
    }
    @RequestMapping("toAddPage")
    public String toAddPage(){
//    	model.put("model",advertisingPageService.getAdvertisingPageDetail(id));
    	return "/background/advertisingPage/addPage";
    }
    
    @RequestMapping("saveAdvertisingPage")
    public String saveAdvertisingPage(AdvertisingPage ap){
    	if(ap.getId() != null){
    		advertisingPageService.updateAdvertisingPage(ap);
    	}else{
    		ap.setId(GenerateSequenceUtil.getUniqueId());
    		advertisingPageService.saveAdvertisingPage(ap);
    	}
    	return "/background/advertisingPage/Manager";
    }
    @RequestMapping("toEditPage")
    public String toEditPage(ModelMap model,Long id){
    	model.put("model",advertisingPageService.getAdvertisingPageDetail(id));
    	return "/background/advertisingPage/editPage";
    }
    @RequestMapping("toDelete")
    @ResponseBody
    public Json toDelete(Long id){
    	Json json = new Json();
    	try {
    		advertisingPageService.deleteById(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return json;
    }
    
    @RequestMapping(value="/getAdvertisingPageById",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getAdvertisingPageById(Long id){
    	ResponseMsg msg = new ResponseMsg();
    	try {
			msg.setData(advertisingPageService.getAdvertisingPageById(id));
			msg.setHearder(0, "ok");
		} catch (Exception e) {
			msg.setHearder(1, "error");
			e.printStackTrace();
		}
    	return JSONObject.toJSONString(msg);
    }
    
    
    

}