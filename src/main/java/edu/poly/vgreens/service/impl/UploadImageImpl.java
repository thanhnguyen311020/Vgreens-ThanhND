package edu.poly.vgreens.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.poly.vgreens.service.UploadService;

@Service
public class UploadImageImpl implements UploadService {

	@Autowired
	ServletContext app;
	
	@Autowired
	Cloudinary cloud;

	@Override
	public File save(MultipartFile file, String folder) {
//		System.out.println("File:" + (System.currentTimeMillis()+file.getOriginalFilename()));
		
		// lấy folder ==> app.getRealPath là đổi đường dẫn ảo sang đường dẫn thực
		File dir = new File(app.getRealPath("/upload/" + folder));
		// tạo mới nếu chưa tồn tại
		if (!dir.exists()) {
			dir.mkdirs();
		}
		 // tên file upload từ client + vs thời gian hiện tại khi upload ảnh lên ==> tên duy nhất
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		System.out.println("S:"+ s);
		//tên file = tên duy nhât lấy hascode đổi về hex hệ 16 + trích lấy phần duôi từ dấu chấm cuối cùng (định dạng)
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		System.out.println("Name: "+ name);
		try {
			// lưu file vào folder
			File saveFile = new File(dir, name);	
			file.transferTo(saveFile);
			System.out.println(saveFile.getAbsolutePath());
			return saveFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public String uploadImage(String imgPath) {
		String img = "";
		try {
			File file = new File(imgPath);
			System.out.println("file: "+ file);
			Map r = cloud.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
			img = ((String) r.get("secure_url"));
//			entity.setImage(img);
			deleteAllFileUpload(file);
			System.out.println("img link "+ img);
			return img;
		} catch (IOException e) {
			System.err.println("=====Error=======" + e.getMessage());
			return img;
		}
	
		
	}
	
	private void deleteAllFileUpload(File file) {
		String pathImage = file.toString();
		String pathFolder = pathImage.substring(0, pathImage.lastIndexOf('\\'));
		try {
			FileUtils.cleanDirectory( new File(pathFolder));
		} catch (IOException e) {
			System.err.println("=======Error========="+ e.getMessage());
			e.printStackTrace();
		}
	}

}
