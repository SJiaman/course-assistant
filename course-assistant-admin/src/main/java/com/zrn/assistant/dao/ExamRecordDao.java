package com.zrn.assistant.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.dto.ExamRecordDTO;
import com.zrn.assistant.dto.RecordCountDTO;
import com.zrn.assistant.entity.ExamRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 考试记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Mapper
public interface ExamRecordDao extends BaseDao<ExamRecordEntity> {
    IPage<ExamRecordDTO> getExamRecordList(IPage<ExamRecordEntity> page, @Param("ew") Wrapper<ExamRecordDTO> queryWrapper);

    List<RecordCountDTO> getExamRecordListCount(List<Long> ids);
}