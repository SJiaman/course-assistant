package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
@Data
@TableName("t_course_student")
public class CourseStudentEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 学生id
     */
	private Long studentId;
    /**
     * 课程id
     */
	private Long courseId;
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