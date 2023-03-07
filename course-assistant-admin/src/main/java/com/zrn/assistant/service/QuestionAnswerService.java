package com.zrn.assistant.service;

import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.QuestionAnswerDTO;
import com.zrn.assistant.entity.QuestionAnswerEntity;

import java.util.List;

/**
 * 题目答案表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
public interface QuestionAnswerService extends CrudService<QuestionAnswerEntity, QuestionAnswerDTO> {

    void deleteQuestionAnswer(Long id);
}