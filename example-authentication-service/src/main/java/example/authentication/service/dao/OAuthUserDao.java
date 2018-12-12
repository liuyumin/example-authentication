package example.authentication.service.dao;

import example.authentication.service.entity.OAuthUser;
import example.authentication.service.mapper.OAuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Repository
public class OAuthUserDao extends BaseDao<OAuthUser> {

    @Autowired
    private OAuthUserMapper oAuthUserMapper;

    @Override
    protected Mapper<OAuthUser> getMapper() {
        return oAuthUserMapper;
    }

    /***
     * 通过account登录更新openId
     * @param account
     * @param openId
     */
    public void updateOpenIdByAccount(String account, String openId) {
        Example example = new Example(OAuthUser.class);
        example.createCriteria().andEqualTo("account",account);

        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.setOpenId(openId);

        updateByExampleSelective(oAuthUser,example);
    }

    /***
     * 通过登录账号获取用户信息
     * @param account
     * @return
     */
    public OAuthUser getUserInfo(String account) {
        Example example = new Example(OAuthUser.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("account",account);

        criteria.andEqualTo("isFreeze",0);

        criteria.andEqualTo("flag",0);

        return queryOne(example);


    }
}
