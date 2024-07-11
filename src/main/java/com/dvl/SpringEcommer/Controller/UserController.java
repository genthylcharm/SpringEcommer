package com.dvl.SpringEcommer.Controller;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvl.SpringEcommer.DTOs.UserDTO;
import com.dvl.SpringEcommer.DTOs.UserLoginDTO;

import jakarta.validation.Valid;


@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO,BindingResult result){
		try {
			if(result.hasErrors()) {
				List<String> erromessages = result.getFieldErrors()
						.stream()
						.map(FieldError :: getDefaultMessage)
						.toList();
				return ResponseEntity.badRequest().body(erromessages);
			}
			return ResponseEntity.ok("Register successfully");
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
@PostMapping("/login")
public ResponseEntity<String> login(
		@Valid @RequestBody UserLoginDTO userLoginDTO ){
	//  kiểm tra thông tin đăng nhập
	// trả về token trong respon
	return ResponseEntity.ok("some token");
	
}
}