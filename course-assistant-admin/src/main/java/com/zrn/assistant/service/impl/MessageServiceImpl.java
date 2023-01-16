package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrn.assistant.common.page.PageData;
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
 * @author Zhang Ruina
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


    @Override
    public PageData<MessageDTO> messagePage(Map<String, Object> params) {
        IPage<MessageEntity> page = getPage(params, null, false);
        IPage<MessageDTO> messageDTOIPage = baseDao.messagePage(page);
        return new PageData<>(messageDTOIPage.getRecords(), messageDTOIPage.getTotal());
    }
}
