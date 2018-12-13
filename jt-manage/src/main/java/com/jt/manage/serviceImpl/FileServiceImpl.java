package com.jt.manage.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Service
public class FileServiceImpl implements FileService {
   private String dirPath = "D:/jt-upload/";
   private String urlPath = "http://image.jt.com/";
	@Override
	public PicUploadResult uploadFile(MultipartFile uploadFile) {
		PicUploadResult result = new PicUploadResult();
		//1.校验文件名判断是否是图片文件,使用正则表达式校验文件名格式
		String filename = uploadFile.getOriginalFilename();
		filename.toLowerCase();//将名字转为小写
		if(!filename.matches("^.*\\.(jpg|png|gif)$")){
			result.setError(1);
			return result;	
		}		
		//2.判断是否恶意程序，伪装后缀名
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if(width==0||height==0){
				result.setError(1);//不是图片
				return result;
			}
			String dateDir = 
					new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			//3随机获取文件名，避免重复,
			String uuid = UUID.randomUUID().toString().replace("-", "");
			int andom = new Random().nextInt(1000);
			String fileType = filename.substring(filename.lastIndexOf("."));
			String uuidfilename = uuid+andom+fileType;
			//定义文件夹路径
			String fileDirPath = dirPath + dateDir ;
			File fileDir = new File(fileDirPath);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			File realFilePath = new File(fileDirPath +"/" + uuidfilename);
			uploadFile.transferTo(realFilePath);
			result.setHeight(height+"");
			result.setWidth(width+"");
			//实现图片的页面访问。拼接虚拟路径
			String realurlPath = urlPath + dateDir +"/" + uuidfilename;
			result.setUrl(realurlPath);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return result;
	}

}
