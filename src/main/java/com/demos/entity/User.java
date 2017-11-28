package com.demos.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Function:
 * Author: created by liguoliang
 * Date: 2017/11/28 0028 下午 3:15
 * Version: 1.0
 */
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    // 没有默认构造会报错
    public User(){

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
