

package com.zrn.assistant.controller;

import com.zrn.assistant.common.oss.AliyunCloudStorageService;
import com.zrn.assistant.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传
 * 
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/oss")
@Api(tags="文件上传")
public class SysOssController {

	@PostMapping("upload")
	@ApiOperation(value = "上传文件")
	public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return Result.fail(10001, "上传失败");
		}

		//上传文件
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String url = AliyunCloudStorageService.uploadSuffix(file.getBytes(), extension);

		return  Result.success(url);
	}

}