package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.CourseStudentScoreDao;
import com.zrn.assistant.dto.CourseStudentScoreDTO;
import com.zrn.assistant.entity.CourseStudentScoreEntity;
import com.zrn.assistant.service.CourseStudentScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 课程得分
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Service
public class CourseStudentScoreServiceImpl extends CrudServiceImpl<CourseStudentScoreDao, CourseStudentScoreEntity,
        CourseStudentScoreDTO> implements CourseStudentScoreService {

    @Override
    public QueryWrapper<CourseStudentScoreEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseStudentScoreEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}