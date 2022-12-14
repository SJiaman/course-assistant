package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.ExamQuestionDao;
import com.zrn.assistant.dto.ExamQuestionDTO;
import com.zrn.assistant.entity.ExamQuestionEntity;
import com.zrn.assistant.service.ExamQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 考试问题表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Service
public class ExamQuestionServiceImpl extends CrudServiceImpl<ExamQuestionDao, ExamQuestionEntity, ExamQuestionDTO> implements ExamQuestionService {

    @Override
    public QueryWrapper<ExamQuestionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ExamQuestionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}