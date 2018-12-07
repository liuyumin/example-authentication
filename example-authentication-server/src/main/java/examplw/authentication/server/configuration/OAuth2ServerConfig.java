package examplw.authentication.server.configuration;

import examplw.authentication.server.Authorization.client.MyClientDetailsService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class OAuth2ServerConfig {

    @EnableAuthorizationServer
    public static class AuthorizationServerConfigurations extends OAuth2AuthorizationServerConfiguration {

        public AuthorizationServerConfigurations(BaseClientDetails details, AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStore, ObjectProvider<AccessTokenConverter> tokenConverter, AuthorizationServerProperties properties) {
            super(details, authenticationManager, tokenStore, tokenConverter, properties);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            super.configure(endpoints);
            endpoints.userApprovalHandler(new DefaultUserApprovalHandler());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .realm("oauth2-resources")
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("permitAll()")
                    .allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
            .withClient("authentication-clientId")
                    .secret("authentication-secret")
                    .redirectUris("http://baidu.com")
                    .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token",
                            "password", "implicit")
                    .scopes("all")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(1200)
                    .refreshTokenValiditySeconds(50000);
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
