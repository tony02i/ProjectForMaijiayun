package com.account.manage.bean;

import java.awt.SecondaryLoop;
import java.io.Serializable;
import java.util.Date;

import com.account.manage.controller.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class User{  

    private String id;  
    private String name;
    private Integer state;
    private Integer type;
    private Date createTime;
    private Date lastLoginTime;
    private String note;
    
    public User() {
        super();
    }
    public User(String id) {
        super();
        this.id = id;
    }
    public User(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public User(String id, String name, Integer state) {
        super();
        this.id = id;
        this.name = name;
        this.state = state;
    }
    public User(String id, String name, Integer state, Integer type, String note) {
        super();
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
        this.note = note;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }  
}  