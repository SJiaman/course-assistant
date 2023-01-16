package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.CourseResourceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程资源表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Mapper
public interface CourseResourceDao extends BaseDao<CourseResourceEntity> {
	
}