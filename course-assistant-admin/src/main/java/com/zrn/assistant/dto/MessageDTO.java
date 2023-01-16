package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@ApiModel(value = "消息")
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "内容")
	private String content;

	@ApiModelProperty(value = "消息创建者id")
	private Integer sendUserId;

	@ApiModelProperty(value = "课程id")
	private Integer courseId;

	private String courseName;

	private Integer receiveCount;

	@ApiModelProperty(value = "阅读统计")
	private Integer readCount;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}