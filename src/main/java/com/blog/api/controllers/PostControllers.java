package com.blog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostDto;
import com.blog.api.services.PostService;

@RestController
@RequestMapping("/api")
public class PostControllers {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId){
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer userId){
		
		List<PostDto> posts = this.postService.getPostByUser(userId);
		
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

	}
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") Integer categoryId){
		
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){

		List<PostDto> posts = this.postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getAllPost(@PathVariable("postId") Integer postId){
		
		PostDto posts = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatedPost(
			@RequestBody PostDto postDto,
			@PathVariable("postId") Integer postId){
		
		PostDto post = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);

	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){
		
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post SuccesFully Deleted", true),HttpStatus.OK);
		
	}
	
}
