package com.blog.api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.config.AppConstants;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;
import com.blog.api.services.FileService;
import com.blog.api.services.PostService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class PostControllers {

	
	@Autowired
	private FileService fileService;

	@Value("${project.post.image}")
	private String path;
	
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
	
	//http://localhost:9192/api/posts?pageNumber=2&pageSize=3&sortBy=
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue=AppConstants.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue=AppConstants.SORT_BY, required=false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue=AppConstants.SORT_DIRECTION, required=false) String sortDirection
			){

		PostResponse postsResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDirection);
		
		return new ResponseEntity<PostResponse>(postsResponse,HttpStatus.OK);
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
	
	@GetMapping("/posts/search/{keywors}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keywords") String keywords
			){
		
		List<PostDto> result = this.postService.getSearchPost(keywords);
		
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
	
	


	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId
			) throws IOException {
		
		
		PostDto postdto = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
			
			postdto.setPostImageName(fileName);
			PostDto updatepostdto = this.postService.updatePost(postdto, postId);

		
		return new ResponseEntity<PostDto>(updatepostdto, HttpStatus.OK);

	}
	
	//Method for serve image file
	
	@GetMapping("/post/image/get/{imageName}" )
	public void downloadimage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getImageResourse(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
