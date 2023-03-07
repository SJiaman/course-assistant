package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.*;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.entity.*;
import com.zrn.assistant.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 课程表
 *
 * @author Zhang Ruina
 * @since 2022-11-27
 */
@Service
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
}