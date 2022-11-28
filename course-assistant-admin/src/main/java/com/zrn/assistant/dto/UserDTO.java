package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
@ApiModel(value = "用户表")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户id")
	private Long id;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "性别")
	private Integer gender;

	@ApiModelProperty(value = "生日")
	private Date birthday;

	@ApiModelProperty(value = "学院")
	private String college;

	@ApiModelProperty(value = "班级")
	private String className;

	@ApiModelProperty(value = "电话")
	private String phone;

	@ApiModelProperty(value = "角色")
	private Integer role;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "头像")
	private String imagePath;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}