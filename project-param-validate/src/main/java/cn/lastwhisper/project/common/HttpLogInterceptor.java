package cn.lastwhisper.project.common;

import cn.lastwhisper.project.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器，需要加到config里面
 * @author lastwhisper
 */
public class HttpLogInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "requestStartTime";

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller 执行前
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler HandlerMethod
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        return true;
    }

    /**
     * Controller 执行后，但是 ModelAndView 还未渲染
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler HandlerMethod
     * @param modelAndView ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * Controller 和 ModelAndView 都执行完毕后
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler HandlerMethod
     * @param ex 业务代码内的异常，比如业务内 10/0，触发“java.lang.ArithmeticException: / by zero”，在这里可以接收到，前提是没有被@ControllerAdvice接收
     *           业务代码外的异常，比如 SpringMVC 参数接收错误，这里是拿不到，只有@ControllerAdvice可以拿到
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        long start = (Long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        logger.info("request completed. url:{}, params:{}, cost:{} ms", url, JsonMapper.obj2String(request.getParameterMap()), end - start);
    }


}
