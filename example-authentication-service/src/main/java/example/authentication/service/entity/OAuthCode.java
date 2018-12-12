package example.authentication.service.entity;

import javax.persistence.*;

@Table(name = "t_oauth_code")
public class OAuthCode {
    /**
     * CODE码
     */
    private String code;

    /**
     * 权限信息
     */
    private byte[] authentication;

    /**
     * 获取CODE码
     *
     * @return code - CODE码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置CODE码
     *
     * @param code CODE码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取权限信息
     *
     * @return authentication - 权限信息
     */
    public byte[] getAuthentication() {
        return authentication;
    }

    /**
     * 设置权限信息
     *
     * @param authentication 权限信息
     */
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}