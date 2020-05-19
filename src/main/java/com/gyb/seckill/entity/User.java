package com.gyb.seckill.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author geng
 * 2020/4/19
 */
@Getter
@Setter
public class User {
    private int id;
    private String nickName;
    private String phone;
    private int age;
    private String password;
    private String passwordSalt;
    private Date createDate;

}
