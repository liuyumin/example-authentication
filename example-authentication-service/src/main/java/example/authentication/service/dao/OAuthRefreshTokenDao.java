package example.authentication.service.dao;

import example.authentication.service.entity.OAuthRefreshToken;
import example.authentication.service.mapper.OAuthRefreshTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class OAuthRefreshTokenDao extends BaseDao<OAuthRefreshToken> {

    @Autowired
    private OAuthRefreshTokenMapper oAuthRefreshTokenMapper;

    @Override
    protected Mapper<OAuthRefreshToken> getMapper() {
        return oAuthRefreshTokenMapper;
    }
}
