package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/")
public class TestDBController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") String id) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("id", id)
                        .select("id", "name", "age")
        );
    }

    @GetMapping("/listUser")
    public List<User> getUser() {
        return userMapper.selectList(
                new QueryWrapper<User>().select("id", "name", "age")
        );
    }

}
