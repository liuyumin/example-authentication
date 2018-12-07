package examplw.authentication.server.Authorization;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static examplw.authentication.server.constants.AuthorizedContants.USER_NAME;

public class AuthorizationServerFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerFilter.class);

    @Autowired
    private AuthorizationServerUserDetails authorizationServerUserDetails;

    private boolean allowOnlyPost = false;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().indexOf("/oauth/user") >= 0){
            filterChain.doFilter(request,response);
            return;
        }else {
            if (checkAuthorizationModel(request,OAuth2Utils.RESPONSE_TYPE,"code") && checkUserNameIsExisit(request)){

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (ObjectUtil.isNotNull(authentication)){
                    String clientId = "authentication-clientId";
                    String clientSecret = "authentication-secret";

                    if (clientId == null) {
                        throw new BadCredentialsException("No client credentials presented");
                    }

                    if (clientSecret == null) {
                        clientSecret = "";
                    }

                    clientId = clientId.trim();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(clientId,
                            clientSecret);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    authenticationToken.setAuthenticated(false);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }


            }else if (checkAuthorizationModel(request,"grant_type","authorization_code")){

            }

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
