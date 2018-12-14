package example.authentication.server.oauth2;

import example.authentication.server.oauth2.server.CustomAuthorizationCodeServices;
import example.authentication.server.oauth2.server.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Configuration
public class OAuth2ServerConfig {

    @EnableAuthorizationServer
    public static class AuthorizationServerConfigurations extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private CustomAuthorizationCodeServices customAuthorizationCodeServices;//自定义生成授权码

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.userApprovalHandler(new DefaultUserApprovalHandler());
            endpoints.authorizationCodeServices(customAuthorizationCodeServices);
//            endpoints.tokenEnhancer(tokenEnhancer());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .realm("oauth2-resources")
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("permitAll()");
//                    .allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
            .withClient("authentication-clientId")
                    .secret(passwordEncoder.encode("authentication-secret"))
                    .redirectUris("https://www.baidu.com")
                    .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token",
                            "password", "implicit")
                    .scopes("all")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(1200)
                    .refreshTokenValiditySeconds(50000);
        }

        @Bean
        public TokenEnhancer tokenEnhancer(){
            return new CustomTokenEnhancer();
        }
    }

//    @Configuration
//    @EnableAuthorizationServer
//    public static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
//
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            super.configure(security);
//        }
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            clients.withClientDetails(new InMemoryClientDetailsService());
//        }
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints.userApprovalHandler(new DefaultUserApprovalHandler());
//        }
//    }
}
