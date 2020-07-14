package com.xsn.service.impl;

import com.xsn.anno.ReadOnly;
import com.xsn.dto.CodeMap;
import com.xsn.dto.ResultDTO;
import com.xsn.entity.TestUser;
import com.xsn.mapper.TestUserMapper;
import com.xsn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    @Transactional
    public ResultDTO addUser(String name, int age) {

        int i = 0;
        TestUser testUser = new TestUser();
        testUser.setAge(age);
        testUser.setName(name);

        try {
            i = testUserMapper.insertSelective(testUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return new ResultDTO(CodeMap.STATE_CODE_SUCCESS);
    }

    @Override
    @ReadOnly
    public ResultDTO getUser(int id) {

        TestUser testUser = testUserMapper.selectByPrimaryKey(id);
        return ResultDTO.success(testUser);
    }
}
