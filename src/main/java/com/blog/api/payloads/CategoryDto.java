package com.blog.api.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//lombork
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=4, message="Category Title Should be Minimum 4 Chars")
	private String categoryTitle;
	
	
	@NotEmpty
	@Size(min=10, message="Category Description Should be Minimum 10 Chars")
	private String categoryDescription;
}
