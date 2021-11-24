package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录用户
     */
    private String name;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像url
     */
    private String piture;

    /**
     * 注册时间
     */
    private Date regitime;

    /**
     * 所属学院
     */
    private Integer deptname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPiture() {
        return piture;
    }

    public void setPiture(String piture) {
        this.piture = piture;
    }

    public Date getRegitime() {
        return regitime;
    }

    public void setRegitime(Date regitime) {
        this.regitime = regitime;
    }

    public Integer getDeptname() {
        return deptname;
    }

    public void setDeptname(Integer deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", password=" + password +
        ", status=" + status +
        ", email=" + email +
        ", userName=" + userName +
        ", piture=" + piture +
        ", regitime=" + regitime +
        ", deptname=" + deptname +
        "}";
    }
}
