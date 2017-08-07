package com.chenqinhao.cat.royal.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * Created by chenqinhao on 2017/7/3.
 */
public class UserProfile implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private Date birth;

    private String sex;

    private String nickname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
