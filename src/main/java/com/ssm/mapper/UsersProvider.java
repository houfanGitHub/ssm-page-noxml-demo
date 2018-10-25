package com.ssm.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Users;

public class UsersProvider {

	public String findAll(Users users) {
		return new SQL() {
			{
				SELECT("*");
				FROM("USERS");
				if(users.getName() != null && !users.getName().trim().equals("")) {
					WHERE("and name like concat('%','#{name}','%')");
				}
				if(users.getUsername() != null && !users.getUsername().trim().equals("")) {
					WHERE("and username like concat('%','#{username}','%')");
				}
			}
		}.toString();
	}
	
	public String updateByPrimaryKeySelective(Users users) {
		return new SQL() {
			{
				UPDATE("USERS");
				if(users.getName() != null && !users.getName().trim().equals("")) {
					SET("name = #{name}");
				}
				if(users.getPassword() != null && !users.getPassword().trim().equals("")) {
					SET("password = #{password}");
				}
				if(users.getGender() != null && !users.getGender().trim().equals("")) {
					SET("gender = #{gender}");
				}
				if(users.getAge() != null) {
					SET("age = #{age}");
				}
                WHERE("uid = #{uid}");
			}
		}.toString();
	}
}
