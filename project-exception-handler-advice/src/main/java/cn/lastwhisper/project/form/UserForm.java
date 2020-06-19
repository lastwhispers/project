package cn.lastwhisper.project.form;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author lastwhisper
 * @date 2020/5/19
 */
public class UserForm {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名为空")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码为空")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
