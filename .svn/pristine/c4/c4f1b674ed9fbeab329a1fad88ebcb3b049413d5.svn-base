package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.dao.OpenDateListDao;
import com.htkj.cfdScenic.app.model.HtmlText;
import com.htkj.cfdScenic.app.model.OpenDateList;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.Visitors;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.upload.UploadFile;
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
import java.util.Date;
import java.util.List;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

/**
 * Title:pc端景区管理controller
 * @author:lishilong
 * @date:2016年9月2日
 */
@Controller
@RequestMapping("/background/visitorsManage")
public class VisitorsManageController extends BaseController{
	
	@Autowired
	private ShopInformationService consumerUserService;
	
	@Autowired
	private VisitorsService visitorsService;

    @Autowired
    private HtmlTextService htmlTextService;
    
    @Autowired
    private OpenDateListDao openDateListDao;
	
	/**
	 * Title:景点管理主页面
	 * @author:lishilong
	 * @date:2016年9月2日
	 */
	@RequestMapping("/toVisitorsManage")
	public String toVisitorsManage(@RequestParam("type") Integer type , ModelMap map){
		map.put("type",type);
		return "/background/visitors/Manager";
	}
	
	/**
	 * Title:景点 - 列表
	 * @author wangfenglong
	 * @date 2016年9月2日
	 */
	@RequestMapping("/getVisitorsList")
	@ResponseBody
	public DataGrid getVisitorsList(PagerForm pagerForm , Visitors visitors){
		return visitorsService.toVisitorsManage(pagerForm.getPageRequest(visitors.toMap()));
	}

