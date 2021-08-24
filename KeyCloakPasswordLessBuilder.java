package md.quin.api.be.qa.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class KeyCloakPasswordLessBuilder {

    private AuthParams authParams;
    private String client_id;
    private String email;
    private String send;
    private String connection;

    public KeyCloakPasswordLessBuilder authParams(AuthParams authParams) {
        this.authParams = authParams;
        return this;
    }
    public KeyCloakPasswordLessBuilder email(String email) {
        this.email = email;
        return this;
    }
    public KeyCloakPasswordLessBuilder send(String send) {
        this.send = send;
        return this;
    }
    public KeyCloakPasswordLessBuilder connection(String connection) {
        this.connection = connection;
        return this;
    }
    public KeyCloakPasswordLessBuilder client_id(String client_id) {
        this.client_id = client_id;
        return this;
    }
}
