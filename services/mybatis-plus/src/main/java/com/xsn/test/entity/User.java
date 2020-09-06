package com.xsn.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author xsn
 * @since 2020-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
// 自己添加无参构造
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private String email;

    // 自己添加的构造方法
    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
