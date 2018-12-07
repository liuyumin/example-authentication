package examplw.authentication.server.controller;

import examplw.authentication.server.Authorization.AuthorizaitionServerToken;
import examplw.authentication.server.model.LoginUser;
import examplw.authentication.server.model.OAuthTokenMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@RestController
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2ApprovalController.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {

        logger.info("=======================getAccessConfirmation====================");
        logger.info("=======================getAccessConfirmation====================");
        logger.info("=======================getAccessConfirmation====================");
        logger.info("=======================getAccessConfirmation====================");
        logger.info("=======================getAccessConfirmation====================");

        return new ModelAndView("oauth_approvals");
    }

    @RequestMapping("/oauth/user")
    public void getAccessUserAuthentication(LoginUser loginUser, HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws Exception {
        logger.info("=======================getAccessUserAuthentication进行路径跳转...====================");
        AuthorizaitionServerToken authorizaitionServerToken = new AuthorizaitionServerToken(loginUser.getUsername(),loginUser.getPassword());
        final Authentication authentication = authenticationManagerBean.authenticate(authorizaitionServerToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        httpServletResponse.sendRedirect("/oauth/authorize?response_type=code&client_id=authentication-clientId" +
                "&redirect_uri=http://www.baidu.com&scope=all&username=wangww");
    }

    @RequestMapping(value = "/oauth/tokens", method= RequestMethod.POST)
    public void postAccessToken(Principal principal,OAuthTokenMode oAuthTokenMode,HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("======================ceshi-/oauth/tokens");
        String redirectUrl = "/oauth/token?grant_type=authorization_code&code="+oAuthTokenMode.getCode()+"&client_id=authentication-clientId&client_secret=authentication-secret&redirect_uri=http://www.baidu.com&scope=all";
        request.getRequestDispatcher(redirectUrl).forward(request,httpServletResponse);
        //redirectStrategy.sendRedirect(request,httpServletResponse,redirectUrl);
    }

    @RequestMapping(value = "/user/me", method= RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority({'sys:user:list'})")
    public Object getUserInfo(@AuthenticationPrincipal UserDetails user){
        logger.info("test security permission...........");
        logger.info("test security permission...........");
        logger.info("test security permission...........");
        logger.info("test security permission...........");
        return user;
    }
}
