package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
@TableName("t_course")
public class CourseEntity {

    /**
     * 课程id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 老师id
     */
	private Long teacherId;
    /**
     * 课程名称
     */
	private String name;
    /**
     * 课程加入口令
     */
	private String command;
    /**
     * 课程人数
     */
	private Integer count;
    /**
     * 开课状态0:未开始1:进行中2:已结束
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
     * 是否删除0:未删除1:删除
     */
	private Boolean deleted;
}