package com.xsn.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String name;

    private Integer age;

    private String email;
}
