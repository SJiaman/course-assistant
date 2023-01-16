package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.QuestionAnswerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目答案表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Mapper
public interface QuestionAnswerDao extends BaseDao<QuestionAnswerEntity> {
	
}