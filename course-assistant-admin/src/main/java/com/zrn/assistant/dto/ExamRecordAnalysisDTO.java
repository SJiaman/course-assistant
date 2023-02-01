package com.zrn.assistant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author Jiaman
 * @Date 2023/2/1 21:21
 * @Desc
 */
@Data
public class ExamRecordAnalysisDTO {
    @ApiModelProperty(value = "测试id")
    private Long id;

    @ApiModelProperty(value = "考试名称")
    private String examName;

    @ApiModelProperty(value = "教师id")
    private Long teacherId;

    @ApiModelProperty(value = "课程id")
    private Long courseId;

    @ApiModelProperty(value = "总分")
    private Integer score;

    private Double minScore;

    private Double maxScore;

    private String averageScore;

    private String averageDoTime;

    private Integer type;
}
