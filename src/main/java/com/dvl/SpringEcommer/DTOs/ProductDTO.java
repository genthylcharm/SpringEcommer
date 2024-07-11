package com.dvl.SpringEcommer.DTOs;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	 @NotBlank(message = "Title is required")
	    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
	    private String name;

	    @Min(value = 0, message = "Price must be greater than or equal to 0")
	    @Max(value = 10000000, message = "Price must be less than or equal to 10,000,000")
	    private Float price;

	    private String thumbnail;

	    private String description;

	    @JsonProperty("category_id")
	    private Long categoryId;
	    private List<MultipartFile> files;
		public ProductDTO(
				@NotBlank(message = "Title is required") @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters") String name,
				@Min(value = 0, message = "Price must be greater than or equal to 0") @Max(value = 10000000, message = "Price must be less than or equal to 10,000,000") Float price,
				String thumbnail, String description, Long categoryId, MultipartFile file) {
			super();
			this.name = name;
			this.price = price;
			this.thumbnail = thumbnail;
			this.description = description;
			this.categoryId = categoryId;
			this.files = files;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Float getPrice() {
			return price;
		}
		public void setPrice(Float price) {
			this.price = price;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public List<MultipartFile> getFiles() {
			return files;
		}
		public void setFiles(List<MultipartFile> files) {
			this.files = files;
		}
	
	    
}
