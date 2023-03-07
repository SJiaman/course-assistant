package com.zrn.assistant.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.QuestionDTO;
import com.zrn.assistant.entity.QuestionEntity;


/**
 * 题目表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
public interface QuestionService extends CrudService<QuestionEntity, QuestionDTO> {

    void addQuestion(QuestionDTO question);

    QuestionDTO getQuestionById(Long id);

    void updateQuestion(QuestionDTO dto);

    void deleteQuestion(Long[] ids);
}