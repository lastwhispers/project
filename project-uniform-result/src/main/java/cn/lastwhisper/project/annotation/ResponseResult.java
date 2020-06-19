package cn.lastwhisper.project.annotation;

import java.lang.annotation.*;

/**
 * 包装返回值
 *
 * @author lastwhisper
 * @date 2020/5/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ResponseResult {


}
