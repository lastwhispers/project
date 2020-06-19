package cn.lastwhisper.project.common;

import cn.lastwhisper.project.enums.ResultEnum;
import cn.lastwhisper.project.exception.BizException;
import cn.lastwhisper.project.util.JsonMapper;
import cn.lastwhisper.project.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局Controller层异常处理类
 *  （1）通过 @ControllerAdvice 指定该类为 Controller 增强类。
 *  （2）通过 @ExceptionHandler 自定捕获的异常类型。
 *  （3）通过 @ResponseBody 返回 json 到前端。
 *  被@ControllerAdvice注解的全局异常处理类也是一个 Controller
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
    public ResultVO handleException(Exception e) {
        // TODO 未知的异常，应该格外注意，可以发送邮件通知等
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        if(request!=null){
            logger.error("request error. url:{}, params:{}", request.getRequestURI(), JsonMapper.obj2String(request.getParameterMap()));
        }
        logger.error(e.getMessage(), e);
        ResultVO<?> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        resultVO.setMsg(e.getMessage());
        return resultVO;
    }

    /**
     * 处理所有业务异常
     *
     * @param e 业务异常
     * @return json结果
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultVO handleOpdRuntimeException(BizException e) {
        // 不打印异常堆栈信息
        logger.error(e.getMsg());
        ResultVO<?> resultVO = new ResultVO<>();
        resultVO.setCode(e.getCode());
        resultVO.setMsg(e.getMsg());
        return resultVO;
    }
}
