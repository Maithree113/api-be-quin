package md.quin.api.be.qa.data;

import com.github.javafaker.Faker;
import md.quin.api.be.qa.builder.AuthParams;
import md.quin.api.be.qa.builder.KeyCloak;
import md.quin.api.be.qa.builder.KeyCloakPasswordLessBuilder;
import md.quin.api.be.qa.builder.KeyCloakUserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class LoginDataFactory {

    private static final Logger log = LogManager.getLogger(LoginDataFactory.class);
    private final Faker faker;
    private static String email;
    private static String code;

    private static String portal_client_id_value = "quin-portal";
    private static String portal_password = "Testing123!";
    private static String portal_username = "agertesting+nofa@gmail.com";

    private static String admin_username = "a.popa@quin.md";
    private static String admin_password = "123123";

    private static String app_client_id_value = "consumer-app";
    private static String admin_client_id_value = "security-admin-console";
    private static String grant_type_value = "password";
    private static String scope_value = "openid profile email offline_access";
    private static String response_type_value = "id_token token";
    private static String connection_value = "email";
    private static String code_value = "code";
    private static String grantType_key = "grant_type";
    private static String clientId_Key = "client_id";
    private static String password_Key = "password";
    private static String username_Key = "username";
    private static String scope_key = "scope";
    private static String response_type_key = "response_type";
    private static String grant_typeRefresh_value = "refresh_token";
    private static String refresh_token_key = "refresh_token";

    public static String authorization = "Authorization";
    public static String bearer = "Bearer ";
    public static String searchQueryParam = "search";
    public static String access_token_value = "access_token";
    public static String refresh_token_value = "refresh_token";
    public static String userIdentifier = "[0].id";

    public LoginDataFactory() {faker = new Faker();}

    public KeyCloakPasswordLessBuilder generateCodeBody() {
        email = faker.internet().emailAddress();
        return new KeyCloakPasswordLessBuilder().
                client_id(app_client_id_value).
                connection(connection_value)
                .send(code_value)
                .email(email)
                .authParams(new AuthParams(scope_value, response_type_value));
    }
    public Map<String, String> getAccessCodeBody() {
        return Map.of(clientId_Key, app_client_id_value,
                grantType_key, grant_type_value,
                password_Key, code,
                username_Key, email,
                scope_key, scope_value,
                response_type_key, response_type_value);
    }
    public Map<String, String> getAdminAccessCodeBody() {
        return Map.of(clientId_Key, admin_client_id_value,
                grantType_key, grant_type_value,
                password_Key, admin_password,
                username_Key, admin_username);
    }
    public KeyCloakUserBuilder setUserPassword() {
       code = String.valueOf(faker.number().randomNumber());
        return new KeyCloakUserBuilder().
               type(grant_type_value).
                value(code).
                temporary(false);
    }
    public Map<String, String> setPortalBody() {
        return Map.of(clientId_Key, portal_client_id_value,
                grantType_key, grant_type_value,
                password_Key, portal_password,
                username_Key, portal_username);
    }
    public static String searchUserByEmail() {
        return email;
    }

    public Map<String,?> getAccessCodeUsingRefreshTokenBody() {
        return Map.of(clientId_Key, app_client_id_value,
                grantType_key, grant_typeRefresh_value,
                refresh_token_key, new KeyCloak().getRefresh_token(),
                scope_key, scope_value,
                response_type_key, response_type_value);
    }
}
