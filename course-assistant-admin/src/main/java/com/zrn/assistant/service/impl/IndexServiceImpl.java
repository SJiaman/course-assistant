package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.dao.CourseDao;
import com.zrn.assistant.dao.ExamDao;
import com.zrn.assistant.dao.ExamQuestionDao;
import com.zrn.assistant.dao.ExamRecordDao;
import com.zrn.assistant.dto.TeacherDashboardDTO;
import com.zrn.assistant.entity.CourseEntity;
import com.zrn.assistant.entity.ExamEntity;
import com.zrn.assistant.entity.ExamQuestionEntity;
import com.zrn.assistant.entity.ExamRecordEntity;
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
public class IndexServiceImpl implements IndexService  {

    @Resource
    private CourseDao courseDao;

    @Resource
    private ExamDao examDao;

    @Resource
    private ExamQuestionDao examQuestionDao;

    @Resource
    private ExamRecordDao examRecordDao;

    @Override
    public TeacherDashboardDTO getTeacherDashboardById(Long id) {
        List<CourseEntity> courseEntities = courseDao.selectList(
                Wrappers.lambdaQuery(CourseEntity.class).eq(CourseEntity::getTeacherId, id));
        int courseCount = courseEntities.size();

        List<Long> courseIds = courseEntities.stream().map(CourseEntity::getId).collect(Collectors.toList());
        List<ExamEntity> examEntities = examDao.selectList(
                Wrappers.lambdaQuery(ExamEntity.class).in(ExamEntity::getCourseId, courseIds));
        int examCount = examEntities.size();

        List<Long> examIds = examEntities.stream().map(ExamEntity::getId).collect(Collectors.toList());
        List<ExamQuestionEntity> examQuestionEntities = examQuestionDao.selectList(
                Wrappers.lambdaQuery(ExamQuestionEntity.class).in(ExamQuestionEntity::getExamId, examIds));
        int examQuestionCount = examQuestionEntities.size();

        List<ExamRecordEntity> examRecordEntities = examRecordDao.selectList(
                Wrappers.lambdaQuery(ExamRecordEntity.class).in(ExamRecordEntity::getExamId, examIds));
        int examRecordCount = examRecordEntities.size();

//        examRecordEntities.stream().collect()

        TeacherDashboardDTO teacherDashboardDTO = new TeacherDashboardDTO();
        teacherDashboardDTO.setCourseCount(courseCount);
        teacherDashboardDTO.setExamCount(examCount);
        teacherDashboardDTO.setQuestionCount(examQuestionCount);
        teacherDashboardDTO.setRecordCount(examRecordCount);
        return teacherDashboardDTO;
    }
}
