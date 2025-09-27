package com.example.medstore.controller;

import java.util.Base64;
import java.util.List;
import java.util.Base64;
import java.util.List;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medstore.model.MasterUserDetailsEntity;
import com.example.medstore.repository.MasterUserDetailsRepository;
import com.example.medstore.security.JwtUtil;
import com.example.medstore.security.MyUserDetailsService;






@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MasterUserDetailsRepository userManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/authenticate")
	public ResponseEntity<MasterUserDetailsEntity> createAuthenticationToken(
	        @RequestParam(value = "userName") String userName,
	        @RequestParam(value = "userPassword") String userPassword) throws Exception {

	    System.out.println("request username: " + userName);

	    List<MasterUserDetailsEntity> userList = userManager.findByuserName(userName);
	    System.out.println("userlist: " + userList.size());

	    if (userList != null && !userList.isEmpty() && userList.get(0).getUserIsDeleted() == 0) {

	        if (userPassword.equals(userList.get(0).getUserPassword())) {

	            final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
	            final String jwt = jwtTokenUtil.generateToken(userDetails);

	            MasterUserDetailsEntity user = userList.get(0);
	            user.setJwtToken(jwt);

	            // Encode user image if exists using java.util.Base64 (Java 17+)
//	            if (user.getUserImage() != null) {
//	                user.setUserPhotoImageIn64Base(
//	                        "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(user.getUserImage()));
//	            }

	            return new ResponseEntity<>(user, HttpStatus.OK);

	        } else {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


}