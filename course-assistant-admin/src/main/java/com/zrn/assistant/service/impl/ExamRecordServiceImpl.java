package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.ExamRecordDao;
import com.zrn.assistant.dto.ExamRecordDTO;
import com.zrn.assistant.entity.ExamRecordEntity;
import com.zrn.assistant.service.ExamRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 考试记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Service
public class ExamRecordServiceImpl extends CrudServiceImpl<ExamRecordDao, ExamRecordEntity, ExamRecordDTO> implements ExamRecordService {

    @Override
    public QueryWrapper<ExamRecordEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ExamRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}