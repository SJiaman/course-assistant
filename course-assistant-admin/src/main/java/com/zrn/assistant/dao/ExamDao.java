package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.ExamEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Mapper
public interface ExamDao extends BaseDao<ExamEntity> {
	
}