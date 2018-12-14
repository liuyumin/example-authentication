package example.authentication.server.constants;

public interface AuthorizedConstants {

    /**
     * 用户登录HTML页面
     */
    String CLIENT_LOGIN_HTML = "/custom_login.html";

    /***
     * 获取用户openId
     */
    String GET_USER_OPEN_ID = "/oauth/getOpenId";

    /***
     * 处理方式
     */
    String CODE_OPERATION_TYPE = "REDIS";

}
