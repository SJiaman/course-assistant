package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 题目表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Data
@TableName("t_question")
public class QuestionEntity {

    /**
     * 题目id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 题目名
     */
	private String title;
    /**
     * 题目类型
     */
	private Integer type;
    /**
     * 题目分数
     */
	private Integer score;
    /**
     * 难度
     */
	private Integer difficult;
    /**
     * 正确答案
     */
	private String correct;
    /**
     * 解析
     */
	private String analyzeText;
    /**
     * 题目详情
     */
	private Integer infoTextContentId;
    /**
     * 创建人
     */
	private Integer createUser;
    /**
     * 状态
     */
	private Integer status;
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
     * 
     */
	private Boolean deleted;
}