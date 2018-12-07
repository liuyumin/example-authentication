package examplw.authentication.server.Authorization;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthorizaitionServerToken extends UsernamePasswordAuthenticationToken {

    public AuthorizaitionServerToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
