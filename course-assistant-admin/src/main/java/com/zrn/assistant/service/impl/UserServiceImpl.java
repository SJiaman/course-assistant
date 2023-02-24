package com.zrn.assistant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zrn.assistant.common.exception.BusinessException;
import com.zrn.assistant.common.service.impl.CrudServiceImpl;
import com.zrn.assistant.common.utils.ConvertUtils;
import com.zrn.assistant.dao.UserDao;
import com.zrn.assistant.dto.LoginDTO;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.entity.UserEntity;
import com.zrn.assistant.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户表
 *
 * @author Zhang Ruina
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


    @Override
    public UserDTO login(LoginDTO dto) {
        UserEntity userEntity = baseDao.selectOne(Wrappers.lambdaQuery(UserEntity.class)
                .eq(UserEntity::getUsername, dto.getUsername()));
        if (userEntity == null) {
            throw new BusinessException("用户不存在");
        }
        if (userEntity.getPassword().equals(dto.getPassword())) {
            return ConvertUtils.sourceToTarget(userEntity, UserDTO.class);
        }
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void register(LoginDTO login) {
        UserEntity userEntity = baseDao.selectOne(Wrappers.lambdaQuery(UserEntity.class)
                .eq(UserEntity::getUsername, login.getUsername()));
        if (userEntity != null) {
            throw new BusinessException("用户已存在");
        }
        UserEntity user = ConvertUtils.sourceToTarget(login, UserEntity.class);
        insert(user);
    }
}