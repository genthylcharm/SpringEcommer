package com.dvl.SpringEcommer.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvl.SpringEcommer.DTOs.OrderDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {
	
		@PostMapping("")
		public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDTO orderDTO ,BindingResult result){
			try {
				if(result.hasErrors()) {
					List<String> erromessages = result.getFieldErrors()
							.stream()
							.map(FieldError :: getDefaultMessage)
							.toList();
					return ResponseEntity.badRequest().body(erromessages);
				}
				return ResponseEntity.ok("createOrder succesfully");
			}catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		@GetMapping("/{user_id}") // thêm biến đường dẫn "uer_id"
		// Get http://localhost:8088/api/v1/orders/4
		public ResponseEntity<?> getOrders(@Valid @PathVariable("user_id") Long userId){
		try {
			return ResponseEntity.ok("Lấy danh sách order từ User_id");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		}
		@PutMapping("/{id}")
		// công việc cua admin
		// Put http://localhost:8088/api/v1/order/2
		public 	ResponseEntity<?> updateOrder(
				@Valid @PathVariable long id,
				@Valid @RequestBody OrderDTO orderDTO){
			return ResponseEntity.ok(" Cập nhật thong tin 1 order");
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteOrder(@Valid @PathVariable Long id){
			// cập nhât active =false
			return ResponseEntity.ok("Order deleted successfully");
		}

}
