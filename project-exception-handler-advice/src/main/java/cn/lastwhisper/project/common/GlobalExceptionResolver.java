package cn.lastwhisper.project.common;

import cn.lastwhisper.project.enums.ResultEnum;
import cn.lastwhisper.project.exception.BizException;
import cn.lastwhisper.project.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局Controller层异常处理类
 * （1）通过 @ControllerAdvice 指定该类为 Controller 增强类。
 * （2）通过 @ExceptionHandler 自定捕获的异常类型。
 * （3）通过 @ResponseBody 返回 json 到前端。
 * 被@ControllerAdvice注解的全局异常处理类也是一个 Controller
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理所有业务内和业务外未知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Integer code;
        String msg;
        if (e instanceof BizException) {
            // http://localhost:8080/user/login?id
            BizException bizException = (BizException) e;
            code = bizException.getCode();
            msg = bizException.getMsg();
        } else if (e instanceof ServletRequestBindingException) {
            // http://localhost:8080/user/login
            code = ResultEnum.URL_ROUTE_ERROR.getCode();
            msg = ResultEnum.URL_ROUTE_ERROR.getMsg();
        } else if (e instanceof NoHandlerFoundException) {
            // http://localhost:8080/
            code = ResultEnum.NOT_ROUTE_ERROR.getCode();
            msg = ResultEnum.NOT_ROUTE_ERROR.getMsg();
        } else {
            logger.error("nuknown error", e);
            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            // http://localhost:8080/user/login?id=10
            code = ResultEnum.UNKNOWN_ERROR.getCode();
            msg = ResultEnum.UNKNOWN_ERROR.getMsg();
        }
        return ResultVO.create(code, msg);
    }

}
