package com.xsn.controller;

import com.xsn.dto.ResultDTO;
import com.xsn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户controller")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "用户名", required = true)
            , @ApiImplicitParam(name = "age", value = "年龄")
    })
    public ResultDTO addUser(
            @RequestParam(name = "name") String name
            , @RequestParam(name = "age", required = false) int age

    ) {

        return userService.addUser(name, age);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    public ResultDTO getUser(
            @RequestParam(name = "id") int id
    ) {

        return userService.getUser(id);
    }
}
