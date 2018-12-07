package examplw.authentication.server.Authorization.accessdenied;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 默认情况下登陆失败会跳转页面，这里自定义
 */
@Component(value = "unauthorizedEntryPoint")
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(UnauthorizedEntryPoint.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.info("==================跳转路径==============");
        logger.info("==================跳转路径==============");
        logger.info("==================跳转路径==============");
        logger.info("==================跳转路径==============");
        logger.info("==================跳转路径==============");

//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/javascript;charset=utf-8");
//        response.getWriter().print(JSONObject.toJSONString(RestMsg.error("没有访问权限!")));

        redirectStrategy.sendRedirect(request,response, "/login.html");

//        response.sendRedirect("/login.html");
    }
}
