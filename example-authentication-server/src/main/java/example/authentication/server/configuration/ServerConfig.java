package example.authentication.server.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("example.authentication.service.mapper")
@ComponentScan(basePackages = {"example.authentication.service"})
public class ServerConfig {
}
