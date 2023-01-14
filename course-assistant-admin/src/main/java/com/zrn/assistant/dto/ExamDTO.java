package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 考试表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Data
@ApiModel(value = "考试表")
public class ExamDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "测试id")
	private Long id;

	@ApiModelProperty(value = "考试名称")
	private String examName;

	@ApiModelProperty(value = "教师id")
	private Long teacherId;

	@ApiModelProperty(value = "课程id")
	private Long courseId;

	@ApiModelProperty(value = "开始时间(可删除)")
	private Date startTime;

	@ApiModelProperty(value = "结束时间(可删除)")
	private Date endTime;

	@ApiModelProperty(value = "考试时长")
	private Integer duration;

	@ApiModelProperty(value = "总分")
	private Integer score;

	@ApiModelProperty(value = "习题状态(0:开始,1:结束)")
	private Integer status;

	@ApiModelProperty(value = "题目数量")
	private Integer questionCount;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}