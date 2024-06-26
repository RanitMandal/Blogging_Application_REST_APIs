package com.blog.api.serviceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entites.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.UserDto;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.UserService;


@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.UserDtoToUser(userDto);
		User saveUser = this.userRepo.save(user);
		return this.userToUserDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		
		return this.userToUserDto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

		
		
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users= this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		this.userRepo.delete(user);
		
	}
	
	
	public User UserDtoToUser(UserDto userDto) {
		User user = this.modelmapper.map(userDto, User.class);
		
		// for use modelmapper
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelmapper.map(user, UserDto.class);
		
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}
}
