package com.zrn.assistant.dao;

import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.entity.ExamRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试记录表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Mapper
public interface ExamRecordDao extends BaseDao<ExamRecordEntity> {
	
}