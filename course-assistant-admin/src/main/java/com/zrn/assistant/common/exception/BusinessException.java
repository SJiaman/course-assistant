

package com.zrn.assistant.common.exception;


import com.zrn.assistant.common.utils.MessageUtils;

/**
 * 自定义异常

 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private int code;
	private String msg;

	public BusinessException(int code) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public BusinessException(int code, String... params) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public BusinessException(int code, Throwable e) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public BusinessException(int code, Throwable e, String... params) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public BusinessException(String msg) {
		super(msg);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public BusinessException(String msg, Throwable e) {
		super(msg, e);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}