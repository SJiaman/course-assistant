package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Mapper
public interface CourseDao extends BaseDao<CourseEntity> {
	
}