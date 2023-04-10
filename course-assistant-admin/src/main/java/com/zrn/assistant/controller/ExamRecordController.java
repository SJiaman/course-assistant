package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.AnswerDTO;
import com.zrn.assistant.dto.ExamRecordAnalysisDTO;
import com.zrn.assistant.dto.ExamRecordDTO;
import com.zrn.assistant.dto.RecordDTO;
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
@RequestMapping("exam/record")
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

    @GetMapping("list")
    @ApiOperation("分页")
    public Result<PageData<ExamRecordDTO>> list( @RequestParam Map<String, Object> params){
        PageData<ExamRecordDTO> page = examRecordService.getExamRecords(params);
        return Result.success(page);
    }

    @GetMapping("analysis")
    @ApiOperation("分析")
    public Result<PageData<ExamRecordAnalysisDTO>> analysis(@RequestParam Map<String, Object> params){
        PageData<ExamRecordAnalysisDTO> page = examRecordService.getExamRecordAnalysis(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<RecordDTO> get(@PathVariable("id") Long id){
//        ExamRecordDTO data = examRecordService.get(id);
        RecordDTO data = examRecordService.getRecord(id);
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


    @PostMapping("do")
    @ApiOperation("保存")
    public Result save(@RequestBody AnswerDTO dto){
        Integer score = examRecordService.doExam(dto);
        return Result.success(score);
    }

    @GetMapping("test")
    @ApiOperation("test")
    public Result getRecordCount() {
        return Result.success(examRecordService.getRecordCount());
    }
}