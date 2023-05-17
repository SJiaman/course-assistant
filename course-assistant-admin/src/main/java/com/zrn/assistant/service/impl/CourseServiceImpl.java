package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.exception.BusinessException;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.*;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.entity.*;
import com.zrn.assistant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 课程表
 *
 * @author Zhang Ruina
 * @since 2022-11-27
 */
@Service
@Slf4j
public class CourseServiceImpl extends CrudServiceImpl<CourseDao, CourseEntity, CourseDTO> implements CourseService {
    @Resource
    private CourseStudentDao courseStudentDao;

    @Resource
    private CourseResourceDao courseResourceDao;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private ExamService examService;

    @Resource
    private MessageService messageService;



    @Override
    public QueryWrapper<CourseEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");
        String teacherId = (String) params.get("teacherId");


        QueryWrapper<CourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(name), "name", name)
                .or()
                .eq(StringUtils.isNotBlank(teacherId), "teacher_id", teacherId);

        return wrapper;
    }

    @Override
    public void save(CourseDTO dto) {
        CourseEntity courseEntity = baseDao.selectOne(Wrappers.
                lambdaQuery(CourseEntity.class).eq(CourseEntity::getName, dto.getName()));

        if (courseEntity != null) {
            throw new BusinessException("课程已存在");
        }
        CourseEntity entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        insert(entity);

        //copy主键值到dto
        BeanUtils.copyProperties(entity, dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourse(Long[] ids) {
        delete(ids);
        // 删除课程关联学生
        courseStudentDao.delete(Wrappers.lambdaQuery(CourseStudentEntity.class)
                .eq(CourseStudentEntity::getCourseId, ids[0]));

        // 删除课程关联资源
        courseResourceDao.delete(Wrappers.lambdaQuery(CourseResourceEntity.class)
                .eq(CourseResourceEntity::getCourseId, ids[0]));

        // 删除课程关联题目
        questionDao.delete(Wrappers.lambdaQuery(QuestionEntity.class)
                .eq(QuestionEntity::getCourseId, ids[0]));


        // 删除课程关联试卷
        examService.deleteExamByCourseId(ids[0]);

        // 删除课程通告
        messageService.deleteMessage(ids[0]);

    }

    @Override
    public List<CourseDTO> studentCourse(Map<String, Object> params) {
        String studentId = (String) params.get("studentId");

        List<CourseEntity> courseEntityList = new ArrayList<>();
        if (StringUtils.isNotBlank(studentId)) {
            List<CourseStudentEntity> courseStudentEntities = courseStudentDao.selectList(Wrappers
                    .lambdaQuery(CourseStudentEntity.class)
                    .eq(CourseStudentEntity::getStudentId, studentId));
            if (!courseStudentEntities.isEmpty()) {
                List<Long> courseIds = courseStudentEntities.stream().map(CourseStudentEntity::getCourseId)
                        .collect(Collectors.toList());
                 courseEntityList = baseDao.selectBatchIds(courseIds);
            }
        }

        return ConvertUtils.sourceToTarget(courseEntityList, currentDtoClass());
    }
}