package examplw.authentication.server.Authorization;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static examplw.authentication.server.constants.AuthorizedContants.USER_NAME;

@Component(value = "authorizationServerFilter")
public class AuthorizationServerFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerFilter.class);

    @Autowired
    private AuthorizationServerUserDetails authorizationServerUserDetails;

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private boolean postOnly = true;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().indexOf("/oauth/user") >= 0){
            if (postOnly && !request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException(
                        "Authentication method not supported: " + request.getMethod());
            }

            String username = request.getParameter(usernameParameter);
            String password = request.getParameter(passwordParameter);

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            // Allow subclasses to set the "details" property
            authRequest.setDetails(authorizationServerUserDetails.loadUserByUsername(username));

            SecurityContextHolder.getContext().setAuthentication(authRequest);

            filterChain.doFilter(request,response);
            return;
        }else {
            filterChain.doFilter(request,response);
        }
    }

    private boolean checkUserNameIsExisit(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()){
            String[] username = parameterMap.get(USER_NAME);
            if (ObjectUtil.isNotNull(username)){
               return true;
            }
        }
        return false;
    }

    /***
     * 目前只支持code模式
     * @param request
     * @return
     */
    private Boolean checkAuthorizationModel(HttpServletRequest request,String key,String value) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()){
            String[] responseTypes = parameterMap.get(key);
            if (ObjectUtil.isNotNull(responseTypes)){
                for (String responseType : responseTypes){
                    if (StrUtil.equals(responseType,value)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
