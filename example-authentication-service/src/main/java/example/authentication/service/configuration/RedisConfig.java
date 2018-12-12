package example.authentication.service.configuration;

import example.authentication.service.properties.RedisProperties;
import example.authentication.service.util.CustomRedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/***
 * redis单点配置
 */

@Component
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getRedisHost());
        redisStandaloneConfiguration.setPort(redisProperties.getRedisPort());
        redisStandaloneConfiguration.setDatabase(redisProperties.getRedisDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getRedisPassword()));

        //如下两种配置方式 第一种方式
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout
        jedisClientConfiguration.usePooling().poolConfig(jedisPoolConfig);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.build());

        return jedisConnectionFactory;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(redisProperties.getRedisMaxIdle());
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(redisProperties.getRedisMaxActive());
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getRedisMaxWait());
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisProperties.getMinEvictableIdleTimeMillis());
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(redisProperties.getNumTestsPerEvictionRun());
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisProperties.getTimeBetweenEvictionRunsMillis());
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(redisProperties.isTestWhileIdle());
        return jedisPoolConfig;
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
//        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
    /**
     * 注入封装RedisTemplate
     * @Title: redisUtil
     * @return RedisUtil
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
    @Bean(name = "customRedisUtils")
    public CustomRedisUtils redisUtil(RedisTemplate<String, Object> redisTemplate) {
        CustomRedisUtils redisUtil = new CustomRedisUtils();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}
