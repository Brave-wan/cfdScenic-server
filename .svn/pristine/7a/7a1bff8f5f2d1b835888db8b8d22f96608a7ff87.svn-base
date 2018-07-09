package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.Advertisement;
import com.htkj.cfdScenic.app.service.AdvertisService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;
/**
 * Title:pc端广告管理controller
 * @author:xingtongwu
 * @date:2016年9月2日
 */
@Controller
@RequestMapping("/background/advertisManage")
public class AdvertisManagerController extends BaseController{
	

		@Autowired
		private AdvertisService advertisService;

		 
		@RequestMapping("/toAdvertis")
	    public String toShopType() {
	        return "/background/advertis/Manager";
	    }

		/**
		 * Title:广告管理主页面
		 * @author:xingtongwu
		 * @date:2016年9月2日
		 */
		@RequestMapping("/toadvertisManage")
		public String toAdvertisManage(){
			return "/background/advertis/Manager";
		}
		
		/**
		 * Title:广告 - 列表
		 * @author xingtongwu
		 * @date 2016年9月2日
		 */
		@RequestMapping("/getAdvertisList")
		@ResponseBody
		public DataGrid getAdvertisList(PagerForm pagerForm , Advertisement advertisement){
			return advertisService.toadvertisManage(pagerForm.getPageRequest(advertisement.toMap()));
		}
		
		/**
		 * Title:广告-新增
		 * @author xingtongwu
		 * @date 2016年9月2日
		 */
		@RequestMapping("/addAdvertis")
		public String insert(@RequestParam("imageFile")CommonsMultipartFile[] files, Advertisement advertisement){
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
	                    img = imageURL;
	                    advertisement.setImgUrl(img);
	                }
	            }
	            if(advertisement.getId() != null && advertisement.getId() != 0){
	            	advertisService.updateByPrimaryKeySelective(advertisement);
	            }else{
	            	advertisement.setLinkId(0L);
	                advertisement.setId(GenerateSequenceUtil.getUniqueId());
	                advertisService.insert(advertisement);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return "redirect:/background/advertisManage/toAdvertis";
	    }
		/**
		 * Title:广告-修改主图
		 * @author xingtongwu
		 * @date 2016年9月2日
		 */
		 @RequestMapping("/updateImg")
		    @ResponseBody
		    public String updateImg(@RequestParam("imageFile")CommonsMultipartFile[] files , Advertisement advertisement){
		        String s = "";
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
		                    img += imageURL+",";

		                }
		            }
		                    advertisement.setImgUrl(img);
		            if(advertisement.getId() != null && advertisement.getId() != 0){
		            	advertisService.updateByPrimaryKeySelective(advertisement);
		            }else{
		                advertisement.setId(GenerateSequenceUtil.getUniqueId());
		                advertisService.insert(advertisement);
		            }
		            s = "success";
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return s;
		    }

	    /**
	     * 跳转到预览页面
	     * @param id
	     * @param model
	     * @return
	     */
		@RequestMapping("/showPage")
		 public String showPage(Long id , ModelMap model){
	        try {
	        	Advertisement advertis = advertisService.selectByPrimaryKey(id);
	            model.addAttribute("model",advertis);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return "/background/advertis/showPage";
	    }
		
		/**
		 * Title:跳转到添加页面
		 * @author wangfenglong
		 * @date 2016年9月6日
		 */
		@RequestMapping("/toAddPage")
		public String toAddPage(Integer type , ModelMap model){
			model.put("type",type);
			return "/background/advertis/toAddPage";
		}
		
		/**
		 * Title:跳转到修改页面
		 * @author wangfenglong
		 * @date 2016年9月6日
		 */
		@RequestMapping("/toEditPage")
		public String toEditPage(Long id , ModelMap model){
			try {
				Advertisement advertisement = advertisService.selectByPrimaryKey(id);
	            model.addAttribute("model", advertisement);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/background/advertis/toEditPage";
		}
		
		/**
		 * Title:广告 - 删除
		 * @author xingtongwu
		 * @date 2016年9月2日
		 */
		@RequestMapping("/deleteAdvertis")
		@ResponseBody
		public Json deleteAdvertis(Long id){
			Json json = new Json();
			try {
				advertisService.deleteByPrimaryKey(id);
				json.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return json;
		}
		
	}


