package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 课程资源表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@ApiModel(value = "课程资源表")
public class CourseResourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "课程id")
	private Long courseId;

	@ApiModelProperty(value = "类型0:视频:1课件")
	private Integer type;

	@ApiModelProperty(value = "URL地址")
	private String url;

	@ApiModelProperty(value = "文件名")
	private String fileName;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}