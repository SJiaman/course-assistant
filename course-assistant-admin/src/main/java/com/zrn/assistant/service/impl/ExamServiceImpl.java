package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.ExamDao;
import com.zrn.assistant.dto.ExamDTO;
import com.zrn.assistant.entity.ExamEntity;
import com.zrn.assistant.service.ExamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 考试表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Service
public class ExamServiceImpl extends CrudServiceImpl<ExamDao, ExamEntity, ExamDTO> implements ExamService {

    @Override
    public QueryWrapper<ExamEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ExamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}