package com.zrn.assistant.modules.demo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
public class UserExcel {
    @Excel(name = "用户id")
    private Long id;
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "真实姓名")
    private String realName;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "性别")
    private Integer gender;
    @Excel(name = "生日")
    private Date birthday;
    @Excel(name = "学院")
    private String college;
    @Excel(name = "班级")
    private String className;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "角色")
    private Integer role;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "头像")
    private String imagePath;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "修改时间")
    private Date modifyTime;
    @Excel(name = "是否删除0:未删除1:删除")
    private Boolean deleted;

}