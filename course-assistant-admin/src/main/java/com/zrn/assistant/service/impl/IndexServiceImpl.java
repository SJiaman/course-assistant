package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.dao.*;
import com.zrn.assistant.dto.TeacherDashboardDTO;
import com.zrn.assistant.entity.*;
import com.zrn.assistant.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Jiaman
 * @Date 2023/2/1 21:10
 * @Desc
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private CourseDao courseDao;

    @Resource
    private ExamDao examDao;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private ExamRecordDao examRecordDao;

    @Override
    public TeacherDashboardDTO getTeacherDashboardById(Long id) {
        int courseCount = 0, examCount = 0, examRecordCount = 0, examQuestionCount = 0;

        // 查询课程数
        List<CourseEntity> courseEntities = courseDao.selectList(
                Wrappers.lambdaQuery(CourseEntity.class).eq(CourseEntity::getTeacherId, id));
        courseCount = courseEntities.size();

        // 查询试卷数
        if (courseCount > 0) {
            List<Long> courseIds = courseEntities.stream().map(CourseEntity::getId).collect(Collectors.toList());
            List<ExamEntity> examEntities = examDao.selectList(
                    Wrappers.lambdaQuery(ExamEntity.class).in(ExamEntity::getCourseId, courseIds));
            examCount = examEntities.size();


            // 查询题目数
            List<QuestionEntity> questionEntities = questionDao.selectList(
                    Wrappers.lambdaQuery(QuestionEntity.class).in(QuestionEntity::getCourseId, courseIds));
            examQuestionCount = questionEntities.size();

            // 查询答卷数
            List<Long> examIds = courseEntities.stream().map(CourseEntity::getId).collect(Collectors.toList());
            List<ExamRecordEntity> examRecordEntities = examRecordDao.selectList(
                    Wrappers.lambdaQuery(ExamRecordEntity.class).in(ExamRecordEntity::getExamId, examIds));
            examRecordCount = examRecordEntities.size();
        }

        TeacherDashboardDTO teacherDashboardDTO = new TeacherDashboardDTO();
        teacherDashboardDTO.setCourseCount(courseCount);
        teacherDashboardDTO.setExamCount(examCount);
        teacherDashboardDTO.setQuestionCount(examQuestionCount);
        teacherDashboardDTO.setRecordCount(examRecordCount);
        return teacherDashboardDTO;
    }
}
