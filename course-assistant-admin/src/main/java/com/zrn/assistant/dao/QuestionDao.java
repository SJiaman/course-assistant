package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Mapper
public interface QuestionDao extends BaseDao<QuestionEntity> {
	
}