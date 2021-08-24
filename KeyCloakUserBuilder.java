package md.quin.api.be.qa.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class KeyCloakUserBuilder {

    private Boolean temporary;
    private String value;
    private String type;

    public KeyCloakUserBuilder temporary(Boolean temporary) {
        this.temporary = temporary;
        return this;
    }
    public KeyCloakUserBuilder value(String value) {
        this.value = value;
        return this;
    }
    public KeyCloakUserBuilder type(String type) {
        this.type = type;
        return this;
    }
}
