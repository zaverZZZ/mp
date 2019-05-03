package com.zaver.mp.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.app.dao.UserDao;
import com.zaver.mp.app.model.User;
import com.zaver.mp.app.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName : UserServiceImpl
 * @Description TODO
 * @Date : 2019/4/5 17:24
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

}
