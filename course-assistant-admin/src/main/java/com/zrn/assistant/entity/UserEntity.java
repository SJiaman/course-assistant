package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
@TableName("t_user")
public class UserEntity {

    /**
     * 用户id
     */
	private Long id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 真实姓名
     */
	private String realName;
    /**
     * 年龄
     */
	private Integer age;
    /**
     * 性别
     */
	private Integer gender;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 学院
     */
	private String college;
    /**
     * 班级
     */
	private String className;
    /**
     * 电话
     */
	private String phone;
    /**
     * 角色
     */
	private Integer role;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 头像
     */
	private String imagePath;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date modifyTime;
    /**
     * 是否删除0:未删除1:删除
     */
	private Boolean deleted;
}