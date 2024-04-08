package com.blog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.CategoryDto;
import com.blog.api.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryContrillers {
	
	@Autowired
	private CategoryService categoryService;
	
	//POST - Create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdcatDto = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createdcatDto,HttpStatus.CREATED);
	}
	
	//PUT - Update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(
			@Valid
			@RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Integer categotyId ){
		
		CategoryDto uypdatedcatDto = this.categoryService.updateCategory(categoryDto,categotyId);
		
		return new ResponseEntity<CategoryDto>(uypdatedcatDto,HttpStatus.ACCEPTED);
	}
	
	//DELETE - Delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> DeleteCategory(@PathVariable("categoryId") Integer categotyId ){
		
		this.categoryService.deleteCategory(categotyId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfilly !!",true),HttpStatus.OK);
	}
	
	//GET - Get All Categories
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> catDto = this.categoryService.getAllCategorys();
		
		return new ResponseEntity<List<CategoryDto>>(catDto,HttpStatus.OK);
	}
	
	//GET - Get Single Categories
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categotyId ){
		
		CategoryDto catDto = this.categoryService.getSingleCategory(categotyId);
		
		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
	}
}
