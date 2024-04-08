package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.PostDto;

public interface PostService {

	
	PostDto createPost(PostDto postDto);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	PostDto deletePost(PostDto postDto, Integer postId);

	List<PostDto> getAllPost(PostDto postDto, Integer postId);

	PostDto getSinglePost(PostDto postDto, Integer postId);

	
	
}
