package cn.lastwhisper.project.aop;

import cn.lastwhisper.project.enums.ResultEnum;
import cn.lastwhisper.project.exception.BizException;
import cn.lastwhisper.project.vo.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 处理和包装异常
 *
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
@Aspect
@Component
public class ControllerAOP {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public cn.lastwhisper.project.vo.ResultVO *(..))")
    public void matchCondition() {

    }

    @Around("matchCondition()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultVO<?> result;
        try {
            result = (ResultVO<?>) pjp.proceed();
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        } finally {
            // 本次操作 入参 用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("[{}] args [{}] use time: {}", pjp.getSignature(), pjp.getArgs(), elapsedTime);
        }

        return result;
    }

    private ResultVO<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultVO<?> result = new ResultVO<>();
        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        if (e instanceof BizException) {
            result.setMsg(((BizException) e).getMsg());
            result.setCode(((BizException) e).getCode());
        } else {
            logger.error(pjp.getSignature() + " error ", e);
            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
            result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        }
        return result;
    }


}
