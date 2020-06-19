package cn.lastwhisper.project.exception;

import cn.lastwhisper.project.enums.ResultEnum;

public class BizException extends RuntimeException {

    private Integer code;

    private String msg;

    public BizException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}