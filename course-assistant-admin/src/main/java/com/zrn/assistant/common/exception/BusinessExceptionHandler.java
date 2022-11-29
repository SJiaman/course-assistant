

package com.zrn.assistant.common.exception;


import com.zrn.assistant.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * 异常处理器


 */
@RestControllerAdvice
public class BusinessExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public Result handleRenException(BusinessException ex){
		return Result.fail(ex.getCode(), ex.getMsg());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException ex){
		return Result.fail(ErrorCode.DB_RECORD_EXISTS, "记录不存在");
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex){
		return Result.fail(400, ex.getMessage());
	}

}