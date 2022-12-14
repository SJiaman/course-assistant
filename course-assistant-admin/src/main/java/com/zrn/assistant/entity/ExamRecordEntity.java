package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 考试记录表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Data
@TableName("t_exam_record")
public class ExamRecordEntity {

    /**
     * 测试回答记录
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 考试id
     */
	private Long examId;
    /**
     * 学生id
     */
	private Long studentId;
    /**
     * 得分
     */
	private Integer score;
    /**
     * 做对数量
     */
	private Integer correctCount;
    /**
     * 答卷状态
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