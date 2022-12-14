package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.dao.MessageUserDao;
import com.zrn.assistant.dto.MessageUserDTO;
import com.zrn.assistant.entity.MessageUserEntity;
import com.zrn.assistant.service.MessageUserService;
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
public class MessageUserServiceImpl extends CrudServiceImpl<MessageUserDao, MessageUserEntity, MessageUserDTO>
        implements MessageUserService {

    @Override
    public QueryWrapper<MessageUserEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MessageUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}