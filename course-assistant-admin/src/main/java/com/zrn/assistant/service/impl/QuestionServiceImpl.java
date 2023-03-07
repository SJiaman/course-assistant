package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.enums.QuestionTypeEnum;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.*;
import com.zrn.assistant.dto.QuestionAnswerDTO;
import com.zrn.assistant.dto.QuestionDTO;
import com.zrn.assistant.entity.ExamQuestionAnswerEntity;
import com.zrn.assistant.entity.ExamQuestionEntity;
import com.zrn.assistant.entity.QuestionAnswerEntity;
import com.zrn.assistant.entity.QuestionEntity;
import com.zrn.assistant.service.QuestionAnswerService;
import com.zrn.assistant.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Service
public class QuestionServiceImpl extends CrudServiceImpl<QuestionDao, QuestionEntity, QuestionDTO> implements QuestionService {
    @Resource
    private QuestionAnswerDao questionAnswerDao;

    @Resource
    private QuestionAnswerService questionAnswerService;

    @Resource
    private ExamQuestionDao examQuestionDao;

    @Resource
    private ExamQuestionAnswerDao examQuestionAnswerDao;

    @Override
    public QueryWrapper<QuestionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String courseId = (String)params.get("courseId");
        String type = (String)params.get("type");


        QueryWrapper<QuestionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id)
        .eq(StringUtils.isNotBlank(courseId), "course_id", courseId)
                .eq(StringUtils.isNotBlank(type), "type", type);

        return wrapper;
    }


    @Override
    public void addQuestion(QuestionDTO question) {
//        QuestionEntity questionEntity = new QuestionEntity();
//        questionEntity.setTitle(question.getTitle());
//        questionEntity.setAnalyzeText(question.getAnalyzeText());
//        questionEntity.setCorrect(question.getCorrect());
//        questionEntity.setDifficult(question.getDifficult());
//        questionEntity.setCorrect(question.getCorrect());
//        questionEntity.setType(question.getType());
//        questionEntity.setCreateUser(question.getCreateUser());
//        questionEntity.setScore(question.getScore());
//        questionEntity.setInfoTextContentId(question.getInfoTextContentId());
        QuestionEntity questionEntity = ConvertUtils.sourceToTarget(question, QuestionEntity.class);
        if (question.getType() == QuestionTypeEnum.MultipleChoice.getCode()) {
            String collect = question.getCorrectArray().stream().sorted().collect(Collectors.joining(","));
            questionEntity.setCorrect(collect);
        }
        insert(questionEntity);
        if (!CollectionUtils.isEmpty(question.getAnswers())) {
            List<QuestionAnswerDTO> answers = question.getAnswers();
            answers.forEach(x -> {
                x.setQuestionId(questionEntity.getId());
                QuestionAnswerEntity questionAnswerEntity = ConvertUtils.sourceToTarget(x, QuestionAnswerEntity.class);
                questionAnswerDao.insert(questionAnswerEntity);
            });
        }
    }

    @Override
    public QuestionDTO getQuestionById(Long id) {
        QuestionDTO questionDTO = get(id);
        List<QuestionAnswerEntity> questionAnswerEntity = questionAnswerDao
                .selectList(Wrappers.lambdaQuery(QuestionAnswerEntity.class).eq(QuestionAnswerEntity::getQuestionId, id));
        questionDTO.setAnswers(ConvertUtils.sourceToTarget(questionAnswerEntity, QuestionAnswerDTO.class));
        return questionDTO;
    }

    @Override
    public void updateQuestion(QuestionDTO dto) {
        QuestionEntity questionEntity = ConvertUtils.sourceToTarget(dto, QuestionEntity.class);
        if (dto.getType() == QuestionTypeEnum.MultipleChoice.getCode()) {
            String collect = dto.getCorrectArray().stream().sorted().collect(Collectors.joining(","));
            questionEntity.setCorrect(collect);
        }
        updateById(questionEntity);
//        QuestionDTO questionById = getQuestionById(dto.getId());
//        List<Long> ids = questionById.getAnswers().stream().map(QuestionAnswerDTO::getQuestionId).collect(Collectors.toList());
//        Long[] answerIds = ids.toArray(new Long[ids.size()]);


        questionAnswerService.deleteQuestionAnswer(dto.getId());

        if (!CollectionUtils.isEmpty(dto.getAnswers())) {
            List<QuestionAnswerDTO> answers = dto.getAnswers();
            answers.forEach(x -> {
                x.setQuestionId(questionEntity.getId());
                QuestionAnswerEntity questionAnswerEntity = ConvertUtils.sourceToTarget(x, QuestionAnswerEntity.class);
                questionAnswerDao.insert(questionAnswerEntity);
            });
        }
    }

    @Override
    public void deleteQuestion(Long[] ids) {
        delete(ids);
        // 试卷题目删除
        LambdaUpdateWrapper<ExamQuestionEntity> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(ExamQuestionEntity::getQuestionId, ids[0]).set(ExamQuestionEntity::getDeleted, 1);
        examQuestionDao.update(null, lambdaUpdateWrapper);

        // 答题记录题目删除
        LambdaUpdateWrapper<ExamQuestionAnswerEntity> examQuestionAnswerWrapper = new LambdaUpdateWrapper<>();
        examQuestionAnswerWrapper.eq(ExamQuestionAnswerEntity::getQuestionId, ids[0]).set(ExamQuestionAnswerEntity::getDeleted, 1);
        examQuestionAnswerDao.update(null, examQuestionAnswerWrapper);
    }
}