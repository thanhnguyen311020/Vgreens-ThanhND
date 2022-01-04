package edu.poly.vgreens.restcontroller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.poly.vgreens.service.UploadService;

@RestController
@CrossOrigin("*")
public class UploadImageRestController {

	@Autowired
	UploadService uploadService;
	@Autowired
	Cloudinary cloudinary;

	@PostMapping("/vgreens/rest/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		File saveFile = uploadService.save(file, folder);
		System.out.println(saveFile.getPath());
		node.put("img", saveFile.getAbsolutePath());
		node.put("name", saveFile.getName());
		node.put("size", saveFile.length());
		return node;
//		try {
////			Map r =  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
////			String img = (String) r.get("secure_url");
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("=====Error=======" + e.getMessage());
//			return node;
//			
//		}
	
	}
}
