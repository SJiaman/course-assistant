package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 考试问题表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@TableName("t_exam_question")
public class ExamQuestionEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 考试id
     */
	private Long examId;
    /**
     * 题目id
     */
	private Long questionId;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;;
    /**
     * 是否删除0:未删除1:删除
     */
	private Boolean deleted;
}