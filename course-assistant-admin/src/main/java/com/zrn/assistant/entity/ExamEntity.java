package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 考试表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Data
@TableName("t_exam")
public class ExamEntity {

    /**
     * 测试id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 考试名称
     */
	private String examName;
    /**
     * 教师id
     */
	private Long teacherId;
    /**
     * 课程id
     */
	private Long courseId;
    /**
     * 开始时间(可删除)
     */
	private Date startTime;
    /**
     * 结束时间(可删除)
     */
	private Date endTime;
    /**
     * 考试时长
     */
	private Integer duration;
    /**
     * 总分
     */
	private Integer score;
    /**
     * 习题状态(0:开始,1:结束)
     */
	private Integer status;
    /**
     * 题目数量
     */
	private Integer questionCount;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
    /**
     * 是否删除0:未删除1:删除
     */
	private Boolean deleted;
}