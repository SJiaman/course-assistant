package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
@Data
@ApiModel(value = "课程-学生关联表")
public class CourseStudentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "学生id")
	private Long studentId;

	@ApiModelProperty(value = "课程id")
	private Long courseId;

	private String username;

	private String realName;

	private String college;

	private String className;

	private String courseName;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;
}