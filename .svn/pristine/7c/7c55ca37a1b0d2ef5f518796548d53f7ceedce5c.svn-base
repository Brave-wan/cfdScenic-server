package com.htkj.cfdScenic.app.controller;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.HtmlText;
import com.htkj.cfdScenic.app.model.NewsNotice;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htkj.cfdScenic.app.service.NewsNoticeService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/newsNotice")
public class NewsNoticeController extends BaseController{
	@Autowired
	private NewsNoticeService newsNoticeService;
	@Autowired
	private HtmlTextService htmlTextService;
	
	/*
	 * 新闻须知列表
	 * GET
	 * http://localhost:8080/cfdScenic/NewsNotice/pressList
	 * 把新闻须知的信息返回
		id 				//新闻须知id
		name			//名称
		newsDescribe 	//描述
		headerImg		//图片
		content			//内容
		creator			//创建人
		createTime		//创建时间
		creatorId 		//创建人id
	 * 调用表 news_notice
	 */
	@RequestMapping(value="/pressList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String pressList(PagerForm pagerForm){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		//List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			//list = newsNoticeService.pressList();
			Map map = new HashMap();
			DataGrid data = newsNoticeService.pressList(pagerForm.getPageRequest(map));
			msg.setHearder(0, "success");
			msg.setData(data);
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	/*
	 * 新闻须知详情
	 * GET
	 * http://localhost:8080/cfdScenic/NewsNotice/pressDetails
	 * 根据新闻id把新闻须知的信息返回
		id 				//新闻须知id
		name			//名称
		newsDescribe 	//描述
		headerImg		//图片
		content			//内容
		creator			//创建人
		createTime		//创建时间
		creatorId 		//创建人id
	 * 调用表 news_notice
	 */
	@RequestMapping(value="/pressDetails",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String pressDetails(Long id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, String> content = new HashMap<String, String>();
		try {
			content = newsNoticeService.pressDetails(id);
			msg.setHearder(0, "success");
			msg.setData(content);
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	@RequestMapping("tolist")
	public String tolist() {
		return "/background/newsNotice/Manager";
	}
	@RequestMapping("list")
	@ResponseBody
	public DataGrid list(NewsNotice newsNotice,PagerForm pagerForm){
		return newsNoticeService.pressList(pagerForm.getPageRequest(newsNotice.toMap()));
	}
	@RequestMapping("toadd")
	public String toadd(){
		return "/background/newsNotice/addPage";
	}
	@RequestMapping("toupdate")
	public String toupdate(Model model,Long id){
		Map<String, String> map = newsNoticeService.pressDetails(id);
		model.addAttribute("model", map);
		if(map.get("content_url")!=null||map.get("content_url")!=""){
			try {
				String aString = map.get("content_url");
				Long url = Long.parseLong(map.get("content_url"));
				HtmlText htmlText = htmlTextService.selectByPrimaryKey(url);
				model.addAttribute("htmlText",htmlText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "/background/newsNotice/updatePage";
	}
	@RequestMapping("update")
	public String update(@RequestParam("imageFile")CommonsMultipartFile[] files,NewsNotice newsNotice){
		 String img = "";
		  try {
	            for(int i = 0;i<files.length;i++){
	                System.out.println("fileName---------->" + files[i].getOriginalFilename());
	                String newAddress = imagePath;
	                if(!files[i].isEmpty()){
	                    int pre = (int) System.currentTimeMillis();
	                    File file1 = new File(newAddress);
	                    //如果文件夹不存在则创建
	                    if(!file1.exists()  && !file1.isDirectory()){
	                        file1.mkdir();
	                    }
	                    String[] split = files[i].getOriginalFilename().split("\\.");
	                    String name = split[split.length-1];
	                    //时间戳+文件名
	                    String newName = new Date().getTime() + GenerateSequenceUtil.getUniqueId() +"."+ name;
	                    //上传的地址
	                    String imgrul = newAddress+ "\\" + newName;
	                    //存数据的地址
	                    String imageURL = requestImgPath +newName;
	                    //拿到输出流，同时重命名上传的文件
	                    FileOutputStream os = new FileOutputStream(imgrul);
	                    //拿到上传文件的输入流
	                    FileInputStream in = (FileInputStream) files[i].getInputStream();
	                    //以写字节的方式写文件
	                    int b = 0;
	                    while((b=in.read()) != -1){
	                        os.write(b);
	                    }
	                    os.flush();
	                    os.close();
	                    in.close();
	                    int finaltime = (int) System.currentTimeMillis();
	                    System.out.println(finaltime - pre);
	                    img += imageURL+"";

	                }
	            }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  if (img != null && "" != img){
			  newsNotice.setHeaderImg(img);
          }
		if (newsNotice.getId()!=null) {
			try {
				newsNoticeService.update(newsNotice);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			newsNotice.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			newsNotice.setId(GenerateSequenceUtil.getUniqueId());
			try {
				newsNoticeService.insert(newsNotice);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return "redirect:/newsNotice/tolist";
	}
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(Long id) {
		Json json = new Json();
		try {
			newsNoticeService.delete(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
}
