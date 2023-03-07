package com.zrn.assistant.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.entity.CourseEntity;

import java.util.List;

/**
 * 课程表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
public interface CourseService extends CrudService<CourseEntity, CourseDTO> {

    void deleteCourse(Long[] ids);
}