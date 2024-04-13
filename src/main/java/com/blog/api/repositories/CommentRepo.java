package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
