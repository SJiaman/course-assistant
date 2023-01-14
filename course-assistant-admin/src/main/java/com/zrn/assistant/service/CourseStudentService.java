package com.zrn.assistant.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.dto.CourseStudentDTO;
import com.zrn.assistant.dto.JoinCourseDTO;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.entity.CourseStudentEntity;

import java.util.List;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
public interface CourseStudentService extends CrudService<CourseStudentEntity, CourseStudentDTO> {

    /**
     * 加入课程
     * @param dto
     */
    void joinCourse(JoinCourseDTO dto);


    List<CourseDTO> getUserCourse(Long id);

    List<CourseStudentDTO> getStudent(Long id);

    List<CourseStudentDTO> getStudentByCourseName(String courseName);
}