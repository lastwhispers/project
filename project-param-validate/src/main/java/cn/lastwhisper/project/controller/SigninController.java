/**
 * ‎Hangzhou Lejian Technology Co., Ltd.
 * Copyright (c) 2019 All Rights Reserved.
 */
package cn.lastwhisper.project.controller;

import cn.lastwhisper.project.bean.User;
import cn.lastwhisper.project.enums.ResultEnum;
import cn.lastwhisper.project.exception.BizException;
import cn.lastwhisper.project.service.IUserService;
import cn.lastwhisper.project.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

import static cn.lastwhisper.project.enums.ResultEnum.PARAM_ERROR;

/**
 * 用户注册入口
 *
 * 利用面向对象思路，设计和完成“手机号注册校验”业务逻辑。
 * 你需要校验多种情况的用户输入，如果涉及DB存储逻辑，你可以暂时使用内存的缓存方式来解决。
 *
 *  一、统一全局异常处理
 *  二、统一API返回值
 *  三、jsr-303 参数校验
 *
 * @author gaojun
 * @email 15037584397@163.com
 *
 */
@Controller
public class SigninController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    /**
     * TODO 请从这里开始补充代码
     *
     * 测试1：138 1234 1234  http://localhost:8080/signin?phone=138%201234%201234
     *	结果：通过此手机号注册成功
     *
     * 测试2：13812345abc  http://localhost:8080/signin?phone=13812345abc
     *	结果：通知本手机号无法注册，提示为非法手机号
     *
     * 测试3：13812345678  http://localhost:8080/signin?phone=13812345678
     *	结果：通知此手机号注册成功
     *
     * 测试4：138 1234 5678 http://localhost:8080/signin?phone=138%201234%205678
     *	结果：提示此手机号已经被其他用户注册
     *
     * 测试5：98765432101 http://localhost:8080/signin?phone=98765432101
     *	结果：此手机号码为中国大陆非法手机号码
     *
     * 测试6：空串 http://localhost:8080/signin?phone=
     *	结果：手机号不能为空
     *
     * 测试7：138 1234 5 http://localhost:8080/signin?phone=138%201234%205
     *	结果：手机号长度应为 11 位
     *
     * 测试8：138123456 http://localhost:8080/signin?phone=138123456
     *	结果：手机号长度应为 11 位
     *
     */
    @RequestMapping("/signin")
    @ResponseBody
    public ResultVO signin(@Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("[用户登录]参数不正确, user={}", user);
            throw new BizException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        String phone = user.getPhone();
        phone = phone.length() == 13 ? phone.replace(" ", "") : phone;
        user.setPhone(phone);

        userService.signin(user);
        return new ResultVO<>();
    }

}
