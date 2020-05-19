package com.gyb.seckill.dao;

import com.gyb.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author geng
 * 2020/4/19
 */
@Mapper
@Repository
public interface UserDao {
    @Select("select id,nick_name as nickName,password,password_salt as passwordSalt," +
            "create_date as createDate" +
            " from user where phone=#{phone}")
    User findByPhone(String phone);

    @Select("select id,nick_name as nickName,password,password_salt as passwordSalt," +
            "create_date as createDate" +
            " from user where nick_name=#{userName}")
    User findByUserName(String userName);

    @Select("select id,nick_name as nickName,password,password_salt as passwordSalt," +
            "create_date as createDate" +
            " from user where email=#{email}")
    User findByEmail(String email);

    @Select("select count(*) from user where phone=#{phone}")
    int countByPhone(String phone);

    @Select("select count(*) from user where nick_name=#{nickName}")
    int countByNickName(String nickName);

    @Insert("insert into user(nick_name,phone,age,password,password_salt,create_date) " +
            "value(#{nickName},#{phone},#{age},#{password},#{passwordSalt},#{createDate})")
    int save(User user);

}
