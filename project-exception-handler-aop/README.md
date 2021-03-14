# 全局异常处理

# 1. 思路

Aop 处理全局异常

对于`SpringMVC`内，业务代码外的异常不能处理。  
比如，web 接口参数类型错误，代码还未执行到接口方法 ，所以 AOP 抓不到该异常。

Aop 打印参数等日志

# 2. 测试

## 2.1 BizException

http://localhost:8080/user/login?id

## 2.2 其他Exception

http://localhost:8080/user/login?id=10


# 参考文章

- 程序员你为什么这么累：https://xwjie.github.io/rule/aop.html

