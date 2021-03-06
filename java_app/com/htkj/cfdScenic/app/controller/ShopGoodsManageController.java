package com.htkj.cfdScenic.app.controller;


import com.htkj.cfdScenic.app.model.*;
import com.htkj.cfdScenic.app.service.*;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

/**
 * @author wangfenglong
 * @date 2016/9/23 002310:51.
 */

@Controller
@RequestMapping("/background/shopGoodsManage")
public class ShopGoodsManageController extends BaseController{

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopInformationService shopInformationService;

    @Autowired
    private HtmlTextService htmlTextService;
    @Autowired
    private RestaurantPackageService packageService;



    @Autowired
    private PictureLibraryService pictureLibraryService;

    /**
     * 商品管理主页面
     * @return
     */
    @RequestMapping("/toShopGoodsManage")
    public String toShopGoodsManage(ModelMap map){
//        map.put("type",type);
        ShopUser user = (ShopUser) webContext.getSessionShopUser();
        ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
        map.addAttribute("type", information.getShopId());
        return "/background/shopGoods/Manager";
    }

    /**
     * 商品 - 列表
     * @param pagerForm
     * @param shopGoods
     * @return
     */
    @RequestMapping("/getShopGoodsList")
    @ResponseBody
    public DataGrid getShopGoodsList(PagerForm pagerForm , ShopGoods shopGoods,String danpin,RestaurantPackage restaurantPackage){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		if(danpin==null){
    			shopGoods.setUserId(user.getId());
    			return shopGoodsService.getShopShopGoodsList(pagerForm.getPageRequest(shopGoods.toMap()));
    		}else{
    			if(danpin=="0"||danpin.equals("0")){
    				shopGoods.setUserId(user.getId());
        			return shopGoodsService.getShopShopGoodsList(pagerForm.getPageRequest(shopGoods.toMap()));
    			}else{
    				ShopInformation shopInformation = shopInformationService.selectByShopUserPrimaryKey(user.getId());
    				restaurantPackage.setShopInformationId(shopInformation.getId());
    				return packageService.getList(pagerForm.getPageRequest(restaurantPackage.toMap()));
    			}
    		}
    	}else{
    		return shopGoodsService.getShopGoodsList(pagerForm.getPageRequest(shopGoods.toMap()));
    	}
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/toAddPage")
    public String toAddPage(Integer type , ModelMap model,String danpin){
        ShopUser user = (ShopUser) webContext.getSessionShopUser();
        ShopInformation shopInformation  = new ShopInformation();
        try {
        	shopInformation = shopInformationService.selectByShopUserPrimaryKey(user.getId());
        	model.addAttribute("shopInformation",shopInformation);
        	model.addAttribute("type",type);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        if(danpin==null||danpin==""){
        	return "/background/shopGoods/addPage";
        }else{
        	model.addAttribute("danpin",danpin);
        	if(danpin=="1"||danpin.equals("1")){
        		if(shopInformation!=null){
        			try {
        				List<ShopGoods> list = shopGoodsService.getGoodsByInformationId(shopInformation.getId());
        				model.put("list", list);
					} catch (Exception e) {
						e.printStackTrace();
					}
        		}
        	}
        	return "/background/shopGoods/adddanpinPage";
        }
    }

    @RequestMapping("/addshopGoods")
    public String addshopGoods(@RequestParam("imageFile")CommonsMultipartFile[] files, ShopGoods shopGoods , String pictureUrl){
        String img = "";
        ShopUser user = (ShopUser) webContext.getSessionShopUser();
        ShopInformation shopInformation = shopInformationService.selectByShopUserPrimaryKey(user.getId());
        Long s = shopInformation.getShopId();
        String str = s.toString();
        shopGoods.setType(Integer.parseInt(str));
        PictureLibrary pl = new PictureLibrary();
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
                shopGoods.setDescribeImg(img);
            }
            if(shopGoods.getId() != null && shopGoods.getId() != 0){
                shopGoodsService.updateByPrimaryKeySelective(shopGoods);
                pictureLibraryService.deleteByLinkId(shopGoods.getId());
            }else{
                shopGoods.setId(GenerateSequenceUtil.getUniqueId());
                shopGoods.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                shopGoodsService.insert(shopGoods);
            }
            if(pictureUrl!=null){
            	String[] split = pictureUrl.split(",");
            	for (int i = 0 ; i < split.length ; i ++) {
            		pl.setId(GenerateSequenceUtil.getUniqueId());
            		pl.setName("商品图集图片");
            		pl.setPicDescribe("商品图集图片");
            		pl.setImgUrl(split[i]);
            		pl.setLinkId(shopGoods.getId());
            		pl.setType(6);
            		pl.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            		pl.setMemo("备注");
            		pl.setImgRootUrl(split[i]);
            		pictureLibraryService.savePictureLibraryAll(pl);
            	}
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IOException",e);
        }
        return "redirect:/background/shopGoodsManage/toShopGoodsManage?type="+shopGoods.getType()+"";
    }

