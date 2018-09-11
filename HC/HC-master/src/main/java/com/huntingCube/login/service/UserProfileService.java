package com.huntingCube.login.service;

import com.huntingCube.login.model.UserProfile;

import java.util.List;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
