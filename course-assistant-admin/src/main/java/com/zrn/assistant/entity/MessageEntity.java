package com.zrn.assistant.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Data
@TableName("t_message")
public class MessageEntity {

    /**
     * 
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 
     */
	private String title;
    /**
     * 
     */
	private String content;
    /**
     * 
     */
	private Integer sendUserId;
    /**
     * 
     */
	private Integer courseId;
    /**
     * 
     */
	private Integer readCount;
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