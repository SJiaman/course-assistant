package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 考试问题记录表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Data
@TableName("t_exam_question_answer")
public class ExamQuestionAnswerEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 习题记录id
     */
	private Long examRecordId;
    /**
     * 问题id
     */
	private Long questionId;
    /**
     * 是否正确
     */
	private Boolean doRight;
    /**
     * 得分
     */
	private Integer score;
    /**
     * 学生回答答案
     */
	private String doAnswer;
    /**
     * 答题状态
     */
	private Integer status;
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