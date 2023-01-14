package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.dto.CourseStudentDTO;
import com.zrn.assistant.entity.CourseStudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Mapper
public interface CourseStudentDao extends BaseDao<CourseStudentEntity> {

    List<CourseStudentDTO> getTeacherStudent(@Param("id") Long id);


    List<CourseStudentDTO> getStudentByCourseName(@Param("courseName") String courseName);


}