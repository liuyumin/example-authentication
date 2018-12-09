//package examplw.authentication.server.configuration;
//
//import examplw.authentication.server.Authorization.AuthorizationServerFilter;
//import examplw.authentication.server.Authorization.AuthorizationServerProvider;
//import examplw.authentication.server.Authorization.accessdenied.AuthServerAccessDeniedHandler;
//import examplw.authentication.server.Authorization.accessdenied.UnauthorizedEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class AuthorizationServerConfiguration  extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UnauthorizedEntryPoint unauthorizedEntryPoint;
//
//    @Autowired
//    private AuthServerAccessDeniedHandler authServerAccessDeniedHandler;
//
//    @Autowired
//    private AuthorizationServerProvider authenticationProvider;
//
//    @Bean
//    public AuthorizationServerFilter authorizationServerFilter(){
//        return new AuthorizationServerFilter();
//    }
//
//    @Bean(value = "authenticationManagerBean")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager manager = super.authenticationManagerBean();
//        return manager;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
//                .accessDeniedHandler(authServerAccessDeniedHandler)
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(
//                        "/login.html",
//                        "/oauth/user","/oauth/tokens").permitAll()
//                .anyRequest().authenticated();
//
//        httpSecurity.addFilterBefore(authorizationServerFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
