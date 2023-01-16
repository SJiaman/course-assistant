package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.ExamQuestionAnswerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试问题记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Mapper
public interface ExamQuestionAnswerDao extends BaseDao<ExamQuestionAnswerEntity> {
	
}