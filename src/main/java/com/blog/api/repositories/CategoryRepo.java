package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entites.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

	
}
