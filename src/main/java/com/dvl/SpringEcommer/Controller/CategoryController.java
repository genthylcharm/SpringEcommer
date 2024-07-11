package com.dvl.SpringEcommer.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvl.SpringEcommer.DTOs.CategoryDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    // Hiển thị tất cả category với phân trang
    @GetMapping("") //http://localhost:8088/api/v1/categories
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit) {
        // Implement actual logic to fetch categories
        return ResponseEntity.ok(String.format("getAllCategories, page=%d, limit=%d", page, limit));
    }

    @PostMapping("")
    // Thêm mới category
    public ResponseEntity<Object> insertCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        // Implement actual logic to insert category
        return ResponseEntity.ok("Category inserted: " + categoryDTO);
    }

    @PutMapping("/{id}")
    // Cập nhật category theo id
    public ResponseEntity<Object> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        // Implement actual logic to update category
        return ResponseEntity.ok("Category updated with id " + id);
    }

    @DeleteMapping("/{id}")
    // Xóa category theo id
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        // Implement actual logic to delete category
        return ResponseEntity.ok("Category deleted with id = " + id);
    }
}
