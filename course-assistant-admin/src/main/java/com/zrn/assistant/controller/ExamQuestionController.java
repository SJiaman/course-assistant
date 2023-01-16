package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.ExamQuestionDTO;
import com.zrn.assistant.service.ExamQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 考试问题表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("demo/examquestion")
@Api(tags="考试问题表")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<ExamQuestionDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<ExamQuestionDTO> page = examQuestionService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<ExamQuestionDTO> get(@PathVariable("id") Long id){
        ExamQuestionDTO data = examQuestionService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody ExamQuestionDTO dto){
        examQuestionService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody ExamQuestionDTO dto){
        examQuestionService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        examQuestionService.delete(ids);
        return Result.success();
    }

}