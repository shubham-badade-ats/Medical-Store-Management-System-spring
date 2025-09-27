package com.example.medstore.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.medstore.model.MasterUserDetailsEntity;



public interface MasterUserDetailsRepository extends JpaRepository<MasterUserDetailsEntity, Integer>{
	
	List<MasterUserDetailsEntity> findByuserIsDeleted(int i);

	List<MasterUserDetailsEntity> findByuserName(String userName);
	
	
	

	List<MasterUserDetailsEntity> findByEmailId(String emailID);

	MasterUserDetailsEntity findByUserIdAndUserName(int userId, String userName);

	MasterUserDetailsEntity findByUserId(int userId);

	MasterUserDetailsEntity findByResetTokenAndUserName(String resetToken,String userNameString);

	List<MasterUserDetailsEntity> findByUserIsDeleted(int i);
	
	MasterUserDetailsEntity  findByResetToken(String resetToken);
	
	
	
	    @Modifying
	    @Query("UPDATE MasterUserDetailsEntity  t SET t.resetToken = null, t.resetTokenExpiration = null WHERE t.resetTokenExpiration < :currentTime")
	    void resetExpiredTokens(@Param("currentTime") LocalDateTime currentTime);
	

	void save(Optional<MasterUserDetailsEntity> masteruser);
	
	
	
	

}
