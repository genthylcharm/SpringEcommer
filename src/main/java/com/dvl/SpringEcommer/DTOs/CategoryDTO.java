package com.dvl.SpringEcommer.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {
	@NotEmpty(message = "Category's  name not empty")
private String name;
}
