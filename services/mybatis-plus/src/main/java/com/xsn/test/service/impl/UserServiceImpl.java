package com.xsn.test.service.impl;

import com.xsn.test.entity.User;
import com.xsn.test.mapper.UserMapper;
import com.xsn.test.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xsn
 * @since 2020-09-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
