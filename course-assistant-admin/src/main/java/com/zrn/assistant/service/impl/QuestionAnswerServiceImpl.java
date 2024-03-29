package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.QuestionAnswerDao;
import com.zrn.assistant.dto.QuestionAnswerDTO;
import com.zrn.assistant.entity.QuestionAnswerEntity;
import com.zrn.assistant.entity.QuestionEntity;
import com.zrn.assistant.service.QuestionAnswerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 题目答案表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@Service
public class QuestionAnswerServiceImpl extends CrudServiceImpl<QuestionAnswerDao, QuestionAnswerEntity, QuestionAnswerDTO> implements QuestionAnswerService {

    @Override
    public QueryWrapper<QuestionAnswerEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<QuestionAnswerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public void deleteQuestionAnswer(Long id) {
        LambdaUpdateWrapper<QuestionAnswerEntity> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(QuestionAnswerEntity::getQuestionId, id).set(QuestionAnswerEntity::getDeleted, 1);
         baseDao.update(null, lambdaUpdateWrapper);
    }
}