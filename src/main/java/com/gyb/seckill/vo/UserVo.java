package com.gyb.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 用户VO
 *
 * @author geng
 * 2020/5/9
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private int userId;
    private String nickName;
    private String phone;
    private int age;
    private Date signUpDate;
}
