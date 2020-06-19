# 全局统一 API 返回值

# 1. 思路

API 接口统一返回值

- 定义对应的业务异常，使用枚举封装业务的状态码（状态码与前端对接）
- 使用`@ControllerAdvice`和`ResponseBodyAdvice`接口的实现类对正常业务结果的封装
- 使用`@ControllerAdvice`、`@ExceptionHandler`、`@ResponseBody`对异常业务结构的封装

##  1.1 为什么使用枚举定义状态码

枚举可以解释状态码的意义

```java
public enum ResultEnum {
 	SUCCESS(0, "success"),

    UNKNOWN_ERROR(1, "未知异常"),

    PARAM_ERROR(2, "参数错误"),

    USER_NOT_EXIST(3,"用户不存在"),

    PASSWORD_ERROR(4, "密码错误"),
    ;
    
    private Integer code;
    private String msg;
    
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
```

一个状态码可能错误的原因不同，比如 状态码`2`的意义是**参数错误**，在登录业务中，可能是用户名为空，也可能是密码为空。



# 2. 测试

内置用户：admin/123456

不存在用户：unkonw/123456

## 2.1 success

http://localhost:8080/user/login?username=admin&password=123456

```json
{
  "code": 0,
  "msg": "success",
  "data": "admin"
}
```

## 2.2 参数错误

http://localhost:8080/user/login?username=&password=123456

```json
{
  "code": 2,
  "msg": "用户名为空",
  "data": null
}
```

http://localhost:8080/user/login?username=admin&password=

```json
{
  "code": 2,
  "msg": "密码为空",
  "data": null
}
```

## 2.3 用户不存在

http://localhost:8080/user/login?username=unkonw&password=123456

密码可以随便写

```json
{
  "code": 3,
  "msg": "用户不存在",
  "data": null
}
```

## 2.4 密码错误

http://localhost:8080/user/login?username=admin&password=root

```json
{
  "code": 4,
  "msg": "密码错误",
  "data": null
}
```

## 2.5 未知异常

http://localhost:8080/user/1

```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "admin",
    "password": "123456",
    "phone": "15037594395",
    "address": "河南平顶山"
  }
}
```

http://localhost:8080/user/2

在日志中可以看到`java.lang.NullPointerException: null`

```json
{
  "code": 1,
  "msg": "未知异常",
  "data": null
}
```

# 参考文章

- SpringMVC 全局异常：https://blog.csdn.net/hbtj_1216/article/details/81102063
- SpringMVC API 接口设计： https://www.toutiao.com/i6694404645827117572/
- Spring Validation：https://www.cnkirito.moe/spring-validation/

