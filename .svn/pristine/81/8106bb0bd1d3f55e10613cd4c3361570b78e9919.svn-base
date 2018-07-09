package com.htkj.cfdScenic.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkj.cfdScenic.app.model.PublicPlaces;
import com.htkj.cfdScenic.app.service.PublicPlacesService;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;

@Controller
@RequestMapping("/background/publicPlaces")
public class PublicPlacesManagerController extends BaseController{
	
	@Autowired
	private PublicPlacesService publicPlacesService;
	
	@RequestMapping("/toPublicPlaces")
	public String toPublicPlaces(){
		return "/background/publicPlaces/Manager";
	}
	//获取列表
	@RequestMapping("/checkPublicPlaces")
	@ResponseBody
	public DataGrid checkPublicPlaces(PagerForm pagerForm){
		Map map = new HashMap();
		return publicPlacesService.checkPublicPlaces(pagerForm.getPageRequest(map));
	}
	//跳转添加页面
	@RequestMapping("/toSavePublicPlaces")
	public String toSavePublicPlaces(){
		return "/background/publicPlaces/editPage";
	}
	//修改或者插入数据
	@RequestMapping("/savePublicPlaces")
	public String savePublicPlaces(PublicPlaces publicPlaces){
		try {
			publicPlacesService.savePublicPlaces(publicPlaces);
		} catch (Exception e) {
			throw new BusinessException("保存失败，请重试",e);
		}
		return "redirect:/background/publicPlaces/toPublicPlaces";
	}
	//跳转修改页面
	@RequestMapping("/toUpdatePublicPlaces")
	public String toUpdatePublicPlaces(Long id,ModelMap modelMap){
		PublicPlaces publicPlaces = publicPlacesService.selectPublicPlaces(id);
		modelMap.put("model",publicPlaces);
		return "/background/publicPlaces/editPage";
	}
	//删除单条数据
	@RequestMapping("/deletePublicPlaces")
	@ResponseBody
	public Json deletePublicPlaces(Long id){
		return publicPlacesService.deletePublicPlaces(id);
	}
}
