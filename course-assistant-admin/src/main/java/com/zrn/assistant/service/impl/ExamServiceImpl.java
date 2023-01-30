package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.ExamDao;
import com.zrn.assistant.dao.ExamQuestionDao;
import com.zrn.assistant.dto.ExamDTO;
import com.zrn.assistant.dto.QuestionDTO;
import com.zrn.assistant.dto.TitleItemsDTO;
import com.zrn.assistant.entity.ExamEntity;
import com.zrn.assistant.entity.ExamQuestionEntity;
import com.zrn.assistant.service.ExamService;
import com.zrn.assistant.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 考试表
 *
 * @author Zhang Ruina
 * @since 2022-12-14
 */
@Service
@Slf4j
public class ExamServiceImpl extends CrudServiceImpl<ExamDao, ExamEntity, ExamDTO> implements ExamService {
    @Resource
    private ExamQuestionDao examquestionDao;

    @Resource
    private QuestionService questionService;

    @Override
    public QueryWrapper<ExamEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        String courseId = (String) params.get("courseId");

        QueryWrapper<ExamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id)
        .eq(StringUtils.isNotBlank(courseId), "course_id", courseId);

        return wrapper;
    }


    @Override
    public void saveExam(ExamDTO dto) {
        ExamEntity examEntity = ConvertUtils.sourceToTarget(dto, ExamEntity.class);
        if (!CollectionUtils.isEmpty(dto.getLimitDateTime())) {
            List<Date> limitDateTime = dto.getLimitDateTime();
            examEntity.setStartTime(limitDateTime.get(0));
            examEntity.setEndTime(limitDateTime.get(1));
        }
        List<TitleItemsDTO> titleItems = dto.getTitleItems();
        int questionCount = 0;
        int score = 0;
        for (TitleItemsDTO item : titleItems) {
            questionCount += item.getQuestionItems().size();
            List<QuestionDTO> questionItems = item.getQuestionItems();
            score += questionItems.stream().mapToInt(QuestionDTO::getScore).sum();
        }
        examEntity.setQuestionCount(questionCount);
        examEntity.setScore(score);
        examEntity.setStatus(0);
        insert(examEntity);

        // 试卷问题
        titleItems.forEach(item -> {
            List<QuestionDTO> questionDTOList = item.getQuestionItems();
            questionDTOList.forEach(x -> {
                ExamQuestionEntity examQuestionEntity = new ExamQuestionEntity();
                examQuestionEntity.setExamId(examEntity.getId());
                examQuestionEntity.setQuestionId(x.getId());
                examQuestionEntity.setQuestionType(x.getType());
                examquestionDao.insert(examQuestionEntity);
            });
        });
    }

    @Override
    public void deleteExam(Integer id) {
        baseDao.deleteById(id);
        examquestionDao.delete(Wrappers.lambdaQuery(ExamQuestionEntity.class).eq(ExamQuestionEntity::getExamId, id));
    }

    @Override
    public ExamDTO getExamById(Long id) {
        ExamDTO examDTO = get(id);
        List<ExamQuestionEntity> examQuestionEntities = examquestionDao
                .selectList(Wrappers.lambdaQuery(ExamQuestionEntity.class).eq(ExamQuestionEntity::getExamId, id));
        Set<Integer> collect = examQuestionEntities.stream().map(ExamQuestionEntity::getQuestionType).collect(Collectors.toSet());
        ArrayList<TitleItemsDTO> titleItemsDTOS = new ArrayList<>();
        for (int i = 0; i < collect.size(); i++) {
            TitleItemsDTO titleItemsDTO = new TitleItemsDTO();
            Integer type = (Integer)collect.toArray()[i];
            titleItemsDTO.setType(type);
            List<Long> questionIds = examQuestionEntities
                    .stream().filter(x -> x.getQuestionType().equals(type)).map(ExamQuestionEntity::getQuestionId).collect(Collectors.toList());
            ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
            for (Long questionId : questionIds) {
                QuestionDTO questionById = questionService.getQuestionById(questionId);
                questionDTOS.add(questionById);
            }
            titleItemsDTO.setQuestionItems(questionDTOS);
            titleItemsDTOS.add(titleItemsDTO);
        }
        examDTO.setTitleItems(titleItemsDTOS);
        return examDTO;
    }
}