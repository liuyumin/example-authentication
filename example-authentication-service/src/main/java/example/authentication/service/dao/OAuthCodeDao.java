package example.authentication.service.dao;

import example.authentication.service.entity.OAuthCode;
import example.authentication.service.mapper.OAuthCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class OAuthCodeDao extends BaseDao<OAuthCode> {

    @Autowired
    private OAuthCodeMapper oAuthCodeMapper;

    @Override
    protected Mapper<OAuthCode> getMapper() {
        return oAuthCodeMapper;
    }
}
