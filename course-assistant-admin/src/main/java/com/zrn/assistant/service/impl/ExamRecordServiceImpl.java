package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.enums.QuestionTypeEnum;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.ExamQuestionAnswerDao;
import com.zrn.assistant.dao.ExamRecordDao;
import com.zrn.assistant.dao.QuestionDao;
import com.zrn.assistant.dto.AnswerDTO;
import com.zrn.assistant.dto.AnswerItemDTO;
import com.zrn.assistant.dto.ExamRecordDTO;
import com.zrn.assistant.entity.ExamQuestionAnswerEntity;
import com.zrn.assistant.entity.ExamRecordEntity;
import com.zrn.assistant.entity.QuestionEntity;
import com.zrn.assistant.service.ExamRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 考试记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Service
public class ExamRecordServiceImpl extends CrudServiceImpl<ExamRecordDao, ExamRecordEntity, ExamRecordDTO> implements ExamRecordService {

    @Resource
    private QuestionDao questionDao;

    @Resource
    private ExamQuestionAnswerDao examQuestionAnswerDao;

    @Override
    public QueryWrapper<ExamRecordEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ExamRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public Integer doExam(AnswerDTO dto) {
        ExamRecordEntity examRecordEntity = new ExamRecordEntity();
        examRecordEntity.setExamId(dto.getId());
        examRecordEntity.setDoTime(dto.getDoTime());
        baseDao.insert(examRecordEntity);
        List<AnswerItemDTO> answerItems = dto.getAnswerItems();
        AtomicInteger score = new AtomicInteger();
        AtomicReference<Integer> correctCount = new AtomicReference<>(0);
        answerItems.forEach(item -> {
            ExamQuestionAnswerEntity examQuestionAnswerEntity = new ExamQuestionAnswerEntity();
            examQuestionAnswerEntity.setExamRecordId(examRecordEntity.getId());
            examQuestionAnswerEntity.setQuestionId(item.getQuestionId());
            if (item.getCompleted()) {
                QuestionEntity questionEntity = questionDao.selectById(item.getQuestionId());
                if (questionEntity.getType() == QuestionTypeEnum.ShortAnswer.getCode()) {
                    examQuestionAnswerEntity.setDoAnswer(item.getContent());
                }
                // 多选打分
                if (questionEntity.getType() == QuestionTypeEnum.MultipleChoice.getCode()) {
                    String collect = item.getContentArray().stream().sorted().collect(Collectors.joining(","));
                    if (questionEntity.getCorrect().equals(collect)) {
                        examQuestionAnswerEntity.setDoRight(true);
                        score.addAndGet(questionEntity.getScore());
                        examQuestionAnswerEntity.setScore(questionEntity.getScore());
                    } else {
                        examQuestionAnswerEntity.setDoRight(false);
                        examQuestionAnswerEntity.setScore(0);
                    }
                    examQuestionAnswerEntity.setDoAnswer(collect);

                }
                // 单选，判断题打分
                if (questionEntity.getType() != QuestionTypeEnum.ShortAnswer.getCode() &&
                        questionEntity.getType() != QuestionTypeEnum.MultipleChoice.getCode()) {
                    if (item.getContent().equals(questionEntity.getCorrect())) {
                        examQuestionAnswerEntity.setDoRight(true);
                        score.addAndGet(questionEntity.getScore());
                        examQuestionAnswerEntity.setScore(questionEntity.getScore());
                    } else {
                        examQuestionAnswerEntity.setDoRight(false);
                        examQuestionAnswerEntity.setScore(0);
                    }
                    examQuestionAnswerEntity.setDoAnswer(item.getContent());
                }
                correctCount.getAndSet(correctCount.get() + 1);
            }
            examQuestionAnswerDao.insert(examQuestionAnswerEntity);
        });
        examRecordEntity.setScore(score.get());
        examRecordEntity.setCorrectCount(correctCount.get());
        baseDao.updateById(examRecordEntity);
        return score.get();
    }
}