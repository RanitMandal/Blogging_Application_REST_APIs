package com.blog.api.entites;

import com.blog.api.payloads.CategoryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name="post")
//lombork
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title",length=100,nullable=false)
	private String postTitle;
	
	
	private String postContent;
	private String postImageName;
	private String postDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	
	
	
	

}
