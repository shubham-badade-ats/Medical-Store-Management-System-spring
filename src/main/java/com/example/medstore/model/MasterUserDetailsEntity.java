package com.example.medstore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ats_wms_master_user_details")
public class MasterUserDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "FIRST_NAME")
	private String firstName;


	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;



	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;

//	@Column(name = "CDATETIME")
//	private LocalDateTime cDatetime=LocalDateTime.now();

	@Column(name = "USER_IS_DELETED")
	private int userIsDeleted;
	
	@Transient
	protected String jwtToken;

	@Column(name = "RESET_PASSWORD")
	private String resetToken;

	@Column(name = "RESET_TOKEN_EXPIRATION")
	private LocalDateTime resetTokenExpiration;

}
