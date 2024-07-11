package com.dvl.SpringEcommer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvl.SpringEcommer.DTOs.OrderDetailDTO;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
	// thêm mới 1 oder detail
	
	@PostMapping
	public ResponseEntity<?> createOderDetail( 
			@Valid @RequestBody OrderDetailDTO newOrderDetailDTO ) {
		return ResponseEntity.ok("createOerDetail here"); 
		
	}
	@GetMapping("/id")
	public ResponseEntity<?> getOrderDetail(
			@Valid @PathVariable("id")Long id){
		return ResponseEntity.ok("getOrderDetail with id" + id);
	}
	@GetMapping("/order/{orderId}")
	public ResponseEntity<?> getOrderDetails(
			@Valid @PathVariable("orderId") Long orderId){
		return ResponseEntity.ok("getOrderDetails with orderId = " + orderId);
	}
	@PutMapping("path/{id}")
	public ResponseEntity<?> updateOrderDetail(
			@Valid @PathVariable("id") Long id,
			@RequestBody OrderDetailDTO newOrderDetailData
			){
		
		return ResponseEntity.ok("updateOrderDetail with id =" + id
				+"newOrderDeatailData: " +newOrderDetailData);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrderDetail(
			@Valid @PathVariable("id") Long id){
		return ResponseEntity.noContent().build();
	}
			
}
