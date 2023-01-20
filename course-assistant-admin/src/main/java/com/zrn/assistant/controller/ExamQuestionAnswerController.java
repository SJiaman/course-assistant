package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.ExamQuestionAnswerDTO;
import com.zrn.assistant.service.ExamQuestionAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 考试问题记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("demo/examquestionanswer")
@Api(tags="考试问题记录表")
public class ExamQuestionAnswerController {
    @Autowired
    private ExamQuestionAnswerService examQuestionAnswerService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<ExamQuestionAnswerDTO>> page( @RequestParam Map<String, Object> params){
        PageData<ExamQuestionAnswerDTO> page = examQuestionAnswerService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<ExamQuestionAnswerDTO> get(@PathVariable("id") Long id){
        ExamQuestionAnswerDTO data = examQuestionAnswerService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody ExamQuestionAnswerDTO dto){
        examQuestionAnswerService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody ExamQuestionAnswerDTO dto){
        examQuestionAnswerService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        examQuestionAnswerService.delete(ids);
        return Result.success();
    }

}