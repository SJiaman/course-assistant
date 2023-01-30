package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 题目表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Data
@ApiModel(value = "题目表")
public class QuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "题目id")
	private Long id;

	@ApiModelProperty(value = "题目名")
	private String title;

	@ApiModelProperty(value = "题目类型")
	private Integer type;

	@ApiModelProperty(value = "题目分数")
	private Integer score;

	@ApiModelProperty(value = "难度")
	private Integer difficult;

	@ApiModelProperty(value = "正确答案")
	private String correct;

	@ApiModelProperty(value = "多选正确答案")
	private List<String> correctArray;

	@ApiModelProperty(value = "解析")
	private String analyzeText;

	@ApiModelProperty(value = "题目详情")
	private Integer infoTextContentId;

	@ApiModelProperty(value = "创建人")
	private Integer createUser;

	@ApiModelProperty(value = "状态")
	private Integer status;


	private List<QuestionAnswerDTO> answers;

	private Long courseId;
}