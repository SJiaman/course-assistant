package com.zrn.assistant.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.dto.MessageDTO;
import com.zrn.assistant.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@Mapper
public interface MessageDao extends BaseDao<MessageEntity> {

    IPage<MessageDTO> messagePage(IPage<MessageEntity> page, @Param("ew") Wrapper<MessageDTO> queryWrapper);

    IPage<MessageDTO> userMessage(IPage<MessageEntity> page);


//    List<MessageDTO> userMessage(Long id);
	
}