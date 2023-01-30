package com.zrn.assistant.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.ExamDTO;
import com.zrn.assistant.entity.ExamEntity;

/**
 * 考试表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
public interface ExamService extends CrudService<ExamEntity, ExamDTO> {

    void saveExam(ExamDTO dto);

    void deleteExam(Integer id);

    ExamDTO getExamById(Long id);
}