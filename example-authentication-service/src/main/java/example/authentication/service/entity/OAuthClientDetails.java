package example.authentication.service.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_oauth_client_details")
public class OAuthClientDetails {
    /**
     * 客户(第三方系统)编号
     */
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String clientId;

    /**
     * 客户(第三方系统)密码
     */
    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 资源编号app,pc
     */
    @Column(name = "resource_ids")
    private String resourceIds;

    /**
     * 资源权限范围
     */
    private String scope;

    /**
     * 验证权限类型code,密码等
     */
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     * 所有权限信息
     */
    private String authorities;

    /**
     * 访问token失效时间
     */
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 刷新token失效时间
     */
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 是否自动审核
     */
    private String autoapprove;

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
     * 扩展信息
     */
    @Column(name = "additional_information")
    private String additionalInformation;

    /**
     * 获取客户(第三方系统)编号
     *
     * @return client_id - 客户(第三方系统)编号
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客户(第三方系统)编号
     *
     * @param clientId 客户(第三方系统)编号
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * 获取客户(第三方系统)密码
     *
     * @return client_secret - 客户(第三方系统)密码
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 设置客户(第三方系统)密码
     *
     * @param clientSecret 客户(第三方系统)密码
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * 获取资源编号app,pc
     *
     * @return resource_ids - 资源编号app,pc
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * 设置资源编号app,pc
     *
     * @param resourceIds 资源编号app,pc
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    /**
     * 获取资源权限范围
     *
     * @return scope - 资源权限范围
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置资源权限范围
     *
     * @param scope 资源权限范围
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 获取验证权限类型code,密码等
     *
     * @return authorized_grant_types - 验证权限类型code,密码等
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * 设置验证权限类型code,密码等
     *
     * @param authorizedGrantTypes 验证权限类型code,密码等
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    /**
     * 获取回调地址
     *
     * @return web_server_redirect_uri - 回调地址
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * 设置回调地址
     *
     * @param webServerRedirectUri 回调地址
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri == null ? null : webServerRedirectUri.trim();
    }

    /**
     * 获取所有权限信息
     *
     * @return authorities - 所有权限信息
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * 设置所有权限信息
     *
     * @param authorities 所有权限信息
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    /**
     * 获取访问token失效时间
     *
     * @return access_token_validity - 访问token失效时间
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * 设置访问token失效时间
     *
     * @param accessTokenValidity 访问token失效时间
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * 获取刷新token失效时间
     *
     * @return refresh_token_validity - 刷新token失效时间
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * 设置刷新token失效时间
     *
     * @param refreshTokenValidity 刷新token失效时间
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 获取是否自动审核
     *
     * @return autoapprove - 是否自动审核
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * 设置是否自动审核
     *
     * @param autoapprove 是否自动审核
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove == null ? null : autoapprove.trim();
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
     * 获取扩展信息
     *
     * @return additional_information - 扩展信息
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * 设置扩展信息
     *
     * @param additionalInformation 扩展信息
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation == null ? null : additionalInformation.trim();
    }
}