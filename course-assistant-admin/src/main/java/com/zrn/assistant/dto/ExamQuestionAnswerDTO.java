package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 考试问题记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@ApiModel(value = "考试问题记录表")
public class ExamQuestionAnswerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "习题记录id")
	private Long examRecordId;

	@ApiModelProperty(value = "问题id")
	private Long questionId;

	@ApiModelProperty(value = "是否正确")
	private Boolean doRight;

	@ApiModelProperty(value = "得分")
	private Integer score;

	@ApiModelProperty(value = "学生回答答案")
	private String doAnswer;

	@ApiModelProperty(value = "答题状态")
	private Integer status;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}