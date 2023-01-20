package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.ExamRecordDTO;
import com.zrn.assistant.service.ExamRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;

/**
 * 考试记录表
 *
 * @author Zhang Ruina
 * @since  2022-12-14
 */
@RestController
@RequestMapping("demo/examrecord")
@Api(tags="考试记录表")
public class ExamRecordController {
    @Autowired
    private ExamRecordService examRecordService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<ExamRecordDTO>> page( @RequestParam Map<String, Object> params){
        PageData<ExamRecordDTO> page = examRecordService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<ExamRecordDTO> get(@PathVariable("id") Long id){
        ExamRecordDTO data = examRecordService.get(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody ExamRecordDTO dto){
        examRecordService.save(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public Result update(@RequestBody ExamRecordDTO dto){
        examRecordService.update(dto);
        return Result.success();
    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        examRecordService.delete(ids);
        return Result.success();
    }

}