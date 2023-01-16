package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.QuestionAnswerDTO;
import com.zrn.assistant.service.QuestionAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 题目答案表
 *
 * @author Zhang Ruina
 * @since  2023-01-14
 */
@RestController
@RequestMapping("/question/answer")
@Api(tags="题目答案表")
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<QuestionAnswerDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<QuestionAnswerDTO> page = questionAnswerService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<QuestionAnswerDTO> get(@PathVariable("id") Long id){
        QuestionAnswerDTO data = questionAnswerService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody QuestionAnswerDTO dto){
        questionAnswerService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody QuestionAnswerDTO dto){
        questionAnswerService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        questionAnswerService.delete(ids);
        return Result.success();
    }

}