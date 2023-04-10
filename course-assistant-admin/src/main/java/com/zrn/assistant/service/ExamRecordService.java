package com.zrn.assistant.service;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.AnswerDTO;
import com.zrn.assistant.dto.ExamRecordAnalysisDTO;
import com.zrn.assistant.dto.ExamRecordDTO;
import com.zrn.assistant.dto.RecordDTO;
import com.zrn.assistant.entity.ExamRecordEntity;

import java.util.List;
import java.util.Map;


/**
 * 考试记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
public interface ExamRecordService extends CrudService<ExamRecordEntity, ExamRecordDTO> {

    Integer doExam(AnswerDTO dto);

    PageData<ExamRecordDTO> getExamRecords(Map<String, Object> params);

    RecordDTO getRecord(Long id);

    PageData<ExamRecordAnalysisDTO> getExamRecordAnalysis(Map<String, Object> params);

    List<Map<String, String>> getRecordCount();
}