package com.dvl.SpringEcommer.Controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.antlr.v4.runtime.misc.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.dvl.SpringEcommer.DTOs.ProductDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
	
	
	@PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)// Post http://localhost:8088/v1/api/products
	
	public ResponseEntity<?> createProduct(
			@Valid @RequestBody ProductDTO productDTO,
			// @RequestPart("file") MultipartFile file,
			BindingResult result){
		try{
			if(result.hasErrors()) {
				List<String> erroMessages = result.getFieldErrors()
						.stream()
						.map(FieldError::getDefaultMessage)
						.toList();
				return ResponseEntity.badRequest().body(erroMessages);
			}
			List<MultipartFile> files =productDTO.getFiles();
			for(MultipartFile file : files) {
				if(file.getSize() == 0) {
					continue;
				}
				
					// Kiểm tra kích thước file và định dạng
					if(file.getSize() > 10 * 1024 * 1024) { 
						// Kích thước > 10MB
						return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
								.body("file is larger! maxium size is 10MB");
					
					}
					String contentType =file.getContentType();
					  if(contentType == null || !contentType.startsWith("image/")) {
		                  return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
		                        .body("File must be an image");
					  }
					  //lưu file và cập nhật thumbail trong DTO
					  String filename = storeFile(file); // thay thế hàm này với code của bạn để lưu file
	
			}
			
			
				return ResponseEntity.ok("Product create successfully");
			
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	private String storeFile(MultipartFile file) throws IOException{
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		// Thêm UUID vào trước tên file để đảm bảo tên file là duy nhấtl
		String uniqueFilename =UUID.randomUUID().toString()+ "_"+filename; 
		// Đường dẫn đến thu mụcmà bạn muốn lưu file
		java.nio.file.Path uploadDir =Paths.get("upload");
		// kiểm tra và tạo thư mục nếu nó không tồn tại
		if(!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		// Đường dẫn đầy đủ của file
		java.nio.file. Path destination = Paths.get(uploadDir.toString(),uniqueFilename);
		// sao chep file vao thu muc dich
		Files.copy(file.getInputStream(),destination,StandardCopyOption.REPLACE_EXISTING);
		return uniqueFilename;
	}
	
	@GetMapping("")
	public ResponseEntity<String> getProduct(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit) 
	{
        
        return ResponseEntity.ok("getProduct here");
	}
	
// http://localhost:8088/api/v1/products/6
	@GetMapping("/{id}")
	public ResponseEntity<String> getProductById(@PathVariable("id") String productId){
		return ResponseEntity.ok("Product with ID:"+productId);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {
	    return ResponseEntity.ok(String.format("Product with id = %d deleted successfully", id));
}
}
