package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 题目答案表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Data
@ApiModel(value = "题目答案表")
public class QuestionAnswerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "题目id")
	private Long questionId;

	@ApiModelProperty(value = "选项")
	private String prefix;

	@ApiModelProperty(value = "答案内容")
	private String content;


}