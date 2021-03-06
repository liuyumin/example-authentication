package example.authentication.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
public class AuthorizationServerProvider implements AuthenticationProvider {

    @Autowired
    private AuthorizationServerUserDetails authorizationServerUserDetails;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = authorizationServerUserDetails.loadUserByUsername(authentication.getName());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public boolean supports(Class<?> authentication) {
        return true;
    }
}
