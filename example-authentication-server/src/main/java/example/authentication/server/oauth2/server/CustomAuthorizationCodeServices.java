package example.authentication.server.oauth2.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import java.util.concurrent.ConcurrentHashMap;

public class CustomAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthorizationCodeServices.class);

    private RandomValueStringGenerator generator = new RandomValueStringGenerator();



//    protected final ConcurrentHashMap<String, OAuth2Authentication> authorizationCodeStore = new ConcurrentHashMap();
//
//    public InMemoryAuthorizationCodeServices() {
//    }
//
//    protected void store(String code, OAuth2Authentication authentication) {
//        this.authorizationCodeStore.put(code, authentication);
//    }
//
//    public OAuth2Authentication remove(String code) {
//        OAuth2Authentication auth = (OAuth2Authentication)this.authorizationCodeStore.remove(code);
//        return auth;
//    }

    public CustomAuthorizationCodeServices() {}

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        //使用Redis存储结构化信息
    }

    @Override
    protected OAuth2Authentication remove(String s) {
        return null;
    }
}
