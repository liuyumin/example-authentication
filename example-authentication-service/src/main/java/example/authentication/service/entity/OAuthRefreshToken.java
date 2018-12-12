package example.authentication.service.entity;

import javax.persistence.*;

@Table(name = "t_oauth_refresh_token")
public class OAuthRefreshToken {
    /**
     * TOKEN编号
     */
    @Column(name = "token_id")
    private String tokenId;

    /**
     * TOKEN值
     */
    private byte[] token;

    /**
     * 权限信息
     */
    private byte[] authentication;

    /**
     * 获取TOKEN编号
     *
     * @return token_id - TOKEN编号
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * 设置TOKEN编号
     *
     * @param tokenId TOKEN编号
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
    }

    /**
     * 获取TOKEN值
     *
     * @return token - TOKEN值
     */
    public byte[] getToken() {
        return token;
    }

    /**
     * 设置TOKEN值
     *
     * @param token TOKEN值
     */
    public void setToken(byte[] token) {
        this.token = token;
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