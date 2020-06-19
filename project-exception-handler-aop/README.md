# 全局异常处理

# 1. 思路

Aop 处理全局异常

对于`SpringMVC`内，业务代码外的异常不能处理。  
比如，web 接口参数类型错误，代码还未执行到接口方法 ，所以 AOP 抓不到该异常。

Aop 打印参数等日志


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

## 2.4 用户密码错误

http://localhost:8080/user/login?username=admin&password=root

```json
{
  "code": 4,
  "msg": "用户密码错误",
  "data": null
}
```

## 2.5 业务内的未知异常

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

在日志中可以看到`java.lang.ArithmeticException: / by zero`

```json
{
  "code": 1,
  "msg": "/ by zero",
  "data": null
}
```


## 2.6 业务外的未知异常

http://localhost:8080/user/啦啦啦啦

在日志中可以看到`Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; 
nested exception is java.lang.NumberFormatException: For input string: "啦啦啦啦"`  

HTTP 响应400 ，里面直接就会把异常所有报出来，无法处理业务外的未知异常


# 参考文章

- 程序员你为什么这么累：https://xwjie.github.io/rule/aop.html

