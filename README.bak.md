# 1. 全局异常处理设计

在开发人员眼里只有两种异常

- 业务异常
- 未知异常

所有异常都需要一个全局异常处理，业务异常不打印堆栈信息，未知异常需要打印堆栈信息，甚至做邮件通知，因为一个健壮的系统中不应该出现未知异常，未知异常导致的错误很有可能会被抛用户。

## 1.1 设计思路

**（1）一个业务异常**

定义一个业务异常`BizException`，多个异常状态码枚举。

所有业务异常都抛`BizException`，通过不同的异常状态码，区分业务异常，适合业务异常比较多的场景。

```java
public class BizException extends RuntimeException {
    private Integer code;
    private String msg;
    // constructor、getter/setter
}
```

全局异常处理器，只需要抓`BizException`异常即可，其他异常都是未知异常。

**（2）多个业务异常**

定义多个业务异常`OrderException、ProductException`等，多个异常状态码枚举。





# 2. 设计 API 统一响应格式



 