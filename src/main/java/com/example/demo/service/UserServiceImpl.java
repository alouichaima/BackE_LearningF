package com.example.demo.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	/*@Override
	public String addUser(UserDTO userDTO) {
		   User user = new User(
				   userDTO.getId(),
				   userDTO.getNom(),
				   userDTO.getPrenom(),
				   userDTO.getDatenaiss(),
				   userDTO.getEmail(),
	                this.passwordEncoder.encode(userDTO.getPassword()),
	                userDTO.getTelephone(),
	                userDTO.getPhoto()
 
	        );
		   userrepo.save(user);
	        return user.getNom();
	}

	UserDTO userDTO;*/
	@Override
	public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userrepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userrepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }

	@Override
	public String addUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
