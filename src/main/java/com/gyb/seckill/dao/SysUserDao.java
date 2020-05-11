package com.gyb.seckill.dao;

import com.gyb.seckill.entity.SysUser;
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
public interface SysUserDao {
    @Select("select id,nick_name as nickName,password,password_salt as passwordSalt," +
            "create_date as createDate" +
            " from sys_user where phone=#{phone}")
    SysUser findByPhone(String phone);

    @Select("select count(*) from sys_user where phone=#{phone}")
    int countByPhone(String phone);

    @Select("select count(*) from sys_user where nick_name=#{nickName}")
    int countByNickName(String nickName);

    @Insert("insert into sys_user(nick_name,phone,age,password,password_salt,create_date) " +
            "value(#{nickName},#{phone},#{age},#{password},#{passwordSalt},#{createDate})")
    int save(SysUser user);

}
