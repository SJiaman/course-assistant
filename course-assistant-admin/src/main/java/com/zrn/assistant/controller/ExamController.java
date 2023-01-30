package com.zrn.assistant.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.dto.ExamDTO;
import com.zrn.assistant.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("exam")
@Api(tags="考试表")
@Slf4j
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<ExamDTO>> page( @RequestParam Map<String, Object> params){
        PageData<ExamDTO> page = examService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<ExamDTO> get(@PathVariable("id") Long id){
//        ExamDTO data = examService.get(id);
        ExamDTO data = examService.getExamById(id);
        return Result.success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public Result save(@RequestBody ExamDTO dto){
//        examService.save(dto);
        log.info("dto, {}", dto);
        examService.saveExam(dto);
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
    public Result delete(@RequestBody Integer id){
//        examService.delete(ids);
        examService.deleteExam(id);
        return Result.success();
    }

}