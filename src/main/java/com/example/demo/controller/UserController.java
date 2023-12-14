package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.LoginResponse;
import com.example.demo.service.UserServiceImpl;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

    
	/*@GetMapping("/retrieveAllUsers")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public List<User> retrieveAllUsers() {

		return userRepository.findAll();
	}*/

    /*@PostMapping("/save")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }*/
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la connexion.");
        }
    }
    
    @PostMapping("/signupAdmin")
	public ResponseEntity<?> registerAdmin(@Validated @RequestBody UserDTO userDTO) {
		if (userRepository.existsByEmail(userDTO.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: email is already taken!"));
		}

		User user = new User(userDTO.getNom(), userDTO.getPrenom(),
				 userDTO.getDatenaiss(), userDTO.getEmail(),encoder.encode(userDTO.getPassword()),
				userDTO.getTelephone());

		Set<String> strRoles = userDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role apprenantRole = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Erreur : Rôle 'ADMIN' introuvable."));
            roles.add(apprenantRole);
        } else {
            for (String roleName : strRoles) {
                Role role = roleRepository.findByName(ERole.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Erreur : Rôle introuvable."));
                roles.add(role);
            }
        }
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
    }
    
    @PostMapping("/signupFormateur")
	public ResponseEntity<?> registerFormateur(@Validated @RequestBody UserDTO userDTO) {
	

		if (userRepository.existsByEmail(userDTO.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user =new User(userDTO.getNom(), userDTO.getPrenom(),
				 userDTO.getDatenaiss(), userDTO.getEmail(),encoder.encode(userDTO.getPassword()),
				userDTO.getTelephone());

		Set<String> strRoles = userDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role apprenantRole = roleRepository.findByName(ERole.FORMATEUR)
                    .orElseThrow(() -> new RuntimeException("Erreur : Rôle 'FORMATEUR' introuvable."));
            roles.add(apprenantRole);
        } else {
            for (String roleName : strRoles) {
                Role role = roleRepository.findByName(ERole.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Erreur : Rôle introuvable."));
                roles.add(role);
            }
        }

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Formateur registered successfully!"));
	}
    
    @PostMapping("/signupApprenant")
    public ResponseEntity<?> registerEtudiant(@Validated @RequestBody UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(userDTO.getNom(), userDTO.getPrenom(),
                userDTO.getDatenaiss(), userDTO.getEmail(), encoder.encode(userDTO.getPassword()),
                userDTO.getTelephone());

        Set<String> strRoles = userDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role apprenantRole = roleRepository.findByName(ERole.APPRENANT)
                    .orElseThrow(() -> new RuntimeException("Erreur : Rôle 'APPRENANT' introuvable."));
            roles.add(apprenantRole);
        } else {
            for (String roleName : strRoles) {
                Role role = roleRepository.findByName(ERole.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Erreur : Rôle introuvable."));
                roles.add(role);
            }
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Apprenant registered successfully!"));
    }

   
}