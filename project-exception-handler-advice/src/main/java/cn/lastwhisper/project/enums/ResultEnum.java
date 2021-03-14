package cn.lastwhisper.project.enums;

/**
 * 结果枚举
 * @author lastwhisper
 * @date 2020/5/19
 */
public enum ResultEnum {

    SUCCESS(0, "success"),

    UNKNOWN_ERROR(10001, "未知异常"),
    PARAM_ERROR(10002, "参数错误"),
    URL_ROUTE_ERROR(10003, "url路由参数绑定错误"),
    NOT_ROUTE_ERROR(10004, "没有找到对应的访问路径"),


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
