package com.xsn.service;

import com.xsn.dto.ResultDTO;

public interface UserService {

    ResultDTO addUser(String name, int age);

    ResultDTO getUser(int id);
}
