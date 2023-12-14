package com.example.demo.service;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.UserDTO;

public interface UserService {
	
	  String addUser(UserDTO userDTO);
	  LoginResponse loginUser(LoginDTO loginDTO);

}
