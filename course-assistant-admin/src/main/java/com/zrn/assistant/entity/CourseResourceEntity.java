package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 课程资源表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@TableName("t_course_resource")
public class CourseResourceEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 课程id
     */
	private Long courseId;
    /**
     * 类型0:视频:1课件
     */
	private Integer type;
    /**
     * URL地址
     */
	private String url;
    /**
     * 文件名
     */
	private String fileName;
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