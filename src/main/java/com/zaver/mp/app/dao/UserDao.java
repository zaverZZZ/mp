package com.zaver.mp.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaver.mp.app.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {

}
