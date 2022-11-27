package com.zrn.assistant.modules.demo.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.modules.demo.entity.CourseEntity;
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