package cn.project.simulationmall.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Create by ankele
 * <p>
 * 2020/6/24 - 16:54
 */
public class User extends LitePalSupport {

    private String userName;
    private String userPwd;
    private String isLogin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }
}
