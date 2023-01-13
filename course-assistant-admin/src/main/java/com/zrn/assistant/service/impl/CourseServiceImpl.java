package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.CourseDao;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.entity.CourseEntity;
import com.zrn.assistant.service.CourseService;
import com.zrn.assistant.service.CourseStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Service
public class CourseServiceImpl extends CrudServiceImpl<CourseDao, CourseEntity, CourseDTO> implements CourseService {

    @Autowired
    private CourseStudentService courseStudentService;

    @Override
    public QueryWrapper<CourseEntity> getWrapper(Map<String, Object> params){
        String name = (String)params.get("name");
        String teacherId = (String)params.get("teacherId");

        QueryWrapper<CourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(name), "name", name)
                .or()
                .eq(StringUtils.isNotBlank(teacherId), "teacher_id", teacherId);

        return wrapper;
    }

}