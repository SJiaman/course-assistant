package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.ExamQuestionAnswerDao;
import com.zrn.assistant.dto.ExamQuestionAnswerDTO;
import com.zrn.assistant.entity.ExamQuestionAnswerEntity;
import com.zrn.assistant.service.ExamQuestionAnswerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 考试问题记录表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Service
public class ExamQuestionAnswerServiceImpl extends CrudServiceImpl<ExamQuestionAnswerDao, ExamQuestionAnswerEntity,
        ExamQuestionAnswerDTO> implements ExamQuestionAnswerService {

    @Override
    public QueryWrapper<ExamQuestionAnswerEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ExamQuestionAnswerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}