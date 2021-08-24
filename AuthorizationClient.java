package md.quin.api.be.qa.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import md.quin.api.be.qa.builder.KeyCloak;
import md.quin.api.be.qa.data.LoginDataFactory;
import md.quin.api.be.qa.specs.LoginRequestSpec;

import static io.restassured.RestAssured.given;

public class AuthorizationClient {

    public AuthorizationClient(){}

    public void loginPortal() {
        Response response = given()
                .spec(LoginRequestSpec.loginPortalRequestSpec())
                .when()
                .post()
                .then()
                .spec(LoginRequestSpec.okResponse()).
                extract().response();

        new KeyCloak().setAccess_token(response.path(LoginDataFactory.access_token_value));
    }

    public void loginApp() {
        getSecurityCode();
        String accessToken = getAdminAccessToken();
        String id = getUserByEmail(accessToken);
        setUserPassword(id,accessToken);
        getAccessToken();
    }

    public void getAccessTokenByRefreshToken() {
        loginApp();
        getAccessTokenBasedOnRefreshToken();
    }

    public void deleteUser(){
        String accessToken = getAdminAccessToken();
        String id = getUserByEmail(accessToken);
        deleteUserByKeyCloak(accessToken, id);
    }

    private void setUserPassword(String id,String accessToken) {
        given()
                .spec(LoginRequestSpec.setUserPasswordRequest(accessToken, id))
                .when()
                .put()
                .then()
                .spec(LoginRequestSpec.noContentResponse());
    }
    private void getSecurityCode() {
        given()
                .spec(LoginRequestSpec.getUserCode())
                .when()
                .post()
                .then()
                .spec(LoginRequestSpec.okResponse());
    }
    private String getAdminAccessToken() {
        Response response = given()
                .spec(LoginRequestSpec.keycloakAdminLoginRequest())
                .when()
                .post()
                .then()
                .spec(LoginRequestSpec.okResponse()).
                extract().response();

        return response.path(LoginDataFactory.access_token_value);
    }
    private String getUserByEmail(String accessToken) {
        Response response = given()
                .spec(LoginRequestSpec.getUserByEmailRequest(accessToken))
                .when()
                .get()
                .then()
                .spec(LoginRequestSpec.okResponse()).
                extract().response();

       return response.path(LoginDataFactory.userIdentifier);
    }
    private void getAccessToken() {
        Response response = given()
                .spec(LoginRequestSpec.loginAppRequestSpec())
                .when()
                .post()
                .then()
                .spec(LoginRequestSpec.okResponse())
                .extract().response();

        new KeyCloak().setAccess_token(response.path(LoginDataFactory.access_token_value));
        new KeyCloak().setRefresh_token(response.path(LoginDataFactory.refresh_token_value));
    }
    private void getAccessTokenBasedOnRefreshToken() {
        Response response = given()
                .spec(LoginRequestSpec.loginAppUsingRefreshTokenSpec())
                .when()
                .post()
                .then()
                .spec(LoginRequestSpec.okResponse())
                .extract().response();

        new KeyCloak().setAccess_token(response.path(LoginDataFactory.access_token_value));
    }
    private void deleteUserByKeyCloak(String accessToken, String id) {
        RestAssured.given()
                .spec(LoginRequestSpec.deleteUserSpec(accessToken, id))
                .when()
                .delete()
                .then()
                .spec(LoginRequestSpec.noContentResponse());
    }
}
