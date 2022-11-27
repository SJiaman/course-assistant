package com.zrn.assistant.modules.demo.controller;


import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.ExcelUtils;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.modules.demo.dto.CourseDTO;
import com.zrn.assistant.modules.demo.excel.CourseExcel;
import com.zrn.assistant.modules.demo.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 课程表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@RestController
@RequestMapping("demo/course")
@Api(tags="课程表")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<CourseDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CourseDTO> page = courseService.page(params);
        return new Result<PageData<CourseDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CourseDTO> get(@PathVariable("id") Long id){
        CourseDTO data = courseService.get(id);
        return new Result<CourseDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody CourseDTO dto){
        courseService.save(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody CourseDTO dto){
        courseService.update(dto);
        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        courseService.delete(ids);
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CourseDTO> list = courseService.list(params);
        ExcelUtils.exportExcelToTarget(response, null, list, CourseExcel.class);
    }

}