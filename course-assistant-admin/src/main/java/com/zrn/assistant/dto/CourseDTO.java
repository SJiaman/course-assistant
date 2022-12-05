package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
@ApiModel(value = "课程表")
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程id")
	private Long id;

	@ApiModelProperty(value = "老师id")
	private Long teacherId;

	@ApiModelProperty(value = "课程名称")
	private String name;

	@ApiModelProperty(value = "课程加入口令")
	private String command;

	@ApiModelProperty(value = "课程人数")
	private Integer count;

	@ApiModelProperty(value = "开课状态0:未开始1:进行中2:已结束")
	private Integer status;
}