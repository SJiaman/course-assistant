package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.CourseResourceDao;
import com.zrn.assistant.dto.CourseResourceDTO;
import com.zrn.assistant.entity.CourseResourceEntity;
import com.zrn.assistant.service.CourseResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 课程资源表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Service
public class CourseResourceServiceImpl extends CrudServiceImpl<CourseResourceDao, CourseResourceEntity, CourseResourceDTO>
        implements CourseResourceService {

    @Override
    public QueryWrapper<CourseResourceEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseResourceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}