package com.example.medstore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.medstore.model.MasterUserDetailsEntity;




@Service
public interface MasterUserDetailsService {

	List<MasterUserDetailsEntity> findAllMasterUserDetails(MasterUserDetailsEntity masterUserDetailsEntity);

	MasterUserDetailsEntity findMasterUserDetailsByid(int userId);

	ResponseEntity<Object> updateMasterUserDetails(int userId,  String firstName,  String userName, String contactNumber, String emailId,  String roleName);

	ResponseEntity<Object> deleteMasterUserDetails(Integer userId);

	ResponseEntity<Object> createMasterUserDetails( String firstName,  String userName, String userPassword, String contactNumber, String emailId, 
			String roleName);

	
	

}
