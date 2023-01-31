package com.zrn.assistant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Zhang Ruina
 * @Date 2023/1/31 9:44
 * @Desc
 */

@Data
public class RecordDTO implements Serializable {
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

    private Integer type;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private List<Date> limitDateTime;

    //	private List<QuestionDTO> questionDTOList;
    private List<TitleItemsDTO> titleItems;

    private AnswerDTO answer;
}