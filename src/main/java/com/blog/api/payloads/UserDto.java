package com.blog.api.payloads;



import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must be min of 4 char")
	private String name;
	
	@NotEmpty
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password must be betweeen 3-10 chars!!")
	//@Pattern(regexp=) //use for password pattern google it
	private String password;
	
	@NotNull
	private String about;
	
	
}