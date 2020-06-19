package cn.lastwhisper.project.service.impl;

import cn.lastwhisper.project.service.IUserService;
import cn.lastwhisper.project.bean.User;
import cn.lastwhisper.project.exception.BizException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.lastwhisper.project.enums.ResultEnum.PHONE_EXIST;

/**
 *
 * @author lastwhisper
 * @date 2020/5/28
 */
@Service
public class UserServiceImpl implements IUserService {

    private Map<String, User> cache = new ConcurrentHashMap<>();

    @Override
    public void signin(User user) {
        if (cache.containsKey(user.getPhone())) {
            throw new BizException(PHONE_EXIST);
        }
        cache.put(user.getPhone(), user);
    }

}
