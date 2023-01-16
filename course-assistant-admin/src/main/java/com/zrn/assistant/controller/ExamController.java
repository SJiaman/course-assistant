package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.ExamDTO;
import com.zrn.assistant.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 考试表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("demo/exam")
@Api(tags="考试表")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<ExamDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<ExamDTO> page = examService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<ExamDTO> get(@PathVariable("id") Long id){
        ExamDTO data = examService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody ExamDTO dto){
        examService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody ExamDTO dto){
        examService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        examService.delete(ids);
        return Result.success();
    }

}