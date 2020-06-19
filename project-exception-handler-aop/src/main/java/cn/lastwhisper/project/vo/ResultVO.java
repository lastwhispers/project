package cn.lastwhisper.project.vo;

import cn.lastwhisper.project.enums.ResultEnum;

import java.io.Serializable;

/**
 * http响应
 *
 * @author lastwhisper
 */
public class ResultVO<T> implements Serializable {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应内容
     */
    private T data;

    public ResultVO() {
    }

    public ResultVO(T data) {
        this.data = data;
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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