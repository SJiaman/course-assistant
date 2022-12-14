package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.MessageDao;
import com.zrn.assistant.dto.MessageDTO;
import com.zrn.assistant.entity.MessageEntity;
import com.zrn.assistant.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-12-14
 */
@Service
public class MessageServiceImpl extends CrudServiceImpl<MessageDao, MessageEntity, MessageDTO> implements MessageService {

    @Override
    public QueryWrapper<MessageEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MessageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}