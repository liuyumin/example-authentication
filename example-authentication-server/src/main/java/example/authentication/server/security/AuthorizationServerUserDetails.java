package example.authentication.server.security;

import example.authentication.service.dao.OAuthUserDao;
import example.authentication.service.entity.OAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Component;

@Component(value = "authorizationServerUserDetails")
public class AuthorizationServerUserDetails implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerUserDetails.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OAuthUserDao oAuthUserDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户信息
        OAuthUser oAuthUser = oAuthUserDao.getUserInfo(username);
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode(oAuthUser.getPassword());
        //用户权限暂时不实现
        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));
        return user;
    }


//    @Override
//    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//
//        BaseClientDetails baseClientDetails = new BaseClientDetails();
//
//        logger.info("============================【{}】",clientId);
//        logger.info("============================【{}】",clientId);
//        logger.info("============================【{}】",clientId);
//
////        ClientDetails details = (ClientDetails)this.clientDetailsStore.get(clientId);
////        if (details == null) {
////            throw new NoSuchClientException("No client with requested id: " + clientId);
////        } else {
////            return details;
////        }
////
////
////        User user = new User(clientId, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));
//        return baseClientDetails;
//    }
}
