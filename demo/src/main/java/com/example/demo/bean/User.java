package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("User")
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
