package example.authentication.server.controller;

import cn.hutool.core.util.ObjectUtil;
import example.authentication.server.model.OAuthTokenMode;
import example.authentication.service.dao.OAuthUserDao;
import example.authentication.server.security.AuthorizationServerToken;
import example.authentication.server.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@RestController
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2ApprovalController.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private OAuthUserDao oAuthUserDao;

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
    public void getAccessUserAuthentication(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AuthorizationServerToken authorizationServerToken = new AuthorizationServerToken(loginUser.getUsername(),loginUser.getPassword());
        final Authentication authentication = authenticationManagerBean.authenticate(authorizationServerToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //如果处在用户，则生成OPENID,进行更新
        String openId = UUID.randomUUID().toString();
        oAuthUserDao.updateOpenIdByAccount(loginUser.getUsername(),openId);

        //路径跳转
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (ObjectUtil.isNull(savedRequest)){
            return;
        }
        String targetUrl = savedRequest.getRedirectUrl();

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    @RequestMapping(value = "/oauth/getOpenId", method= RequestMethod.POST)
    public void getUserOpenId(Principal principal, OAuthTokenMode oAuthTokenMode, HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        //判断token是否存在

    }

    @RequestMapping(value = "/oauth/tokens", method= RequestMethod.POST)
    public void postAccessToken(Principal principal,OAuthTokenMode oAuthTokenMode,HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("======================ceshi-/oauth/tokens");
        String redirectUrl = "/oauth/token?grant_type=authorization_code&code="+oAuthTokenMode.getCode()+"&client_id=authentication-clientId&client_secret=authentication-secret&redirect_uri=https://www.baidu.com&scope=all";
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
