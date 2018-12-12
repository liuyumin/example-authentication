package example.authentication.service.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_oauth_access_token")
public class OAuthAccessToken {
    /**
     * TOKEN编号
     */
    @Column(name = "token_id")
    private String tokenId;

    /**
     * 验证编号
     */
    @Column(name = "authentication_id")
    private String authenticationId;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 客户编号
     */
    @Column(name = "client_id")
    private String clientId;

    /**
     * 刷新TOKEN
     */
    @Column(name = "refresh_token")
    private String refreshToken;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0：未删除，1：逻辑删除
     */
    private Integer flag;

    /**
     * TOKEN内容
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
     * 获取验证编号
     *
     * @return authentication_id - 验证编号
     */
    public String getAuthenticationId() {
        return authenticationId;
    }

    /**
     * 设置验证编号
     *
     * @param authenticationId 验证编号
     */
    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId == null ? null : authenticationId.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取客户编号
     *
     * @return client_id - 客户编号
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客户编号
     *
     * @param clientId 客户编号
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * 获取刷新TOKEN
     *
     * @return refresh_token - 刷新TOKEN
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 设置刷新TOKEN
     *
     * @param refreshToken 刷新TOKEN
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取0：未删除，1：逻辑删除
     *
     * @return flag - 0：未删除，1：逻辑删除
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置0：未删除，1：逻辑删除
     *
     * @param flag 0：未删除，1：逻辑删除
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取TOKEN内容
     *
     * @return token - TOKEN内容
     */
    public byte[] getToken() {
        return token;
    }

    /**
     * 设置TOKEN内容
     *
     * @param token TOKEN内容
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