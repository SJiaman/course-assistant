package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.exception.BusinessException;
import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.CourseDao;
import com.zrn.assistant.dao.CourseStudentDao;
import com.zrn.assistant.dao.UserDao;
import com.zrn.assistant.dto.CourseDTO;
import com.zrn.assistant.dto.CourseStudentDTO;
import com.zrn.assistant.dto.JoinCourseDTO;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.entity.CourseEntity;
import com.zrn.assistant.entity.CourseStudentEntity;
import com.zrn.assistant.entity.UserEntity;
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
 * @author Zhang Ruina
 * @since  2022-11-27
 */
@Service
public class CourseStudentServiceImpl extends CrudServiceImpl<CourseStudentDao, CourseStudentEntity, CourseStudentDTO> implements CourseStudentService {
    @Resource
    private CourseDao courseDao;

    @Resource
    private UserDao userDao;

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
                .map(CourseStudentEntity::getCourseId).collect(Collectors.toList());
        List<CourseEntity> courseEntities = courseDao.selectBatchIds(courseIds);
        return ConvertUtils.sourceToTarget(courseEntities, CourseDTO.class);
    }

    @Override
    public PageData<CourseStudentDTO> getStudent(Map<String, Object> params) {
        IPage<CourseStudentEntity> page = getPage(params, null, false);
        QueryWrapper<CourseStudentDTO> wrapper = new QueryWrapper<>();
        String teacherId = (String)params.get("teacherId");
        String courseName = (String)params.get("courseName");
        wrapper.eq("b.deleted", false)
                .eq(StringUtils.isNotBlank(courseName),"a.name", params.get("courseName"))
                .eq(StringUtils.isNotBlank(teacherId), "a.teacher_id", teacherId);
        IPage<CourseStudentDTO> teacherStudent = baseDao.getTeacherStudent(page, wrapper);
        return new PageData<>(teacherStudent.getRecords(), teacherStudent.getTotal());
//        List<CourseEntity> courseEntities = courseDao.selectList(Wrappers.lambdaQuery(CourseEntity.class)
//                .eq(CourseEntity::getTeacherId, id));
//        List<Long> courseIds = courseEntities.stream().map(CourseEntity::getId).collect(Collectors.toList());
//        List<CourseStudentEntity> courseStudentEntities = baseDao.selectList(Wrappers.lambdaQuery(CourseStudentEntity.class)
//                .in(CourseStudentEntity::getCourseId, courseIds));
//        List<Long> studentIds = courseStudentEntities.stream().map(CourseStudentEntity::getStudentId).collect(Collectors.toList());
//        List<UserEntity> userEntities = userDao.selectBatchIds(studentIds);
//        IPage<CourseStudentEntity> page = getPage(params, null, false);
//        QueryWrapper<CourseStudentDTO> wrapper = new QueryWrapper<>();
//        IPage<CourseStudentDTO> messageDTOIPage = baseDao.getTeacherStudent(page);
//        List<CourseStudentDTO> dto = baseDao.getTeacherStudent(id);
    }

    @Override
    public List<CourseStudentDTO> getStudentByCourseName(String courseName) {
//        CourseEntity courseEntity = courseDao.selectOne(Wrappers.lambdaQuery(CourseEntity.class)
//                .eq(CourseEntity::getName, courseName));
//        List<CourseStudentEntity> courseStudentEntities = baseDao.selectList(Wrappers.lambdaQuery(CourseStudentEntity.class)
//                .eq(CourseStudentEntity::getCourseId, courseEntity.getId()));
//        List<Long> studentIds = courseStudentEntities.stream().map(CourseStudentEntity::getStudentId).collect(Collectors.toList());
//        List<UserEntity> userEntities = userDao.selectBatchIds(studentIds);
        List<CourseStudentDTO> dtoList = baseDao.getStudentByCourseName(courseName);
        return dtoList;
    }
}