    @RequestMapping("/adddanpin")
    public String adddanpin(@RequestParam("imageFile")CommonsMultipartFile[] files, ShopGoods shopGoods,RestaurantPackage restaurantPackage,String danpin,Integer packageStock){
        String img = "";
        ShopUser user = (ShopUser) webContext.getSessionShopUser();
        ShopInformation shopInformation = shopInformationService.selectByShopUserPrimaryKey(user.getId());
        Long s = shopInformation.getShopId();
        String str = s.toString();
        shopGoods.setType(Integer.parseInt(str));
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
            	if(danpin=="1"||danpin.equals("1")){
                restaurantPackage.setHeadImg(img);
            	}else{
            		shopGoods.setDescribeImg(img);
            	}
            }
            if(danpin.equals("0")){
            	if(shopGoods.getId() != null && shopGoods.getId() != 0){
            		shopGoodsService.updateByPrimaryKeySelective(shopGoods);
            	}else{
	        		shopGoods.setId(GenerateSequenceUtil.getUniqueId());
	        		shopGoods.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	                shopGoodsService.insert(shopGoods);
            	}
            }else{
            	restaurantPackage.setName(shopGoods.getGoodsName());
            	restaurantPackage.setNoticeId(shopGoods.getContentId());
            	restaurantPackage.setShopInformationId(shopInformation.getId());
            	restaurantPackage.setStock(packageStock);
            	if(restaurantPackage.getId() != null && restaurantPackage.getId() != 0){
            		packageService.updateByPrimaryKeySelective(restaurantPackage);
              	}else{
              		restaurantPackage.setId(GenerateSequenceUtil.getUniqueId());
              		restaurantPackage.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
              		packageService.insert(restaurantPackage);
              	}

            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IOException",e);
        }
        return "redirect:/background/shopGoodsManage/toShopGoodsManage?type="+shopGoods.getType()+"";
    }
    /**
     * 修改主图
     * @param files
     * @param shopGoods
     * @return
     */
    @RequestMapping("/updateImg")
    @ResponseBody
    public String updateImg(@RequestParam("imageFile")CommonsMultipartFile[] files, ShopGoods shopGoods){
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
                    img += imageURL+"";

                }
            }
            if (img != null && "" != img){
                shopGoods.setDescribeImg(img);
            }
            if(shopGoods.getId() != null && shopGoods.getId() != 0){
                shopGoodsService.updateByPrimaryKeySelective(shopGoods);
            }else{
                shopGoods.setId(GenerateSequenceUtil.getUniqueId());
                shopGoods.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                shopGoodsService.insert(shopGoods);
            }
            s = "success";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IOException",e);
        }
        return s;
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteShopGoods")
    @ResponseBody
    public Json deleteShopGoods(Long id,String type){
        Json json = new Json();
        try {
        	if(type.equals("1")||type=="1"){
        		packageService.deleteById(id);
        	}else{
        		shopGoodsService.deleteShopGoods(id);
        	}
        	json.setSuccess(true);
        } catch (Exception e) {
        	e.printStackTrace();
            json.setSuccess(false);
        }
        return json;
    }

    /**
     * 跳转到修改商品页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEditPage")
    public String toEditPage(Long id , ModelMap model,String danpin){
        List list1 = pictureLibraryService.selectByLinkId(id);
        model.put("picture",list1);
    	if(danpin!=null){
    		if(danpin=="1"||danpin.equals("1")){
    			try {
    				Map<String , Object> restaurantPackage = packageService.getshopInformationList(id);
    				List<ShopGoods> list = shopGoodsService.getGoodsByInformationId((Long)restaurantPackage.get("shop_information_id"));
    				model.put("list", list);
    				HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) restaurantPackage.get("notice_id"));
    				model.addAttribute("repack",restaurantPackage);
    				model.addAttribute("htmlText",htmlText);
//    				model.addAttribute("type", 2);
    			model.addAttribute("danpin", 2);
    			} catch (Exception e) {
    				// TODO: handle exception
    				e.printStackTrace();
    			}
    			return "/background/shopGoods/editPage";
    		}else{
    			try {
        			Map<String , Object> shopGoods = shopGoodsService.selectByPrimaryKey(id);
        			HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) shopGoods.get("contentId"));
        			model.addAttribute("model",shopGoods);
        			model.addAttribute("htmlText",htmlText);
        			model.addAttribute("type", shopGoods.get("type"));
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		return "/background/shopGoods/editPage";
    		}
    	}else{
    		try {
    			Map<String , Object> shopGoods = shopGoodsService.selectByPrimaryKey(id);
    			HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) shopGoods.get("contentId"));
    			model.addAttribute("model",shopGoods);
    			model.addAttribute("htmlText",htmlText);
    			model.addAttribute("type", shopGoods.get("type"));
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return "/background/shopGoods/editPage";
    	}
    }

    /**
     * 查看详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showPage")
    public String showPage(Long id , ModelMap model,String danpin){
        try {
        	if(danpin!=null){
        		if(danpin=="1"||danpin.equals("1")){
            		Map<String , Object> restaurantPackage = packageService.getshopInformationList(id);
//    				List<ShopGoods> list = shopGoodsService.getGoodsByInformationId((Long)restaurantPackage.get("shop_information_id"));
//    				model.put("list", list);
    				HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) restaurantPackage.get("notice_id"));
    				model.addAttribute("repack",restaurantPackage);
    				String shopid = restaurantPackage.get("goods_ids").toString();
    				String[] shopids = shopid.split(",");
    				List list = new ArrayList();
    				for (int i = 0; i < shopids.length; i++) {
    					Long shid = Long.parseLong(shopids[i]);
    					Map map = shopGoodsService.selectByPrimaryKey(shid);
    					ShopGoods goods = new ShopGoods();
    					String gid = map.get("id").toString();
    					Long goodsid = Long.parseLong(gid);
    					goods.setId(goodsid);
    					goods.setDescribeImg(map.get("describeImg").toString());
    					list.add(goods);
					}
    				model.addAttribute("list", list);
    				model.addAttribute("htmlText",htmlText);
    				model.addAttribute("danpin", 1);
            	}else{
            		Map<String , Object> shopGoods = shopGoodsService.selectByPrimaryKey(id);
            		HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) shopGoods.get("contentId"));
            		List list1 = pictureLibraryService.selectByLinkId(id);
            		model.put("picture",list1);
            		model.addAttribute("model",shopGoods);
            		model.addAttribute("htmlText",htmlText);
            		model.addAttribute("type", shopGoods.get("type"));
            	}
        	}else{
        		Map<String , Object> shopGoods = shopGoodsService.selectByPrimaryKey(id);
        		HtmlText htmlText = htmlTextService.selectByPrimaryKey((Long) shopGoods.get("contentId"));
        		List list1 = pictureLibraryService.selectByLinkId(id);
        		model.put("picture",list1);
        		model.addAttribute("model",shopGoods);
        		model.addAttribute("htmlText",htmlText);
        		model.addAttribute("type", shopGoods.get("type"));
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "/background/shopGoods/showPage";
    }


}
