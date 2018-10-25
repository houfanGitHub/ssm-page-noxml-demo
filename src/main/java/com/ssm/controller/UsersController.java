package com.ssm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Users;
import com.ssm.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userServie;
	
	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "addUser";
	}
	
	@RequestMapping("/toUpdateUser/{uid}")
	public String toUpdateUser(@PathVariable("uid")Integer uid,Map<String,Object> map) {
		if(uid != null) {
			try {
				Users users = userServie.findOne(uid);
				map.put("user", users);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "updateUser";
	}
	
	@RequestMapping("/userinfo")
	public String user(Map<String,Object> map,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="username",required=false)String username,
			@RequestParam(value="pageNum",required=false,defaultValue="1")Integer pageNum) {
		try {
			PageInfo<Users> findAll = userServie.findAll(name,username,pageNum);
			map.put("pageInfo", findAll);
			map.put("name", name);
			map.put("username", username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userinfo";
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(Users users) {
		if(users != null) {
			try {
				userServie.addUsers(users);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			return "not null";
		}
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(Users users) {
		if(users != null) {
			try {
				userServie.updateUsers(users);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			return "not null";
		}
	}
	
	@RequestMapping("/deleteUser/{uid}")
	@ResponseBody
	public String deleteUser(@PathVariable("uid")Integer uid) {
		if(uid != null) {
			try {
				userServie.deleteUsers(uid);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			return "not null";
		}
	}
}
