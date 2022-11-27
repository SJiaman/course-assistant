package com.zrn.assistant.modules.demo.controller;


import com.zrn.assistant.common.page.PageData;
import com.zrn.assistant.common.utils.ExcelUtils;
import com.zrn.assistant.common.utils.Result;
import com.zrn.assistant.modules.demo.dto.UserDTO;
import com.zrn.assistant.modules.demo.excel.UserExcel;
import com.zrn.assistant.modules.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 用户表
 *
 * @author Zhang Ruina 727976120@qq.com
 * @since  2022-11-27
 */
@RestController
@RequestMapping("demo/user")
@Api(tags="用户表")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("page")
    @ApiOperation("分页")
    public Result<PageData<UserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<UserDTO> page = userService.page(params);
        return new Result<PageData<UserDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<UserDTO> get(@PathVariable("id") Long id){
        UserDTO data = userService.get(id);
        return new Result<UserDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public Result save(@RequestBody UserDTO dto){
        userService.save(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result update(@RequestBody UserDTO dto){
        userService.update(dto);
        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        userService.delete(ids);
        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<UserDTO> list = userService.list(params);
        ExcelUtils.exportExcelToTarget(response, null, list, UserExcel.class);
    }

}