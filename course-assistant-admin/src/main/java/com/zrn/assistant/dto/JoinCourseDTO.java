package com.zrn.assistant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Zhang Ruina
 * @Date 2022/12/7 13:03
 * @Desc
 */
@Data
public class JoinCourseDTO implements Serializable {
    @ApiModelProperty(value = "课程id")
    private Long courseId;

    @ApiModelProperty(value = "学生id")
    private Long studentId;

    @ApiModelProperty(value = "课程加入口令")
    private String command;
}
