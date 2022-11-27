package com.zrn.assistant.modules.demo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
public class CourseExcel {
    @Excel(name = "课程id")
    private Long id;
    @Excel(name = "老师id")
    private Long teacherId;
    @Excel(name = "课程名称")
    private String name;
    @Excel(name = "课程加入口令")
    private String command;
    @Excel(name = "课程人数")
    private Integer count;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "修改时间")
    private Date modifyTime;
    @Excel(name = "是否删除0:未删除1:删除")
    private Boolean deleted;

}