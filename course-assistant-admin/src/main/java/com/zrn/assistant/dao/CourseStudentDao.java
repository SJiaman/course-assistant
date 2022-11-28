package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.CourseStudentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Mapper
public interface CourseStudentDao extends BaseDao<CourseStudentEntity> {
	
}