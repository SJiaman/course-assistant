

package com.zrn.assistant.common.utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应数据


 */
@ApiModel(value = "响应")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    @ApiModelProperty(value = "编码：0表示成功，其他值表示失败")
    private int code = 0;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String msg = "success";
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }
    public boolean isSuccess() {
        return code == 0;
    }

    public static <T> Result<T> success(int code, String message, T data) {
        Result rest = new Result();
        rest.setCode(code);
        rest.setMsg(message);
        rest.setData(data);
        return rest;
    }

    public static <T> Result<T> success(T data) {
        Result rest = new Result();
        rest.setData(data);
        return rest;
    }

    public static <T> Result<T> success() {
        Result rest = new Result();
        return rest;
    }

    public static <T> Result<T> fail() {
        Result<T> rest = new Result();
        rest.setCode(400);
        rest.setMsg("操作失败");
        return rest;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> rest = new Result();
        rest.setCode(code);
        rest.setMsg(message);
        return rest;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
