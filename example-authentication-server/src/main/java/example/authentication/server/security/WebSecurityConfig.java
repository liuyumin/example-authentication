package example.authentication.server.security;

import example.authentication.server.constants.AuthorizedConstants;
import example.authentication.server.security.accessdenied.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthorizationServerFilter authorizationServerFilter;

    @Autowired
    private AuthorizationServerUserDetails authorizationServerUserDetails;

    @Autowired
    private AuthorizationServerProvider authenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UnauthorizedEntryPoint unauthorizedEntryPoint(){
        return new UnauthorizedEntryPoint(AuthorizedConstants.CLIENT_LOGIN_HTML);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        return authorizationServerUserDetails;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers(AuthorizedConstants.CLIENT_LOGIN_HTML,AuthorizedConstants.GET_USER_OPEN_ID).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
                .and().csrf().disable();

        http.addFilterBefore(authorizationServerFilter, UsernamePasswordAuthenticationFilter.class);

        //第二种方式
//        http.httpBasic().and()
//                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());
//
//        http.csrf().disable();

    }
}
