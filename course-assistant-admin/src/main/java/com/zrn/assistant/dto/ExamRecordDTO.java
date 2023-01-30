package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 考试记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@ApiModel(value = "考试记录表")
public class ExamRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "测试回答记录")
	private Long id;

	@ApiModelProperty(value = "考试id")
	private Long examId;

	@ApiModelProperty(value = "学生id")
	private Long studentId;

	@ApiModelProperty(value = "得分")
	private Integer score;

	@ApiModelProperty(value = "做对数量")
	private Integer correctCount;

	private Integer doTime;

	@ApiModelProperty(value = "答卷状态")
	private Integer status;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;

	@ApiModelProperty(value = "是否删除0:未删除1:删除")
	private Boolean deleted;


}