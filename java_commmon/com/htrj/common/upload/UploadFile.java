package com.htrj.common.upload;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class UploadFile {
	
	private final static String TEMPCONTEXTURL_ALL = "https://www.cfdzhsd.com/cfdScenic/";
	
	public List<Map<String,String>> uploadImage(@RequestParam("imageFile") MultipartFile[] imageFiles,String userId,HttpServletRequest request,HttpServletResponse response)
	{
		// 判断文件是否为空  
		//判断file数组不能为空并且长度大于0  
		List<Map<String,String>> list=new ArrayList<>();
//		String[] resultUrls=new String[imageFiles.length];
        if(imageFiles!=null&&imageFiles.length>0){  
            //循环获取file数组中得文件  
            for(int i = 0;i<imageFiles.length;i++){  
                MultipartFile file = imageFiles[i];  
                //保存文件  
                Map<String,String> map= saveFile(file,request,response,userId);  
                list.add(map);
             //  resultUrls[i]=resultUrl;
               
            }  
        }  
		return list;
	}

	
	
    private Map<String,String> saveFile(MultipartFile file,HttpServletRequest request,HttpServletResponse response,String userId) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	
            	String uuid = UUID.randomUUID().toString();
                uuid=uuid.substring(0, 8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
            	
            	String time=new Date().getTime()+""+ uuid;

            	Map<String,String> map=new HashMap<>();
            	
                // 文件保存路径  
                String filePath = request.getSession().getServletContext().getRealPath("/") + "scripts/upload/" +userId+"/" +time
                        + file.getOriginalFilename();  
                StringBuffer url = request.getRequestURL();  
                String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
                String resultUrl=tempContextUrl+"scripts/upload/" +userId+"/" +time
                		+ file.getOriginalFilename();
                map.put("contextUrl", resultUrl);
                map.put("rootUrl", filePath);
                
                File file1 = new File(request.getSession().getServletContext().getRealPath("/") + "scripts/upload/"+userId);
                String asdf = request.getSession().getServletContext().getRealPath("/") + "scripts/upload/"+userId;
                System.out.println(asdf);
	              //如果文件夹不存在则创建    
	              if  (!file1.exists()  && !file1.isDirectory())      
	              {       
	                  System.out.println("//不存在");  
	                  file1.mkdir();    
	              } else   
	              {  
	                  System.out.println("//目录存在");  
	              }
                
                // 转存文件  
                file.transferTo(new File(filePath));  
                return map;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    } 
    
    
  //html文件上传--返回文件地址

  	    public  String saveHtml(HttpServletRequest request, HttpServletResponse response,String activityDetail){  
  	      try {
				 //取域名
				StringBuffer url = request.getRequestURL();  
//				String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
				String tempContextUrl = TEMPCONTEXTURL_ALL;
				String time = new Date().getTime()+"";
			/*	
				String html = "<!DOCTYPE html><html><head><title>/title>"
				+ "<meta content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\" name=\"viewport\">"
				+ "<meta name=\"keywords\" content=\"keyword1,keyword2,keyword3\"><meta name=\"content-type\" content=\"text/html; charset=UTF-8\">"
				+"<meta name=\"format-detection\" content=\"telephone=no\">"
				+"<meta http-equiv=\"x-rim-auto-match\" content=\"none\">"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"/wxstore/resources/styles/mobile.css\" />"
				+ "</head><body>"+ activityDetail +"</body></html>";*/
				
				
				
				
				
				String html="<!DOCTYPE html>"+
                            "<html>"+
                            "	<head>"+
                            "		<meta charset=\"utf-8\">"+
                            "		<title>新闻须知详情页</title>"+
                            "		<script src=\"../ueditor/flexible.js\" type=\"text/javascript\" charset=\"utf-8\"></script>"+
                            "		<link rel=\"stylesheet\" type=\"text/css\" href=\"../../styles/sysHtkj/style.css\"/>"+
                            "<style>.wenzi-narration p img{width:100%;height:auto}</style>"+
                            "	</head>"+
                            "	<body>"+
                            "		<div class=\"news\">"+
                            "			<div class=\"xuzhi\">"+
                            "				<div class=\"contan-hd\">"+
//                            "				<p><span class=\"xx-left\"><i></i></span><span class=\"xx-right\"><i></i></span></p>"+
//                            "				<p><span class=\"xx-left\"><i></i></span>内容介绍<span class=\"xx-right\"><i></i></span></p>"+
                            "			</div>"+
                            "			<div class=\"xb\">"+
//                            "				<p><img src=\"../../images/jq12-tubiao1.png\"/></p>"+
                            "			</div>"+
                            "			<div class=\"wenzi-narration\">"+

                                        activityDetail+

                            "			</div>"+
                            "			</div>"+
                            "		</div>"+
                            "	</body>"+
                            "</html>";

				String addressRootPath = request.getServletContext().getRealPath( "/" );
				String addressPath = tempContextUrl ;
				addressRootPath += "scripts/upload/";
				addressPath += "scripts/upload/";
				addressRootPath += time;
				addressPath += time;
				String address = addressRootPath + "travel.html";
				String outAddress = addressPath + "travel.html";
				
				FileWriter fw = null;
				File f = new File(address);
                System.out.println(address+"=============================================");

				if(!f.exists()){
					f.createNewFile();
					}
				fw = new FileWriter(f);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(address)), "UTF-8"));
                out.write(html, 0, html.length()-1);
				out.close();
				return outAddress;
  	        } catch (IOException e) {
  	        	e.printStackTrace();
  	        	return null;
  	        }
  	        

  	      
  	        
  	    }

  	    
  	    	
  	
  	
  	
    
    
}
