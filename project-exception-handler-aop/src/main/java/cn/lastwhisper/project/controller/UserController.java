package cn.lastwhisper.project.controller;

import cn.lastwhisper.project.domain.User;
import cn.lastwhisper.project.enums.ResultEnum;
import cn.lastwhisper.project.exception.BizException;
import cn.lastwhisper.project.form.UserForm;
import cn.lastwhisper.project.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @RequestMapping("/login")
    public ResultVO<String> create(@Valid UserForm userForm, BindingResult bindingResult) {
        // 1.参数校验
        if (bindingResult.hasErrors()) {
            logger.info("[用户登录]参数不正确, userForm={}", userForm);
            throw new BizException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        /*-------------------------下面是 Service 干的事------------------------------*/

        // 2.根据用户名查询数据库获取用户
        User user = getUserByUsername(userForm.getUsername());
        if (user == null) {
            logger.info("[用户登录]用户不存在");
            throw new BizException(ResultEnum.USER_NOT_EXIST);
        }

        // 3.对比密码
        if (!userForm.getPassword().equals(user.getPassword())) {
            logger.info("[用户登录]用户密码错误");
            throw new BizException(ResultEnum.PASSWORD_ERROR);
        }

        return new ResultVO<>(user.getUsername());
    }

    /**
     * 根据用户 id 查询用户
     */
    @RequestMapping("/{id}")
    public ResultVO<User> user(@PathVariable Long id) {
        /*-------------------------下面是 Service 干的事------------------------------*/
        // 此处是为了，测试未知异常
        if (id % 2 == 0) {
            int a = 10/0;
        }
        User user = getUserById(id);
        return new ResultVO<>(user);
    }

    /**
     * 这个是 Service 干的事
     */
    public User getUserById(Long id) {
        return new User(id, "admin", "123456", "15037594395", "河南平顶山");
    }

    /**
     * 这个是 Service 干的事
     */
    public User getUserByUsername(String username) {
        if ("unkonw".equals(username)) {
            return null;
        }
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        return user;
    }

}
