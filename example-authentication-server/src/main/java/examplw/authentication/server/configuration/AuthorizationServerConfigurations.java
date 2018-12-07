//package examplw.authentication.server.configuration;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
//import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.client.BaseClientDetails;
//import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//import javax.annotation.PostConstruct;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.UUID;
//
//@EnableAuthorizationServer
//@Configuration
//@ConditionalOnClass({EnableAuthorizationServer.class})
//@ConditionalOnMissingBean({AuthorizationServerConfigurer.class})
//@ConditionalOnBean({AuthorizationServerEndpointsConfiguration.class})
//@EnableConfigurationProperties({AuthorizationServerProperties.class})
//public class AuthorizationServerConfigurations extends AuthorizationServerConfigurerAdapter {
//    private static final Log logger = LogFactory.getLog(AuthorizationServerConfigurations.class);
//
//    private final BaseClientDetails details;
//    private final AuthenticationManager authenticationManager;
//    private final TokenStore tokenStore;
//    private final AccessTokenConverter tokenConverter;
//    private final AuthorizationServerProperties properties;
//
//    public AuthorizationServerConfigurations(BaseClientDetails details, AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStore, ObjectProvider<AccessTokenConverter> tokenConverter, AuthorizationServerProperties properties) {
//        this.details = details;
//        this.authenticationManager = authenticationManager;
//        this.tokenStore = (TokenStore)tokenStore.getIfAvailable();
//        this.tokenConverter = (AccessTokenConverter)tokenConverter.getIfAvailable();
//        this.properties = properties;
//    }
//
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder builder = clients.inMemory().withClient(this.details.getClientId());
//        builder.secret(this.details.getClientSecret()).resourceIds((String[])this.details.getResourceIds().toArray(new String[0])).authorizedGrantTypes((String[])this.details.getAuthorizedGrantTypes().toArray(new String[0])).authorities((String[]) AuthorityUtils.authorityListToSet(this.details.getAuthorities()).toArray(new String[0])).scopes((String[])this.details.getScope().toArray(new String[0]));
//        if (this.details.getAutoApproveScopes() != null) {
//            builder.autoApprove((String[])this.details.getAutoApproveScopes().toArray(new String[0]));
//        }
//
//        if (this.details.getAccessTokenValiditySeconds() != null) {
//            builder.accessTokenValiditySeconds(this.details.getAccessTokenValiditySeconds().intValue());
//        }
//
//        if (this.details.getRefreshTokenValiditySeconds() != null) {
//            builder.refreshTokenValiditySeconds(this.details.getRefreshTokenValiditySeconds().intValue());
//        }
//
//        if (this.details.getRegisteredRedirectUri() != null) {
//            builder.redirectUris((String[])this.details.getRegisteredRedirectUri().toArray(new String[0]));
//        }
//
//    }
//
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        if (this.tokenConverter != null) {
//            endpoints.accessTokenConverter(this.tokenConverter);
//        }
//
//        if (this.tokenStore != null) {
//            endpoints.tokenStore(this.tokenStore);
//        }
//
//        if (this.details.getAuthorizedGrantTypes().contains("password")) {
//            endpoints.authenticationManager(this.authenticationManager);
//        }
//
//    }
//
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        if (this.properties.getCheckTokenAccess() != null) {
//            security.checkTokenAccess(this.properties.getCheckTokenAccess());
//        }
//
//        if (this.properties.getTokenKeyAccess() != null) {
//            security.tokenKeyAccess(this.properties.getTokenKeyAccess());
//        }
//
//        if (this.properties.getRealm() != null) {
//            security.realm(this.properties.getRealm());
//        }
//
//    }
//
//    @Configuration
//    @ConditionalOnMissingBean({BaseClientDetails.class})
//    protected static class BaseClientDetailsConfiguration {
//        private final OAuth2ClientProperties client;
//
//        protected BaseClientDetailsConfiguration(OAuth2ClientProperties client) {
//            this.client = client;
//        }
//
//        @Bean
//        @ConfigurationProperties(
//                prefix = "security.oauth2.client"
//        )
//        public BaseClientDetails oauth2ClientDetails() {
//            BaseClientDetails details = new BaseClientDetails();
//            if (this.client.getClientId() == null) {
//                this.client.setClientId(UUID.randomUUID().toString());
//            }
//
//            details.setClientId(this.client.getClientId());
//            details.setClientSecret(this.client.getClientSecret());
//            details.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "client_credentials", "implicit", "refresh_token"));
//            details.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//            details.setRegisteredRedirectUri(Collections.<String>emptySet());
//            return details;
//        }
//    }
//
//    @Configuration
//    protected static class ClientDetailsLogger {
//        private final OAuth2ClientProperties credentials;
//
//        protected ClientDetailsLogger(OAuth2ClientProperties credentials) {
//            this.credentials = credentials;
//        }
//
//        @PostConstruct
//        public void init() {
//            String prefix = "security.oauth2.client";
//            boolean defaultSecret = this.credentials.isDefaultSecret();
//            AuthorizationServerConfigurations.logger.info(String.format("Initialized OAuth2 Client%n%n%s.clientId = %s%n%s.secret = %s%n%n", prefix, this.credentials.getClientId(), prefix, defaultSecret ? this.credentials.getClientSecret() : "****"));
//        }
//    }
//}
