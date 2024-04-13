package com.blog.api.payloads;

import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//lombork
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;
	private String postTitle;
	private String postContent;
	private String postImageName;
	private String postDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments = new HashSet<>();	

}
