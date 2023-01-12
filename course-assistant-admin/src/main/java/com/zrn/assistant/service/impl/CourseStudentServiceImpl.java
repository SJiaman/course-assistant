package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.exception.BusinessException;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.CourseDao;
import com.zrn.assistant.dao.CourseStudentDao;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.dto.CourseStudentDTO;
import com.zrn.assistant.dto.JoinCourseDTO;
import com.zrn.assistant.entity.CourseEntity;
import com.zrn.assistant.entity.CourseStudentEntity;
import com.zrn.assistant.service.CourseService;
import com.zrn.assistant.service.CourseStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Service
public class CourseStudentServiceImpl extends CrudServiceImpl<CourseStudentDao, CourseStudentEntity, CourseStudentDTO> implements CourseStudentService {
    @Resource
    private CourseDao courseDao;

    @Override
    public QueryWrapper<CourseStudentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseStudentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public void joinCourse(JoinCourseDTO dto) {
        CourseEntity courseEntity = courseDao.selectById(dto.getCourseId());
        if (!courseEntity.getCommand().equals(dto.getCommand())) {
            throw new BusinessException("班级口令错误");
        }
        CourseStudentDTO courseStudentDTO = new CourseStudentDTO();
        courseStudentDTO.setStudentId(dto.getStudentId());
        courseStudentDTO.setCourseId(dto.getCourseId());
        save(courseStudentDTO);
    }

    @Override
    public List<CourseDTO> getUserCourse(Long id) {
        List<CourseStudentEntity> courseStudentEntities = baseDao.selectList(Wrappers.lambdaQuery(CourseStudentEntity.class)
                .eq(CourseStudentEntity::getStudentId, id));

        List<Long> courseIds = courseStudentEntities.stream()
                .map(CourseStudentEntity -> CourseStudentEntity.getCourseId()).collect(Collectors.toList());

        List<CourseEntity> courseEntities = courseDao.selectBatchIds(courseIds);

        List<CourseDTO> courseStudentDTOS = ConvertUtils.sourceToTarget(courseEntities, CourseDTO.class);

        return courseStudentDTOS;
    }
}