package com.blog.api.payloads;

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

	private String categoryTitle;
	
	private String categoryDescription;
}
