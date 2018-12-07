package examplw.authentication.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OAuthTokenMode implements Serializable {
    private static final long serialVersionUID = 129629339523377245L;

    private String grant_type;

    private String client_id;

    private String code;

    private String redirect_uri;

    private String scope;
}
