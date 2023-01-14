package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 题目答案表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2023-01-14
 */
@Data
@TableName("t_question_answer")
public class QuestionAnswerEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 题目id
     */
	private Integer questionId;
    /**
     * 选项
     */
	private String prefix;
    /**
     * 答案内容
     */
	private Integer content;
    /**
     * 创建时间
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