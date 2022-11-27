package com.zrn.assistant.modules.demo.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.modules.demo.dto.CourseStudentDTO;
import com.zrn.assistant.modules.demo.entity.CourseStudentEntity;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
public interface CourseStudentService extends CrudService<CourseStudentEntity, CourseStudentDTO> {

}