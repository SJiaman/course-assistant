package com.zrn.assistant.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.modules.demo.dao.UserDao;
import com.zrn.assistant.modules.demo.dto.UserDTO;
import com.zrn.assistant.modules.demo.entity.UserEntity;
import com.zrn.assistant.modules.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@Service
public class UserServiceImpl extends CrudServiceImpl<UserDao, UserEntity, UserDTO> implements UserService {

    @Override
    public QueryWrapper<UserEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}