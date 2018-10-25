package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Users;

public interface UserService {

	public PageInfo<Users> findAll(String name,String username,Integer pageNum);
	
	void addUsers(Users users);
	
	void updateUsers(Users users);
	
	void deleteUsers(Integer uid);

	Users findOne(Integer uid);
}
