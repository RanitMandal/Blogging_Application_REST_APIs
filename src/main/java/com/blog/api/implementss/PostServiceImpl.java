package com.blog.api.implementss;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entites.Category;
import com.blog.api.entites.Post;
import com.blog.api.entites.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CategoryDto;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.UserDto;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.repositories.PostRepo;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));	
		Category ctegory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));	
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setCategory(ctegory);
		post.setUser(user);
		
		
		Post createPost = this.postRepo.save(post);

		return this.modelMapper.map(createPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post postupdate = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
		//postupdate.setCategory(postDto.getCategory());
		postupdate.setPostContent(postDto.getPostContent());
		postupdate.setPostImageName(postDto.getPostImageName());
		postupdate.setPostTitle(postDto.getPostTitle());
		postupdate.setPostDate(postDto.getPostDate());
		
		Post udatedPost = this.postRepo.save(postupdate);
		
		return this.modelMapper.map(udatedPost, PostDto.class);
	}

	
	
	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
		this.postRepo.delete(post);

	}

	@Override
	public List<PostDto> getAllPost() {
		
		List<Post> posts= this.postRepo.findAll();
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Optional<Post> post= this.postRepo.findById(postId);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category ctegory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));	
		List<Post> posts = this.postRepo.findByCategory(ctegory);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));	
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		
		return postDtos;
	}

	@Override
	public List<PostDto> getSearchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
