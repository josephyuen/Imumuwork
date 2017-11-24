package com.zumumu.imumu.ui.user.model;

/**
 * Created by PC_p on 2016/12/26.
 *登录以及注册
 */

public class UserBean {
    private String username;
    private String passWord;
    private String phone;
    private String verifycode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
