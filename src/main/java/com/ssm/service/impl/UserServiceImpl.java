package com.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.mapper.UsersMapper;
import com.ssm.pojo.Users;
import com.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public PageInfo<Users> findAll(String name,String username,Integer pageNum) {
		Integer pageSize = 5;
		PageHelper.startPage(pageNum, pageSize);
		Users users = new Users();
		users.setName(name);
		users.setUsername(username);
		List<Users> list = usersMapper.findAll(users);
		PageInfo<Users> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void addUsers(Users users) {
		users.setLocked(0);
		users.setCreatedate(new Date());
		users.setLastlogindate(null);
		usersMapper.insert(users);
	}

	@Override
	public void updateUsers(Users users) {
		usersMapper.updateByPrimaryKeySelective(users);
	}

	@Override
	public void deleteUsers(Integer uid) {
		usersMapper.deleteByPrimaryKey(uid);
	}

	@Override
	public Users findOne(Integer uid) {
		Users users = usersMapper.selectByPrimaryKey(uid);
		return users;
	}

}
