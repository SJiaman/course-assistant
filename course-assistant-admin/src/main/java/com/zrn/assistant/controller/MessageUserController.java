package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.MessageUserDTO;
import com.zrn.assistant.service.MessageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("demo/messageuser")
@Api(tags="用户消息")
public class MessageUserController {
    @Autowired
    private MessageUserService messageUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<MessageUserDTO>> page( @RequestParam Map<String, Object> params){
        PageData<MessageUserDTO> page = messageUserService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<MessageUserDTO> get(@PathVariable("id") Long id){
        MessageUserDTO data = messageUserService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody MessageUserDTO dto){
        messageUserService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody MessageUserDTO dto){
        messageUserService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        messageUserService.delete(ids);
        return Result.success();
    }

}