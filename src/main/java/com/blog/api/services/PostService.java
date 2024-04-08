package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.PostDto;

public interface PostService {

	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);

	List<PostDto> getAllPost();

	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> getSearchPost(String keyword);
	
	
	
	
}
