package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 考试问题表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@ApiModel(value = "考试问题表")
public class ExamQuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "考试id")
	private Long examId;

	private Integer questionType;

	@ApiModelProperty(value = "题目id")
	private Long questionId;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}