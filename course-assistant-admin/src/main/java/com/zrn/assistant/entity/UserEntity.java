package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
@Data
@TableName("t_user")
public class UserEntity {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
	private Date modifyTime;
    /**
     * 是否删除0:未删除1:删除
     */
	private Boolean deleted;
}