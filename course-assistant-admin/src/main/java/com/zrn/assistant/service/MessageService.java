package com.zrn.assistant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.service.CrudService;
import com.zrn.assistant.dto.MessageDTO;
import com.zrn.assistant.entity.MessageEntity;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
public interface MessageService extends CrudService<MessageEntity, MessageDTO> {

    PageData<MessageDTO> messagePage(Map<String, Object> params);

    void saveMessage(MessageDTO message);

    PageData<MessageDTO> getMessageList(Map<String, Object> params);

    void readed(Long id);
}