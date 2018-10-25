package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.ssm.pojo.Users;

@Repository
public interface UsersMapper {
	
    @Delete(value="delete from users where uid = #{uid}")
    int deleteByPrimaryKey(Integer uid);

    @Insert("insert into users(name,username,password,gender,age,createdate,lastlogindate,locked) "
    		+ "value(#{name},#{username},#{password},#{gender},#{age},#{createdate},#{lastlogindate},#{locked})")
    int insert(Users record);

    @Select("select * from users where uid = #{uid}")
    Users selectByPrimaryKey(Integer uid);

    @UpdateProvider(type=UsersProvider.class,method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Users record);


    @SelectProvider(type=UsersProvider.class,method="findAll")
    List<Users> findAll(Users users);
}