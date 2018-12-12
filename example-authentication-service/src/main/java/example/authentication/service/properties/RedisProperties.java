package example.authentication.service.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RedisProperties {

    @Value("${redis.data.database}")
    private int redisDatabase;

    @Value("${redis.data.host}")
    private String redisHost;

    @Value("${redis.data.port}")
    private int redisPort;

    @Value("${redis.data.password}")
    private String redisPassword;

    @Value("${redis.data.pool.max-active}")
    private int redisMaxActive;

    @Value("${redis.data.pool.max-wait}")
    private int redisMaxWait;

    @Value("${redis.data.pool.max-idle}")
    private int redisMaxIdle;

    @Value("${redis.data.pool.min-idle}")
    private int redisMinIdle;

    @Value("${redis.data.timeout}")
    private int redisTimeout;

    @Value("${redis.data.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.data.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.data.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.data.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.data.testWhileIdle}")
    private boolean testWhileIdle;

    //集群配置
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer mmaxRedirectsac;
}
