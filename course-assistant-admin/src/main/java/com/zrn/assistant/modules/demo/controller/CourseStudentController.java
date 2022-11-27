package com.zrn.assistant.modules.demo.controller;

import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.ExcelUtils;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.modules.demo.dto.CourseStudentDTO;
import com.zrn.assistant.modules.demo.excel.CourseStudentExcel;
import com.zrn.assistant.modules.demo.service.CourseStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 课程-学生关联表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@RestController
@RequestMapping("demo/coursestudent")
@Api(tags="课程-学生关联管理")
public class CourseStudentController {
    @Autowired
    private CourseStudentService courseStudentService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<CourseStudentDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CourseStudentDTO> page = courseStudentService.page(params);
        return Result.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CourseStudentDTO> get(@PathVariable("id") Long id){
        CourseStudentDTO data = courseStudentService.get(id);
        return Result.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody CourseStudentDTO dto){
        courseStudentService.save(dto);
        return  Result.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody CourseStudentDTO dto){
        courseStudentService.update(dto);
        return  Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        courseStudentService.delete(ids);
        return  Result.success();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CourseStudentDTO> list = courseStudentService.list(params);
        ExcelUtils.exportExcelToTarget(response, null, list, CourseStudentExcel.class);
    }

}