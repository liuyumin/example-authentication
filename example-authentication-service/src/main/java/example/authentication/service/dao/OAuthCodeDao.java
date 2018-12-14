package example.authentication.service.dao;

import example.authentication.service.entity.OAuthCode;
import example.authentication.service.mapper.OAuthCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Repository
public class OAuthCodeDao extends BaseDao<OAuthCode> {

    @Autowired
    private OAuthCodeMapper oAuthCodeMapper;

    @Override
    protected Mapper<OAuthCode> getMapper() {
        return oAuthCodeMapper;
    }

    /***
     * 存储验证码映射信息
     * @param code
     * @param bytes
     */
    public void storeCodeMappingInfo(String code, byte[] bytes) {
        OAuthCode oAuthCode = new OAuthCode();
        oAuthCode.setCode(code);
        oAuthCode.setAuthentication(bytes);
        this.saveSelective(oAuthCode);
    }

    /***
     * 通过code获取权限信息
     * @param code
     * @return
     */
    public OAuthCode getOAuth2AuthenticationInfo(String code) {
        Example example = new Example(OAuthCode.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("code",code);

        return this.queryOne(example);
    }
}
