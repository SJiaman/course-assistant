package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.MessageDTO;
import com.zrn.assistant.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("message")
@Api(tags="消息管理")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<MessageDTO>> page( @RequestParam Map<String, Object> params){
//        PageData<MessageDTO> page = messageService.page(params);
        PageData<MessageDTO> pageData = messageService.messagePage(params);
        return Result.success(pageData);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<MessageDTO> get(@PathVariable("id") Long id){
        MessageDTO data = messageService.get(id);
        return Result.success(data);
    }

    @GetMapping("user")
    @ApiOperation("用户消息")
    public Result<PageData<MessageDTO>> getUserMessage(@RequestParam Map<String, Object> params) {
        PageData<MessageDTO> pageData = messageService.getMessageList(params);
        return Result.success(pageData);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody MessageDTO dto){
//        messageService.save(dto);
        messageService.saveMessage(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody MessageDTO dto){
        messageService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        messageService.delete(ids);
        return Result.success();
    }


    @PostMapping("readed/{id}")
    @ApiOperation("读消息")
    public Result update(@PathVariable("id") Long id){
        messageService.readed(id);
        return Result.success();
    }
}