package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.Advertisement;
import com.htkj.cfdScenic.app.model.Jump;
import com.htkj.cfdScenic.app.service.AdvertisementService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

@RequestMapping("/background/advertisement")
@Controller
public class AdvertisementManagerController extends BaseController {

    @Autowired
    private AdvertisementService advertisementService;

    public final Logger	logger	= LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/toAdvertisement")
    public String toShopType() {
        return "/background/advertisement/Manager";
    }

    /**
     * 轮播图-列表
     * @param pagerForm
     * @param advertisement
     * @return
     */
    @RequestMapping("/getAdvertisementList")
    @ResponseBody
    public DataGrid getAdvertisementList(PagerForm pagerForm , Advertisement advertisement){
        return advertisementService.getAdvertisementList(pagerForm.getPageRequest(advertisement.toMap()));
    }

    /**
     * 跳到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap model){
    	List<Jump> list = advertisementService.getVisitorsAndShopInformation();
    	model.put("list",list);
        return "/background/advertisement/addPage";
    }
    /**
     * 查询根据类型查询跳转连接
     */
    @RequestMapping("getJumpType")
    @ResponseBody
    public Json getJumpType(Integer type){
    	Json json = new Json();
    	try {
    		List<Map> list = new ArrayList<Map>();
    		Map map = new HashMap();
    		switch (type) {
    		case 0:
    			map.put("type",0);
    			map.put("tableType",0);
    			break;
			case 1:
				map.put("type",1);
				map.put("tableType",1);
				break;
			case 2:
				map.put("type",2);
				map.put("tableType",1);
				break;
			case 3:
				map.put("type",3);
				map.put("tableType",1);
				break;
			case 4:
				map.put("type",4);
				map.put("tableType",1);
				break;
			case 5:
				map.put("type",1);
				map.put("tableType",2);
				break;
			case 6:
				map.put("type","2,3");
				map.put("tableType",2);
				break;
			case 7:
				map.put("type",1);
				map.put("tableType",3);
				break;
			case 8:
				map.put("type",2);
				map.put("tableType",3);
				break;
			case 9:
				map.put("type",3);
				map.put("tableType",3);
				break;
    		}
    		list = advertisementService.getJumpType(map);
			json.setObj(list);
			json.setSuccess(true);
		} catch (Exception e) {
				e.printStackTrace();
		}
    	return json;
    }
    
    /**
     * 跳到修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(Long id , ModelMap model){
        try {
            if (id != null){
                Advertisement advertisement = advertisementService.selectByPrimaryKey(id);
                Integer type = advertisement.getAdvertisementType();
                		Map map = new HashMap();
                		switch (type) {
                		case 0:
                			map.put("type",0);
                			map.put("tableType",0);
                			break;
            			case 1:
            				map.put("type",1);
            				map.put("tableType",1);
            				break;
            			case 2:
            				map.put("type",2);
            				map.put("tableType",1);
            				break;
            			case 3:
            				map.put("type",3);
            				map.put("tableType",1);
            				break;
            			case 4:
            				map.put("type",4);
            				map.put("tableType",1);
            				break;
            			case 5:
            				map.put("type",1);
            				map.put("tableType",2);
            				break;
            			case 6:
            				map.put("type",2);
            				map.put("tableType",2);
            				break;
            			case 7:
            				map.put("type",1);
            				map.put("tableType",3);
            				break;
            			case 8:
            				map.put("type",2);
            				map.put("tableType",3);
            				break;
            			case 9:
            				map.put("type",3);
            				map.put("tableType",3);
            				break;
                		}
                		List<Map> list = advertisementService.getJumpType(map);
            	model.put("list",list);
                model.put("model",advertisement);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "/background/advertisement/editPage";
    }

    /**
     * 添加/修改信息
     * @param imageFile
     * @param advertisement
     * @return
     */
    @RequestMapping("/insert")
    public String insert(@RequestParam("imageFile")CommonsMultipartFile[] files, Advertisement advertisement){
        String img = "";
        logger.info(files.length+"有没有图片！！！！！！！！！！！！！！！！！！！！！！！！！");
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
                    advertisement.setImgUrl(imageURL);

                }
            }
            if(advertisement.getId() != null && advertisement.getId() != 0){
                advertisementService.updateByPrimaryKeySelective(advertisement);
            }else{
                advertisement.setId(GenerateSequenceUtil.getUniqueId());
                advertisementService.insert(advertisement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/background/advertisement/toAdvertisement";
    }

    /**
     * 修改图片
     * @param imageFile
     * @param advertisement
     * @return
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
                advertisementService.updateByPrimaryKeySelective(advertisement);
            }else{
                advertisement.setId(GenerateSequenceUtil.getUniqueId());
                advertisementService.insert(advertisement);
            }
            s = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try {
            if(imageFile != null){
                if(!imageFile.isEmpty()){
                    *//** * imgPath为原文件名  *//*
                    int idx = imageFile.getOriginalFilename().lastIndexOf(".");
                    *//** * 文件后缀  *//*
                    String extention= imageFile.getOriginalFilename().substring(idx);

                    ImageBean image = UploadUtil.getImagePath(extention);

                    *//** * 复制文件  *//*
                    imageFile.transferTo(new File(image.getPath()));
                    *//** * 给image字段赋值 *//*
                    String imageUrl = WxGlobal.requestImgPath + image.getImageName();
                    System.out.println(imageUrl);
                    if(imageUrl != null && !imageUrl.equals(""))
                        advertisement.setImgUrl(imageUrl);
                }
            }
            if(advertisement.getId() != null && advertisement.getId() != 0){
                advertisementService.updateByPrimaryKeySelective(advertisement);
            }else{
                advertisement.setId(GenerateSequenceUtil.getUniqueId());
                advertisementService.insert(advertisement);
            }
            s = "success";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IOException",e);
        }*/
        return s;
    }

    @RequestMapping("/deleteAdvertisement")
    @ResponseBody
    public Json deleteAdvertisement(Long id){
        Json json = new Json();
        try {
            if (id != null){
                advertisementService.deleteAdvertisement(id);
                json.setSuccess(true);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return  json;
    }
    /**
     * 获取轮播图需要跳转的景点或者店铺
     */
    @RequestMapping("/getVisitorsAndShopInformation")
    @ResponseBody
    public Json getVisitorsAndShopInformation(){
    	Json json = new Json();
    	try {
			List<Jump> list = advertisementService.getVisitorsAndShopInformation();
			json.setObj(list);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
    	return json;
    }

}
