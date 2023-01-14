package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.QuestionDTO;
import com.zrn.assistant.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 题目表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2023-01-14
 */
@RestController
@RequestMapping("demo/question")
@Api(tags="题目表")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<QuestionDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<QuestionDTO> page = questionService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<QuestionDTO> get(@PathVariable("id") Long id){
        QuestionDTO data = questionService.get(id);
        return Result.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody QuestionDTO dto){
        questionService.save(dto);
        return Result.success();
    }

    @PostMapping
    @ApiOperation("修改")
    public Result update(@RequestBody QuestionDTO dto){
        questionService.update(dto);
        return Result.success();
    }

    @PostMapping
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        questionService.delete(ids);
        return Result.success();
    }

}