package example.authentication.server.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;


public class LoginUser implements Serializable {

    private static final long serialVersionUID = -1954198443276000363L;

    @NotBlank(message="登录用户名为空")
    private String username;

    @NotBlank(message="登录密码为空")
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
}
