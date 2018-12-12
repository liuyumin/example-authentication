package example.authentication.server.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthorizationServerToken extends UsernamePasswordAuthenticationToken {

    public AuthorizationServerToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
