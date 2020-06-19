package cn.lastwhisper.project.enums;

/**
 * 结果枚举
 * @author lastwhisper
 * @date 2020/5/19
 */
public enum ResultEnum {

    SUCCESS(0, "success"),

    UNKNOWN_ERROR(1, "未知异常"),

    PARAM_ERROR(2, "非法参数"),




    PHONE_NULL(3, "手机号不能为空"),

    PHONE_EXIST(4, "此手机号已经被其他用户注册"),

    PHONE_LENGTH_ILLEGAL(5, "手机号长度应为 11 位"),

    PHONE_ILLEGAL(6, "手机号无法注册，非法手机号"),

    PHONE_NOT_CHINA_ILLEGAL(7, "手机号为中国大陆非法手机号"),


    ;

    /**
     * 响应码，用于与前端对接
     */
    private Integer code;

    /**
     * 响应码对应的意义，这也是为什么常量定义使用枚举，而不是定义在接口或者类中。
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
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
