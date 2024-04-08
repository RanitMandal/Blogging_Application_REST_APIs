package com.blog.api.payloads;

import com.blog.api.entites.Category;
import com.blog.api.entites.User;

public class PostDto {

	private String postTitle;
	private String postContent;
	private String postImageName;
	private String postDate;
	private CategoryDto categoryDto;
	private UserDto userDto;
}
