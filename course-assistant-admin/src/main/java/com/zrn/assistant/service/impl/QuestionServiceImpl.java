package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.QuestionAnswerDao;
import com.zrn.assistant.dao.QuestionDao;
import com.zrn.assistant.dto.QuestionAnswerDTO;
import com.zrn.assistant.dto.QuestionDTO;
import com.zrn.assistant.entity.QuestionAnswerEntity;
import com.zrn.assistant.entity.QuestionEntity;
import com.zrn.assistant.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 题目表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2023-01-14
 */
@Service
public class QuestionServiceImpl extends CrudServiceImpl<QuestionDao, QuestionEntity, QuestionDTO> implements QuestionService {
    @Resource
    private QuestionAnswerDao questionAnswerDao;

    @Override
    public QueryWrapper<QuestionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<QuestionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

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
        insert(questionEntity);
        List<QuestionAnswerDTO> answers = question.getAnswers();
        answers.forEach(x -> {
            x.setQuestionId(questionEntity.getId());
            QuestionAnswerEntity questionAnswerEntity = ConvertUtils.sourceToTarget(x, QuestionAnswerEntity.class);
            questionAnswerDao.insert(questionAnswerEntity);
        });
    }
}