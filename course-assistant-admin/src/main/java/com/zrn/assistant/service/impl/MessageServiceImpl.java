package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.CourseDao;
import com.zrn.assistant.dao.CourseStudentDao;
import com.zrn.assistant.dao.MessageDao;
import com.zrn.assistant.dao.MessageUserDao;
import com.zrn.assistant.dto.MessageDTO;
import com.zrn.assistant.entity.*;
import com.zrn.assistant.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Service
public class MessageServiceImpl extends CrudServiceImpl<MessageDao, MessageEntity, MessageDTO> implements MessageService {
    @Resource
    private CourseStudentDao courseStudentDao;

    @Resource
    private MessageUserDao messageUserDao;

    @Resource
    private CourseDao courseDao;

    @Override
    public QueryWrapper<MessageEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String courseId = (String) params.get("courseId");
        String teacherId = (String)params.get("teacherId");

        QueryWrapper<MessageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id)
                .eq(StringUtils.isNotBlank(courseId), "course_id", courseId);

        if (StringUtils.isNotBlank(teacherId)) {
            List<CourseEntity> courseEntities = courseDao.selectList(
                    Wrappers.lambdaQuery(CourseEntity.class).eq(CourseEntity::getTeacherId, teacherId));
            if (!courseEntities.isEmpty()) {
                List<Long> courseIds = courseEntities.stream().map(CourseEntity::getId).collect(Collectors.toList());
                wrapper.in(CollectionUtils.isEmpty(courseIds) , "course_id", courseIds);
            } else {
                // 没有课程问题
                wrapper.eq("course_id", 0);
            }
        }
        return wrapper;
    }


    @Override
    public PageData<MessageDTO> messagePage(Map<String, Object> params) {
        IPage<MessageEntity> page = getPage(params, null, false);
        QueryWrapper<MessageDTO> wrapper = new QueryWrapper<>();
        String courseId = (String)params.get("courseId");
        String teacherId = (String)params.get("teacherId");

        if (StringUtils.isNotBlank(teacherId)) {
            List<CourseEntity> courseEntities = courseDao.selectList(
                    Wrappers.lambdaQuery(CourseEntity.class).eq(CourseEntity::getTeacherId, teacherId));
            if (!courseEntities.isEmpty()) {
                List<Long> courseIds = courseEntities.stream().map(CourseEntity::getId).collect(Collectors.toList());
                wrapper.in(CollectionUtils.isEmpty(courseIds) , "course_id", courseIds);
            } else {
                // 没有课程问题
                wrapper.eq("course_id", 0);
            }
        }
        wrapper.eq("a.deleted", false);
        IPage<MessageDTO> messageDTOIPage = baseDao.messagePage(page, wrapper);
        return new PageData<>(messageDTOIPage.getRecords(), messageDTOIPage.getTotal());
    }

    @Override
    public void saveMessage(MessageDTO message) {
        MessageEntity messageEntity = ConvertUtils.sourceToTarget(message, MessageEntity.class);
        insert(messageEntity);

        // 保存用户消息
        List<CourseStudentEntity> courseStudentEntities = courseStudentDao.selectList(
                Wrappers.lambdaQuery(CourseStudentEntity.class).eq(CourseStudentEntity::getCourseId, message.getCourseId()));
        for (CourseStudentEntity courseStudentEntity : courseStudentEntities) {
            MessageUserEntity messageUserEntity = new MessageUserEntity();
            messageUserEntity.setReceiveUserId(courseStudentEntity.getStudentId());
            messageUserEntity.setMessageId(messageEntity.getId());
            messageUserDao.insert(messageUserEntity);
        }
    }

    @Override
    public PageData<MessageDTO> getMessageList(Map<String, Object> params) {
        IPage<MessageEntity> page = getPage(params, null, false);
        IPage<MessageDTO> messageDTOIPage = baseDao.userMessage(page);
        return new PageData<>(messageDTOIPage.getRecords(), messageDTOIPage.getTotal());
    }

    @Override
    public void readed(Long id) {
        MessageUserEntity messageUserEntity = messageUserDao.selectById(id);
        messageUserEntity.setReaded(true);
        messageUserEntity.setReadTime(new Date());
        messageUserDao.updateById(messageUserEntity);
    }

    @Override
    public void deleteMessage(Long id) {
        List<MessageEntity> messageEntities = baseDao.selectList(Wrappers.lambdaQuery(MessageEntity.class)
                .eq(MessageEntity::getCourseId, id));
        baseDao.delete(Wrappers.lambdaQuery(MessageEntity.class)
                .eq(MessageEntity::getCourseId, id));
        List<Long> ids = messageEntities.stream().map(MessageEntity::getId).collect(Collectors.toList());
        ids.forEach( x -> {
            messageUserDao.delete(Wrappers.lambdaQuery(MessageUserEntity.class)
                    .eq(MessageUserEntity::getMessageId, x));
        });
    }

    @Override
    public Integer unreadCount(Long id) {
        List<MessageUserEntity> messageUserEntities = messageUserDao.selectList(Wrappers.
                lambdaQuery(MessageUserEntity.class).eq(MessageUserEntity::getReceiveUserId, id)
        .eq(MessageUserEntity::getReaded, false));
        return messageUserEntities.size();
    }

    @Override
    public void deleteMessageById(Long id) {
        MessageEntity messageEntity = baseDao.selectById(id);
        baseDao.delete(Wrappers.lambdaQuery(MessageEntity.class)
                .eq(MessageEntity::getId, id));

        messageUserDao.delete(Wrappers.lambdaQuery(MessageUserEntity.class)
                .eq(MessageUserEntity::getMessageId, messageEntity.getId()));

    }
}
