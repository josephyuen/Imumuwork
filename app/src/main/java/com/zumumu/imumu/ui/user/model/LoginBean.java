package com.zumumu.imumu.ui.user.model;

/**
 * Created by PC_p on 2017/1/12.
 */

public class LoginBean {


    /**
     * user_CreateTime : 1477555753
     * user_ID : 4678
     * user_Mobile : 15574819278
     * user_NickName : 从余
     * user_No : 11203
     * user_Password : 202cb962ac59075b964b07152d234b70
     */

    private DataBean data;
    /**
     * data : {"user_CreateTime":"1477555753","user_ID":4678,"user_Mobile":"15574819278","user_NickName":"从余","user_No":11203,"user_Password":"202cb962ac59075b964b07152d234b70"}
     * msg : 登录成功
     * status : 0
     */

    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private String user_CreateTime;
        private int user_ID;
        private String user_Mobile;
        private String user_NickName;
        private int user_No;
        private String user_Password;

        public String getUser_CreateTime() {
            return user_CreateTime;
        }

        public void setUser_CreateTime(String user_CreateTime) {
            this.user_CreateTime = user_CreateTime;
        }

        public int getUser_ID() {
            return user_ID;
        }

        public void setUser_ID(int user_ID) {
            this.user_ID = user_ID;
        }

        public String getUser_Mobile() {
            return user_Mobile;
        }

        public void setUser_Mobile(String user_Mobile) {
            this.user_Mobile = user_Mobile;
        }

        public String getUser_NickName() {
            return user_NickName;
        }

        public void setUser_NickName(String user_NickName) {
            this.user_NickName = user_NickName;
        }

        public int getUser_No() {
            return user_No;
        }

        public void setUser_No(int user_No) {
            this.user_No = user_No;
        }

        public String getUser_Password() {
            return user_Password;
        }

        public void setUser_Password(String user_Password) {
            this.user_Password = user_Password;
        }
    }
}
