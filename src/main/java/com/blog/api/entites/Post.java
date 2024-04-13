package com.blog.api.entites;

import java.util.HashSet;
import java.util.Set;

import com.blog.api.payloads.CategoryDto;
import com.blog.api.payloads.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@OneToMany(mappedBy="post", cascade= CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();	
	

}
