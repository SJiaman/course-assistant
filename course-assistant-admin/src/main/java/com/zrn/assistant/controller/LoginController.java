package com.zrn.assistant.controller;

import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.LoginDTO;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang Ruina
 * @Date 2022/11/30 19:15
 * @Desc
 */
@RestController
@Api(tags = "登录管理")
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO login) {
        userService.register(login);
        return Result.success();
    }


    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO dto) {
        UserDTO userDTO = userService.login(dto);
        return Result.success(userDTO);
    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    public Result logout() {
        userService.logout();
        return Result.success();
    }
}
