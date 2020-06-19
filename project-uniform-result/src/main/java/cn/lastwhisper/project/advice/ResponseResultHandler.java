package cn.lastwhisper.project.advice;

import cn.lastwhisper.project.annotation.ResponseResult;
import cn.lastwhisper.project.constant.ResponseConstants;
import cn.lastwhisper.project.utils.ResultUtil;
import cn.lastwhisper.project.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Controller body 包装
 * @author lastwhisper
 * @date 2020/5/19
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        ResponseResult responseResult = (ResponseResult) requestAttributes.getRequest().getAttribute(ResponseConstants.RESPONSE_WRAPPER);
        // 判断是否需要返回值包装
        return responseResult != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // body 是否已经被全局异常封装，或者用户自己封装
        if (body instanceof ResultVO) {
            return body;
        }
        return ResultUtil.success(body);
    }

}
