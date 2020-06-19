# 参数校验

-  JSR303/JSR-349 是校验规范，位于 javax.validation.constraints 

- hibernate validation 是规范的实现，并增加了一些其他校验注解，位于 org.hibernate.validator.constraints
- spring validation 对 hibernate validation 进行了二次封装，并结合 SpringMVC

**JSR 提供的校验注解** :

```
@Null   被注释的元素必须为 null    
@NotNull    被注释的元素必须不为 null    
@AssertTrue     被注释的元素必须为 true    
@AssertFalse    被注释的元素必须为 false    
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值    @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内    
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内    
@Past   被注释的元素必须是一个过去的日期    
@Future     被注释的元素必须是一个将来的日期    
@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
@Email  被注释的元素必须是电子邮箱地址 
```

**Hibernate Validator 提供的校验注解** ：

```
@NotBlank(message =)   验证字符串非 null，且长度必须大于 0       
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内    
@NotEmpty   被注释的字符串的必须非空    
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
@Email  被注释的元素必须是电子邮箱地址 
```

# 测试











# 参考

https://beanvalidation.org/2.0/spec/#builtinconstraints
https://www.cnkirito.moe/spring-validation/

