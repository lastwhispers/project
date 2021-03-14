package cn.lastwhisper.project.controller;

import cn.lastwhisper.project.enums.ResultEnum;
import cn.lastwhisper.project.exception.BizException;
import cn.lastwhisper.project.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 业务 Controller
 *
 * @author lastwhisper
 * @date 2020/5/19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/login")
    public ResultVO login(@RequestParam Long id) {
        if(Objects.isNull(id)){
            throw new BizException(ResultEnum.PARAM_ERROR);
        }
        if(id.equals(10L)){
            throw new NullPointerException("我想抛一个空指针异常");
        }
        return new ResultVO<>(id);
    }

}
