package com.zrn.assistant.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrn.assistant.common.dao.BaseDao;
import com.zrn.assistant.dto.MessageDTO;
import com.zrn.assistant.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Mapper
public interface MessageDao extends BaseDao<MessageEntity> {

    IPage<MessageDTO> messagePage(IPage<MessageEntity> page);
	
}