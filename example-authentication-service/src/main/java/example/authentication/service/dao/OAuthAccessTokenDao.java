package example.authentication.service.dao;

import example.authentication.service.entity.OAuthAccessToken;
import example.authentication.service.mapper.OAuthAccessTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class OAuthAccessTokenDao extends BaseDao<OAuthAccessToken> {

    @Autowired
    private OAuthAccessTokenMapper oAuthAccessTokenMapper;

    @Override
    protected Mapper<OAuthAccessToken> getMapper() {
        return oAuthAccessTokenMapper;
    }
}
