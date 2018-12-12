package example.authentication.service.dao;

import example.authentication.service.entity.OAuthClientDetails;
import example.authentication.service.mapper.OAuthClientDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class OAuthClientDetailsDao extends BaseDao<OAuthClientDetails> {

    @Autowired
    private OAuthClientDetailsMapper oAuthClientDetailsMapper;

    @Override
    protected Mapper<OAuthClientDetails> getMapper() {
        return oAuthClientDetailsMapper;
    }
}
