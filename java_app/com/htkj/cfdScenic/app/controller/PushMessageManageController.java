package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.PushMessage;
import com.htkj.cfdScenic.app.service.PuchMessageService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import com.htrj.common.utils.JPushUtils;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* <p>Title: PushMessageManageController</p>
* <p>Description: </p>
* @author Administrator
* @date 2016年10月20日上午11:23:11
 */
@RequestMapping("/background/pushMessageManage/")
@Controller
public class PushMessageManageController extends BaseController {

	@Autowired
	private PuchMessageService pushMessageService;
	
	@Autowired
	private ShopUserService shopUserService;
   
    /**
     * 推送消息主页面
     * @return
     */
    @RequestMapping("/toPushMessageManage")
    public String toPushMessageManage() {
        return "/background/pushMessage/Manager";
    }

    /**
     * 群发消息主页面-商铺
     * @return
     */
    @RequestMapping("/toMassMessageManage")
    public String toMassMessageManage() {
        return "/background/pushMessage/MassManager";
    }
    
    /**
     * 群发消息主页面-用户
     * @return
     */
    @RequestMapping("/toUserMassMessageManage")
    public String toUserMassMessageManage() {
        return "/background/pushMessage/userMassManager";
    }
    
    /**
     * 推送消息列表
     * @param page
     * @param 
     * @return
     */
    @RequestMapping("/getPushMessageList")
    @ResponseBody
    public DataGrid getPushMessageList(PagerForm page ,String createTime){
    
		Map<String , Object> map = new HashedMap();
        map.put("createTime" , createTime);
        return pushMessageService.getPushMessageList(page.getPageRequest(map));
    }
    
    /**
     * 获取商铺列表
     * @param page
     * @param 
     * @return
     */
    @RequestMapping("/getShopUserList")
    @ResponseBody
    public DataGrid getShopUserList(PagerForm page ,String name,String mobileNo,String shopName){
    
		Map<String , Object> map = new HashedMap();
        map.put("nickName" , name);
        map.put("mobileNo" , mobileNo);
        map.put("shopName" , shopName);
        return shopUserService.getShopUserList(page.getPageRequest(map));
    }
    
    
    /**
	 * Title:跳转到添加页面
	 */
	@RequestMapping("/toSendPage")
	public String toSendPage(String ids , String type,ModelMap model){
		model.put("ids",ids);
		model.put("type",type);
		return "/background/pushMessage/sendPage";
	}
	
	/**
	 * Title
	 * type 0 商户 1用户
	 */
	@RequestMapping("/sendMessage")
	public String sendMessage(@RequestParam("imageFile") CommonsMultipartFile[] files,PushMessage pushMessage,String ids,String title,Integer type){
		
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
            if (img != null && "" != img){
            	pushMessage.setImage(img);
            }
            pushMessage.setTitle(title);
            pushMessage.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            pushMessageService.insert(pushMessage,ids,type);
            
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new BusinessException("图片上传失败-IllegalStateException",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("图片上传失败-IOException",e);
		}
        String returnUrl="";
		if(type==0){
			returnUrl = "redirect:/background/pushMessageManage/toMassMessageManage";
		}else{
			returnUrl = "redirect:/background/pushMessageManage/toUserMassMessageManage";
		}
		return returnUrl;
	}
	
}
