package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.UserDTO;
import com.zrn.assistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 用户表
 *
 * @author Zhang Ruina
 * @since  2022-11-27
 */
@RestController
@RequestMapping("user")
@Api(tags="用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<UserDTO>> page( @RequestParam Map<String, Object> params){
        PageData<UserDTO> page = userService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<UserDTO> get(@PathVariable("id") Long id){
        UserDTO data = userService.get(id);
        return Result.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody UserDTO dto){
        userService.save(dto);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody UserDTO dto){
        userService.update(dto);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        userService.delete(ids);
        return Result.success();
    }
}