package md.quin.api.be.qa.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AuthParams {

    private String scope;

    private String response_type;

    public AuthParams(String scope, String response_type) {
        this.scope = scope;
        this.response_type = response_type;
    }
}
