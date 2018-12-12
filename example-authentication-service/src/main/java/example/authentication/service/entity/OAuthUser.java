package example.authentication.service.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_oauth_user")
public class OAuthUser {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 系统用户唯一标识
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 登陆账号
     */
    private String account;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 电话
     */
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * 性别 0-男 1-女
     */
    private String sex;

    /**
     * 头像
     */
    private String photo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否冻结 0--未冻结 1--冻结
     */
    @Column(name = "is_freeze")
    private Integer isFreeze;

    /**
     * 最后登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 0：未删除，1：逻辑删除
     */
    private Integer flag;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取系统用户唯一标识
     *
     * @return open_id - 系统用户唯一标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置系统用户唯一标识
     *
     * @param openId 系统用户唯一标识
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取昵称
     *
     * @return user_nickname - 昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置昵称
     *
     * @param userNickname 昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    /**
     * 获取登陆账号
     *
     * @return account - 登陆账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置登陆账号
     *
     * @param account 登陆账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 获取登陆密码
     *
     * @return password - 登陆密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登陆密码
     *
     * @param password 登陆密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取电话
     *
     * @return mobile_no - 电话
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 设置电话
     *
     * @param mobileNo 电话
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     * 获取性别 0-男 1-女
     *
     * @return sex - 性别 0-男 1-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别 0-男 1-女
     *
     * @param sex 性别 0-男 1-女
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取头像
     *
     * @return photo - 头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置头像
     *
     * @param photo 头像
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取是否冻结 0--未冻结 1--冻结
     *
     * @return is_freeze - 是否冻结 0--未冻结 1--冻结
     */
    public Integer getIsFreeze() {
        return isFreeze;
    }

    /**
     * 设置是否冻结 0--未冻结 1--冻结
     *
     * @param isFreeze 是否冻结 0--未冻结 1--冻结
     */
    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }

    /**
     * 获取最后登录IP
     *
     * @return last_login_ip - 最后登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后登录IP
     *
     * @param lastLoginIp 最后登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
     * 获取修改人
     *
     * @return update_by - 修改人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置修改人
     *
     * @param updateBy 修改人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
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
}