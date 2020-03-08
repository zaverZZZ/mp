package com.zaver.mp.modules.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.modules.app.dao.UserDao;
import com.zaver.mp.modules.app.model.User;
import com.zaver.mp.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDao userDao;

    @Override
    public IPage<User> pageAll(Page<User> page, String nickName) {
        IPage<User> result = userDao.pageAll(page,nickName);
        return result;
    }

    @Override
    public boolean save(User user) {
        return super.save(user);
    }
}
