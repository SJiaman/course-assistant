package com.zrn.assistant.modules.demo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Data
public class CourseStudentExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "学生id")
    private Long studentId;
    @Excel(name = "课程id")
    private Long courseId;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "修改时间")
    private Date modifyTime;
    @Excel(name = "是否删除0:未删除1:删除")
    private Boolean deleted;

}