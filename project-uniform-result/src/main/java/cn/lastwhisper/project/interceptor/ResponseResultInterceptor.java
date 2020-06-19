package cn.lastwhisper.project.interceptor;

import cn.lastwhisper.project.annotation.ResponseResult;
import cn.lastwhisper.project.constant.ResponseConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Controller 拦截器
 * @author lastwhisper
 * @date 2020/5/19
 */
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    /**
     * 拦截请求，检查该接口是否有包装返回值注解标志
     *
     * @param request request
     * @param response response
     * @param handler HandlerMethod
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            // 判断是否在类上加了包装返回值注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(ResponseConstants.RESPONSE_WRAPPER, clazz.getAnnotation(ResponseResult.class));
            }else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(ResponseConstants.RESPONSE_WRAPPER, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }

}
