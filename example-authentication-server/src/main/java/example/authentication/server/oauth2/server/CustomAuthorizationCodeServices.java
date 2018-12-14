package example.authentication.server.oauth2.server;

import cn.hutool.core.util.StrUtil;
import example.authentication.server.constants.AuthorizedConstants;
import example.authentication.service.dao.OAuthCodeDao;
import example.authentication.service.entity.OAuthCode;
import example.authentication.service.util.CustomRedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthorizationCodeServices.class);

    @Autowired
    private CustomRedisUtils customRedisUtils;

    @Autowired
    private OAuthCodeDao oAuthCodeDao;

    public CustomAuthorizationCodeServices() {}

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        byte[] bytes = SerializationUtils.serialize(oAuth2Authentication);
        if (StrUtil.equals(AuthorizedConstants.CODE_OPERATION_TYPE,"REDIS")){
            //使用Redis存储结构化信息
            customRedisUtils.setNx(code,String.valueOf(bytes),10*60*60);
        }else {
            //使用DB处理方式
            oAuthCodeDao.storeCodeMappingInfo(code,bytes);
        }
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        byte[] bytes;
        OAuth2Authentication authentication;
        if (StrUtil.equals(AuthorizedConstants.CODE_OPERATION_TYPE,"REDIS")){
            bytes = String.valueOf(customRedisUtils.get(code)).getBytes();
            authentication = (OAuth2Authentication)SerializationUtils.deserialize(bytes);
            if (authentication != null) {
                customRedisUtils.del(code);
            }
        }else {
            OAuthCode oAuthCode = oAuthCodeDao.getOAuth2AuthenticationInfo(code);
            authentication = (OAuth2Authentication)SerializationUtils.deserialize(oAuthCode.getAuthentication());
            if (authentication != null) {
                customRedisUtils.del(code);
            }
        }

        return authentication;
    }
}
