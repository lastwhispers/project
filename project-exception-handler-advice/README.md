# 全局异常处理

# 1. 思路

`@ControllerAdvice`、`@ExceptionHandler`处理全局异常

对于`SpringMVC`内，业务代码外的异常也可以处理。   
比如，web 接口参数类型错误，代码还未执行到接口方法 ，但是异常会被抛到`@ExceptionHandler`。


# 2. 测试

## 2.1 BizException

http://localhost:8080/user/login?id

## 2.2 ServletRequestBindingException

http://localhost:8080/user/login

## 2.3 NoHandlerFoundException

http://localhost:8080/

## 2.4 其他Exception

http://localhost:8080/user/login?id=10

