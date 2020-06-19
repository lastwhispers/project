package cn.lastwhisper.project.bean;

import cn.lastwhisper.project.validator.annotation.CheckPhone;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Objects;

/**
 * 用户实体
 * @author lastwhisper
 * @date 2020/5/28
 */
public class User implements Serializable {

    @CheckPhone
    private String phone;

    @Email(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式错误")
    private String email;

    // other filed

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
