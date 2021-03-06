package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.*;
import com.htkj.cfdScenic.app.service.*;
import com.htkj.cfdScenic.app.util.MD5;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

import oracle.net.aso.a;

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
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

/**
 * @author wangfenglong
 * @date 2016/9/27 00279:36.
 */

@RequestMapping("/background/shopInformationManager")
@Controller
public class ShopInformationManageController extends BaseController {

    @Autowired
    private ShopInformationService shopInformationService;
    @Autowired
    private SysVerificationService sysVerificationService;
    @Autowired
    private HtmlTextService htmlTextService;
    @Autowired
    private ShopUserService shopUserService ;
    @Autowired
  	private SmsSendService smsSendService;
    @Autowired
    private PictureLibraryService pictureLibraryService;
    /**
     * 店铺信息主页面
     * @return
     */
    @RequestMapping("/toShopInformationManage")
    public String toShopInformationManage(ShopInformation shopInformation,ModelMap model){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		try {
    			shopInformation = shopInformationService.selectByShopUserPrimaryKey(user.getId());
    			Map<String, Object> sho = shopInformationService.selectByPrimaryKey(shopInformation.getId());
    			
//				String a = sho.get("detail_id").toString();
//				Long id = Long.parseLong(a);
    			if(shopInformation.getDetailId() != null){
    			HtmlText htmlText = htmlTextService.selectByPrimaryKey(shopInformation.getDetailId());
    			model.addAttribute("htmlText",htmlText);
    			}else{
    				model.addAttribute("htmlText",new HtmlText());
    			}
    			
    			List<Map<String, Object>> list = shopInformationService.getType();
                List list1 = pictureLibraryService.selectByLinkId(shopInformation.getId());
                model.put("picture",list1);
    			model.addAttribute("list",list);
    			model.addAttribute("model", sho);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	        return "/background/shopInformation/editPage";
    	}else{
    		return "/background/shopInformation/Manager";
    	}
    }
    /**
     * 店铺信息-列表
     * @param page
     * @param shopInformation
     * @return
     */
    @RequestMapping("/getShopInformationList")
    @ResponseBody
    public DataGrid getShopInformationList(PagerForm page , ShopInformation shopInformation){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		shopInformation.setShopUserId(user.getId());
    		return shopInformationService.getShopList(page.getPageRequest(shopInformation.toMap()));
    	}else{
    		return shopInformationService.getShopInformationList(page.getPageRequest(shopInformation.toMap()));
    	}
    }
    @RequestMapping("/addVisitors")
    @ResponseBody
    public Json addVisitors(PagerForm page , ShopInformation shopInformation){
    	 DataGrid data = new DataGrid();
    	 Json json = new Json();
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
		shopInformation.setShopUserId(user.getId());
		data = shopInformationService.getShopList(page.getPageRequest(shopInformation.toMap()));
		if(data.getTotal()>0){
			json.setSuccess(false);
			json.setMessage("只能添加一条信息");
		}else{
			json.setSuccess(true);
		}
		return json;
    }
    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @RequestMapping("/toAddPage")
    public String toAddPage(ModelMap model){
        List<Map<String, Object>> list = shopInformationService.getType();
        model.put("list",list);
        return "/background/shopInformation/addPage";
    }

    /**
     * 公用上传方法
     * @param files
     */
    public String uploadImg(CommonsMultipartFile[] files){
        String imageURL = "";
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
                    imageURL = requestImgPath +newName;
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
                }
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return imageURL;
    }

    /**
     * 店铺信息 - 添加
     * @param files
     * @param shopInformation
     * @return
     */
    @RequestMapping("/addShopInformation")
    public String addShopInformation(@RequestParam("imageFile1") CommonsMultipartFile[] files1 ,
                                     @RequestParam("imageFile2") CommonsMultipartFile[] files2 ,
                                     @RequestParam("imageFile3") CommonsMultipartFile[] files3 ,
                                     @RequestParam("imageFile4") CommonsMultipartFile[] files4 ,
                                     @RequestParam("imageFile5") CommonsMultipartFile[] files5 ,
                                     @RequestParam("imageFile6") CommonsMultipartFile[] files6 ,
                                     @RequestParam("imageFile7") CommonsMultipartFile[] files7 ,
                                     @RequestParam("imageFile8") CommonsMultipartFile[] files8 ,
                                     @RequestParam("imageFile9") CommonsMultipartFile[] files9 ,ShopInformation shopInformation , String pictureUrl){
        String img1 = uploadImg(files1);
        String img2 = uploadImg(files2);
        String img3 = uploadImg(files3);
        String img4 = uploadImg(files4);
        String img5 = uploadImg(files5);
        String img6 = uploadImg(files6);
        String img7 = uploadImg(files7);
        String img8 = uploadImg(files8);
        String img9 = uploadImg(files9);
        PictureLibrary pl = new PictureLibrary();
        try {
            if (img1 != null && "" != img1) {
                shopInformation.setHeadImg(img1);
            }  if (img2 != null && "" != img2) {
                shopInformation.setBackgroudImg(img2);
            }  if (img3 != null && "" != img3) {
                shopInformation.setHoldCardImg(img3);
            }  if (img4 != null && "" != img4) {
                shopInformation.setFaceCardImg(img4);
            }  if (img5 != null && "" != img5) {
                shopInformation.setBackCardImg(img5);
            }  if (img6 != null && "" != img6) {
                shopInformation.setLicenseImg(img6);
            }  if (img7 != null && "" != img7) {
                shopInformation.setOtherImg1(img7);
            }  if (img8 != null && "" != img8) {
                shopInformation.setOtherImg2(img8);
            }  if (img9 != null && "" != img9) {
                shopInformation.setShopImg(img9);
            }

            if(shopInformation.getId() != null && shopInformation.getId() != 0){
            	/*shopInformation.setIsAudit(0);*///默认提交
            	try {
					
            		shopInformationService.updateByPrimaryKeySelective(shopInformation);
                    pictureLibraryService.deleteByLinkId(shopInformation.getId());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
            }else{
                shopInformation.setId(GenerateSequenceUtil.getUniqueId());
                shopInformation.setState(0);
                shopInformation.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                shopInformation.setIsAudit(0);//默认提交
                shopInformationService.insert(shopInformation);
            }
            String[] split = pictureUrl.split(",");
            for (int i = 0 ; i < split.length ; i ++) {
                pl.setId(GenerateSequenceUtil.getUniqueId());
                pl.setName("店铺图集图片");
                pl.setPicDescribe("店铺图集图片");
                pl.setImgUrl(split[i]);
                pl.setLinkId(shopInformation.getId());
                pl.setType(6);
                pl.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                pl.setMemo("备注");
                pl.setImgRootUrl(split[i]);
                pictureLibraryService.savePictureLibraryAll(pl);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        }

        return "redirect:/background/shopInformationManager/toShopInformationManage";
    }



    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEditPage")
    public String toEditPage(Long id , ModelMap model){
        try {
            Map<String, Object> shopInformation = shopInformationService.selectByPrimaryKey(id);
            HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) shopInformation.get("detail_id"));
            List<Map<String, Object>> list = shopInformationService.getType();
            List list1 = pictureLibraryService.selectByLinkId(id);
            model.put("picture",list1);
            model.addAttribute("list",list);
            model.addAttribute("model", shopInformation);
            model.addAttribute("htmlText",htmlText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/background/shopInformation/editPage";
    }

    /**
     * 跳转到详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showPage")
    public String toShowPage(Long id , ModelMap model){
        try {
            Map<String, Object> shopInformation = shopInformationService.selectByPrimaryKey(id);
            HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) shopInformation.get("detail_id"));
            List<Map<String, Object>> list = shopInformationService.getType();
            List list1 = pictureLibraryService.selectByLinkId(id);
            model.put("picture",list1);
            model.addAttribute("list",list);
            model.addAttribute("model", shopInformation);
            model.addAttribute("htmlText",htmlText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/background/shopInformation/showPage";
    }


    /**
     * Title: - 删除
     * @author wangfenglong
     * @date 2016年9月2日
     */
    @RequestMapping("/deleteShopInformation")
    @ResponseBody
    public Json deleteShopInformation(Long id){
        Json json = new Json();
    	//假删除
    	ShopInformation information = new ShopInformation();
    	information.setId(id);
    	information.setState(2);
    	try {
    		shopInformationService.updateByPrimaryKeySelective(information);
    		json.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			json.setMessage("删除失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
        return json;
    }
    /**
     * Title: - 启用
     * @author  张腾跃 
     * @date 2016年10月18日14:56:28
     */
    @RequestMapping("/qiyong")
    @ResponseBody
    public Json qiyong(Long id){
        Json json = new Json();
        /*ShopInformation information = shopInformationService.getInforMationByid(id);
        information.setState(0);*/
        ShopInformation information = new ShopInformation();
    	information.setId(id);
    	information.setState(0);
        try {
        	shopInformationService.updateByPrimaryKeySelective(information);
        	json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage("启用失败");
			e.printStackTrace();
		}
        return json;
    }
    /**
     * Title: - 禁用店铺
     * @author 张腾跃
     * @date 2016年10月18日14:55:52
     */
    @RequestMapping("/jinyong")
    @ResponseBody
    public Json jinyong(Long id){
    	 Json json = new Json();
         ShopInformation information = shopInformationService.getInforMationByid(id);
         information.setState(1);
         try {
         	shopInformationService.updateByPrimaryKeySelective(information);
         	json.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			json.setSuccess(false);
			json.setMessage("禁用失败");
			e.printStackTrace();
		}
        return json;
    }
    //审核
    @RequestMapping("/audit")
    @ResponseBody
    public Json audit(ShopInformation shopInformation) {
        Json json = new Json();
        try{
			shopInformationService.audit(shopInformation);
            json.setSuccess(true);
        } catch (Exception e) {
        	e.printStackTrace();
            json.setSuccess(false);
        }
        return json;
    }
	/**
	 * 店铺认证
	 * 张腾跃
	 * 2016年10月10日16:03:20
	 */
    @RequestMapping("/renzShopInformation")
    public String renzShopInformation(@RequestParam("imageFile1") CommonsMultipartFile[] files1 ,
                                     @RequestParam("imageFile2") CommonsMultipartFile[] files2 ,
                                     @RequestParam("imageFile3") CommonsMultipartFile[] files3 ,
                                     ShopInformation shopInformation,ModelMap model){
        Json json = new Json();
    	String img1 = uploadImg(files1);
        String img2 = uploadImg(files2);
        String img3 = uploadImg(files3);
        try {
            if (img1 != null && "" != img1) {
                shopInformation.setHoldCardImg(img1);
            }  if (img2 != null && "" != img2) {
                shopInformation.setFaceCardImg(img2);
            }  if (img3 != null && "" != img3) {
                shopInformation.setBackCardImg(img3);
            }
            ShopUser  user = (ShopUser) webContext.getSessionShopUser();
            ShopInformation s = shopInformationService.selectByShopUserPrimaryKey(user.getId());
            if(s!=null){
            	shopInformation.setId(s.getId());
            }
            shopInformation.setShopUserId(user.getId());
            if(shopInformation.getId() != null && shopInformation.getId() != 0){
//            	shopInformation.setIsAudit(0);//默认提交
                shopInformationService.updateByPrimaryKeySelective(shopInformation);
            }else{
                shopInformation.setId(GenerateSequenceUtil.getUniqueId());
                shopInformation.setState(0);
                shopInformation.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                shopInformation.setIsAudit(0);//默认提交
                shopInformationService.insert(shopInformation);
            }
            json.setSuccess(true);
        } catch (IllegalStateException e) {
        	json.setSuccess(false);
        	json.setMessage("图片上传失败,请稍后重试");
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        }
        model.addAttribute("id", shopInformation.getId());
        return "/home/regit-sjrz";
    }
    /**
	 * 店铺认证
	 * 张腾跃
	 * 2016年10月10日16:03:20
     * @throws UnsupportedEncodingException 
	 */
    @RequestMapping("/renztjShopInformation")
    @ResponseBody
    public Json renztjShopInformation(@RequestParam("imageFile4") CommonsMultipartFile[] files4 ,
                                     @RequestParam("imageFile5") CommonsMultipartFile[] files5 ,
                                     @RequestParam("imageFile6") CommonsMultipartFile[] files6 ,
                                     @RequestParam("imageFile7") CommonsMultipartFile[] files7 ,
                                     ShopInformation shopInformation,ModelMap model) throws UnsupportedEncodingException{
        Json json = new Json();
        String img4 = uploadImg(files4);
        String img5 = uploadImg(files5);
        String img6 = uploadImg(files6);
        String img7 = uploadImg(files7);
        try {
        	if (img4 != null && "" != img4) {
                shopInformation.setLicenseImg(img4);
            }  if (img5 != null && "" != img5) {
                shopInformation.setOtherImg1(img5);
            }  if (img6 != null && "" != img6) {
                shopInformation.setOtherImg2(img6);
            }  if (img7 != null && "" != img7) {
                shopInformation.setShopImg(img7);
            }
            ShopUser  user = (ShopUser) webContext.getSessionShopUser();
            ShopInformation s = shopInformationService.selectByShopUserPrimaryKey(user.getId());
            if(s!=null){
            	shopInformation.setId(s.getId());
            }
            String phone ="";//定义空的手机号
            String str ="您的信息已提交，请耐心等待审核，审核结果会以短信方式进行通知";
//            if(user!=null){
            	//注册后没有进行验证，再次登陆后进行验证（使用userid进行匹配）
            	shopInformation.setShopUserId(user.getId());
            	phone = user.getTelPhone();
//            }else{
//            	//注册后直接进行验证
//            }
            if(shopInformation.getId() != null && shopInformation.getId() != 0){
            	shopInformation.setIsAudit(0);//默认提交
                shopInformationService.updateByPrimaryKeySelective(shopInformation);
            }else{
                shopInformation.setId(GenerateSequenceUtil.getUniqueId());
                shopInformation.setState(0);
                shopInformation.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                shopInformation.setIsAudit(0);//默认提交
                shopInformationService.insert(shopInformation);
            }
            SmsSendRecord sms = new SmsSendRecord();
			sms.setMobiles(phone);
			sms.setNeedstatus(false);
			sms.setContent(str);
			SysVerification sysVerification = new SysVerification();
			Map<String, String> map = smsSendService.SmsSend(sms);
			if (map.get("state").equals("0")) {
				json.setSuccess(true);
			} else {
				json.setSuccess(false);
				json.setMessage("信息发送失败");
			}
        } catch (IllegalStateException e) {
        	json.setSuccess(false);
        	json.setMessage("图片上传失败,请稍后重试");
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        }
        model.addAttribute("id", shopInformation.getId());
     
        return json;
    }
    /**
     * Title:保存店铺信息
     * @author:lishilong
     * @date:2016年10月17日
     */
    @RequestMapping("/saveShopInformation")
    public String saveShopInformation(ShopInformation shopInformation,@RequestParam("imageFile1") CommonsMultipartFile[] imageFile1,@RequestParam("imageFile2") CommonsMultipartFile[] imageFile2,String pictureUrl){
        PictureLibrary pl = new PictureLibrary();
    	try { 
    		Map<String, Object> sim = shopInformationService.selectByPrimaryKey(shopInformation.getId());
    		String passWord = sim.get("cash_password")+"";
    		String headImg = uploadImg(imageFile1);
    		String backgroudImg = uploadImg(imageFile2);
    		shopInformation.setHeadImg(headImg);
    		shopInformation.setBackgroudImg(backgroudImg);
    		if(!passWord.equals(shopInformation.getCashPassWord())){
    			String cashPassWord = shopInformation.getCashPassWord();
    			shopInformation.setCashPassWord(MD5.GetMD5Code(cashPassWord));
    		}
    		shopInformationService.updateByPrimaryKeySelective(shopInformation);
            pictureLibraryService.deleteByLinkId(shopInformation.getId());
            String[] split = pictureUrl.split(",");
            for (int i = 0 ; i < split.length ; i ++) {
                pl.setId(GenerateSequenceUtil.getUniqueId());
                pl.setName("店铺图集图片");
                pl.setPicDescribe("店铺图集图片");
                pl.setImgUrl(split[i]);
                pl.setLinkId(shopInformation.getId());
                pl.setType(6);
                pl.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                pl.setMemo("备注");
                pl.setImgRootUrl(split[i]);
                pictureLibraryService.savePictureLibraryAll(pl);
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("修改店铺失败",e);
		}
    	return "redirect:/background/shopInformationManager/toShopInformationManage";
    }
    /**
     * 跳转支付宝页面
     */
    @RequestMapping(value="/toAlipayInfo",produces = "text/html;charset=UTF-8")
    public String toAlipayInfo(ModelMap mm){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();
    	AlipayInfo ai = shopInformationService.getAlipayInfo(user.getId());
    	mm.put("model",ai);
    	return "/background/alipayInfo/editPage";
    }
    /**
     * 商户支付宝信息
     */
    @RequestMapping(value="editAlipayInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Json editAlipayInfo(AlipayInfo ai){
    	Json json = new Json();
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();
    	try {
    		if(ai.getId() == null){
    			ai.setShopUserId(user.getId());
    		}
    		json = shopInformationService.editAlipayInfo(ai);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return json;
    }
    




}

