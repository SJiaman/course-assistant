package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.CourseStudentDao;
import com.zrn.assistant.dto.CourseStudentDTO;
import com.zrn.assistant.entity.CourseStudentEntity;
import com.zrn.assistant.service.CourseStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Service
public class CourseStudentServiceImpl extends CrudServiceImpl<CourseStudentDao, CourseStudentEntity, CourseStudentDTO> implements CourseStudentService {

    @Override
    public QueryWrapper<CourseStudentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseStudentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}