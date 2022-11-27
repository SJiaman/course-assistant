package com.zrn.assistant.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.modules.demo.dao.CourseDao;
import com.zrn.assistant.modules.demo.dto.CourseDTO;
import com.zrn.assistant.modules.demo.entity.CourseEntity;
import com.zrn.assistant.modules.demo.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Service
public class CourseServiceImpl extends CrudServiceImpl<CourseDao, CourseEntity, CourseDTO> implements CourseService {

    @Override
    public QueryWrapper<CourseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}