	@RequestMapping("/uploadPicture")
    @ResponseBody
	public Json uploadPicture(@RequestParam("imageFile") CommonsMultipartFile[] files){
        String imageURL = "";
        Json json = new Json();
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
            json.setSuccess(true);
            json.setObj(imageURL);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IllegalStateException",e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("图片上传失败-IOException",e);
        }
        return json;
    }
	
	/**
	 * Title:景点-新增
	 * @author wangfenglong
	 * @date 2016年9月2日
	 */
	@RequestMapping("/addVisitors")
	public String addVisitors(String pictureUrl ,Visitors visitors , Integer typeId , String openDate,Long openDateId,@RequestParam("imageFile1") CommonsMultipartFile[] files){

        try {
        	if(files.length > 0){
        		String headImg = uploadImg(files);
        		if(!headImg.equals("")){
        			visitors.setHeadImg(headImg);
        		}
        	}
			if(visitors.getId() != null && visitors.getId() != 0){
				visitorsService.updateByPrimaryKeySelective(visitors);
				//删除这个景区的图集重新添加
				visitorsService.deletePictureLibraryByVisitorsId(visitors.getId());
				if(pictureUrl.indexOf(",") == -1){
					PictureLibrary pictureLibrary = new PictureLibrary();
					pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
					pictureLibrary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					pictureLibrary.setImgRootUrl(pictureUrl);
					pictureLibrary.setImgUrl(pictureUrl);
					pictureLibrary.setLinkId(visitors.getId());
					pictureLibrary.setMemo("备注");
					pictureLibrary.setName("景区图集");
					pictureLibrary.setPicDescribe("图片描述");
					pictureLibrary.setType(1);
					visitorsService.savePictureLibrary(pictureLibrary);
				}else{
					String[] pic = pictureUrl.split(",");
					for(int i=0;i<pic.length;i++){
						PictureLibrary pictureLibrary = new PictureLibrary();
						pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
						pictureLibrary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pictureLibrary.setImgRootUrl(pic[i]);
						pictureLibrary.setImgUrl(pic[i]);
						pictureLibrary.setLinkId(visitors.getId());
						pictureLibrary.setMemo("备注");
						pictureLibrary.setName("景区图集");
						pictureLibrary.setPicDescribe("图片描述");
						pictureLibrary.setType(1);
						visitorsService.savePictureLibrary(pictureLibrary);
					}
				}
				//景区开放时间
				if(openDateId != null){
					OpenDateList openDateList = new OpenDateList();
					openDateList.setTimeDetail(openDate);
					openDateList.setId(openDateId);
					visitorsService.updateOpenDateList(openDateList);
				}else{
					OpenDateList odl = new OpenDateList();
					odl.setId(GenerateSequenceUtil.getUniqueId());
					odl.setName(visitors.getName());
					odl.setTimeDetail(openDate);
					odl.setVisitorsId(visitors.getId());
					odl.setState(0);
					odl.setStartValid(visitors.getStartValid());
					odl.setEndValid(visitors.getEndValid());
					visitorsService.saveOpenDateList(odl);
				}
			}else{
				Long visitorsId = GenerateSequenceUtil.getUniqueId();
				visitors.setId(visitorsId);
				visitors.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(visitors.getType() == 4){
					visitors.setStartValid(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					visitors.setEndValid(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}
				visitorsService.insert(visitors);
				if(visitors.getType() != 4){
				if(pictureUrl.indexOf(",") == -1){
					PictureLibrary pictureLibrary = new PictureLibrary();
					pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
					pictureLibrary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					pictureLibrary.setImgRootUrl(pictureUrl);
					pictureLibrary.setImgUrl(pictureUrl);
					pictureLibrary.setLinkId(visitors.getId());
					pictureLibrary.setMemo("备注");
					pictureLibrary.setName("景区图集");
					pictureLibrary.setPicDescribe("图片描述");
					pictureLibrary.setType(1);
					visitorsService.savePictureLibrary(pictureLibrary);
				}else{
					String[] pic = pictureUrl.split(",");
					for(int i=0;i<pic.length;i++){
						PictureLibrary pictureLibrary = new PictureLibrary();
						pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
						pictureLibrary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pictureLibrary.setImgRootUrl(pic[i]);
						pictureLibrary.setImgUrl(pic[i]);
						pictureLibrary.setLinkId(visitors.getId());
						pictureLibrary.setMemo("备注");
						pictureLibrary.setName("景区图集");
						pictureLibrary.setPicDescribe("图片描述");
						pictureLibrary.setType(1);
						visitorsService.savePictureLibrary(pictureLibrary);
					}
				}
			}
				OpenDateList odl = new OpenDateList();
				odl.setId(GenerateSequenceUtil.getUniqueId());
				odl.setName(visitors.getName());
				odl.setTimeDetail(openDate);
				odl.setVisitorsId(visitorsId);
				odl.setState(0);
				odl.setStartValid(visitors.getStartValid());
				odl.setEndValid(visitors.getEndValid());
				visitorsService.saveOpenDateList(odl);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("图片上传失败-IllegalStateException",e);
		}
		if (typeId != null){
			return "redirect:/background/visitorsManage/toVisitorsManage?type="+typeId+"";
		}
			return "redirect:/background/visitorsManage/toVisitorsManage?type="+visitors.getType()+"";
	}
	/**
	 * Title:景点-修改主图
	 * @author wangfenglong
	 * @date 2016年9月2日
	 */
	@RequestMapping("/updateImg")
	@ResponseBody
	public String updateImg(@RequestParam("imageFile") CommonsMultipartFile[] files, Visitors visitors){
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
                    visitors.setHeadImg(img);
			if(visitors.getId() != null && visitors.getId() != 0){
				visitorsService.updateByPrimaryKeySelective(visitors);
			}else{
				visitors.setId(GenerateSequenceUtil.getUniqueId());
				visitors.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				visitorsService.insert(visitors);
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
     * 跳转到预览页面
     * @param id
     * @param model
     * @return
     */
	@RequestMapping("/showPage")
	public String showPage(Long id , ModelMap model){
		try {
			Visitors visitors = visitorsService.selectByPrimaryKey(id);
            HtmlText htmlText1 = htmlTextService.selectByPrimaryKey(visitors.getNoticeId());
            HtmlText htmlText2 = htmlTextService.selectByPrimaryKey(Long.parseLong(visitors.getDetailsId()));
            model.addAttribute("model", visitors);
            model.addAttribute("htmlText1",htmlText1);
            model.addAttribute("htmlText2",htmlText2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/background/visitors/showPage";
	}
	/**
	 * Title:跳转到添加页面
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/toAddPage")
	public String toAddPage(Integer type , ModelMap model){
		model.put("type",type);
		return "/background/visitors/toAddPage";
	}
	
	/**
	 * Title:跳转到修改页面
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/toEditPage")
	public String toEditPage(Long id , ModelMap model){
		try {
			Visitors visitors = visitorsService.selectByPrimaryKey(id);
            HtmlText htmlText1 = htmlTextService.selectByPrimaryKey(visitors.getNoticeId());
            HtmlText htmlText2 = htmlTextService.selectByPrimaryKey(Long.parseLong(visitors.getDetailsId()));
            OpenDateList odl = openDateListDao.selectOneOpenDateListByVisitorsId(visitors.getId());
            List<String> picUrl = visitorsService.selectPictureLibraryByVisitorsId(id);
            String pic = new String();
            for(int i=0;i<picUrl.size();i++){
            	if(i==0){
            		pic = picUrl.get(i);
            	}else{
            		pic = pic + "," +picUrl.get(i);
            	}
            }
            model.addAttribute("picUrl",picUrl);
            model.addAttribute("pic",pic);
            model.addAttribute("odl",odl);
            model.addAttribute("model", visitors);
            model.addAttribute("htmlText1",htmlText1);
            model.addAttribute("htmlText2",htmlText2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/background/visitors/toEditPage";
	}
	
	/**
	 * Title:景点 - 删除
	 * @author wangfenglong
	 * @date 2016年9月2日
	 */
	@RequestMapping("/deleteVisitors")
	@ResponseBody
	public Json deleteVisitors(Long id){
		Json json = new Json();
		try {
			visitorsService.deleteByPrimaryKey(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Title:设置总体满意度
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/manyidu")
	@ResponseBody
	public Json manyidu(Visitors visitors){
		Json json = new Json();
		try {
			visitorsService.updateByPrimaryKeySelective(visitors);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
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

}
