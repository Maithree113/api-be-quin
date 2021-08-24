package md.quin.api.be.qa.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import md.quin.api.be.qa.config.Configuration;
import md.quin.api.be.qa.config.ConfigurationManager;
import md.quin.api.be.qa.data.LoginDataFactory;
import org.apache.http.HttpStatus;

public class LoginRequestSpec{

    LoginRequestSpec(){}

    protected static Configuration configuration = ConfigurationManager.getConfiguration();

    private static RequestSpecification set(String keyCloakURI) {
        return new RequestSpecBuilder().
                setBaseUri(keyCloakURI).
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }
    public static RequestSpecification loginPortalRequestSpec() {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakPortalTokenUri())).
                setContentType(ContentType.URLENC.withCharset("UTF-8")).
                addFormParams(new LoginDataFactory().setPortalBody()).
                build();
    }
    public static RequestSpecification loginAppRequestSpec() {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakAppTokenUri())).
                setContentType(ContentType.URLENC.withCharset("UTF-8")).
                addFormParams(new LoginDataFactory().getAccessCodeBody()).
                build();
    }
    public static RequestSpecification getUserCode() {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakPasswordLessUri())).
                setContentType(ContentType.JSON).
                setBody(new LoginDataFactory().generateCodeBody()).
                build();
    }
    public static RequestSpecification keycloakAdminLoginRequest() {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakMasterUri())).
                setContentType(ContentType.URLENC.withCharset("UTF-8")).
                addFormParams(new LoginDataFactory().getAdminAccessCodeBody()).
                build();
    }
    public static RequestSpecification getUserByEmailRequest(String accessToken) {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakAppUserUri())).
                setContentType(ContentType.JSON).
                addHeader(LoginDataFactory.authorization, LoginDataFactory.bearer + accessToken).
                addQueryParam(LoginDataFactory.searchQueryParam,
                        LoginDataFactory.searchUserByEmail()).
                build();
    }
    public static RequestSpecification setUserPasswordRequest(String accessToken, String id) {
        return new RequestSpecBuilder().
                addRequestSpecification(set(String.format(configuration.keyCloakResetPasswordUri(), id))).
                setContentType(ContentType.JSON).
                addHeader(LoginDataFactory.authorization, LoginDataFactory.bearer + accessToken).
                setBody(new LoginDataFactory().setUserPassword()).
                build();
    }
    public static RequestSpecification loginAppUsingRefreshTokenSpec() {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakAppTokenUri())).
                setContentType(ContentType.URLENC.withCharset("UTF-8")).
                addFormParams(new LoginDataFactory().getAccessCodeUsingRefreshTokenBody()).
                build();
    }
    public static RequestSpecification deleteUserSpec(String accessToken, String id) {
        return new RequestSpecBuilder().
                addRequestSpecification(set(configuration.keyCloakAppUserUri() + "/" + id)).
                setContentType(ContentType.JSON).
                addHeader(LoginDataFactory.authorization, LoginDataFactory.bearer + accessToken).
                build();
    }
    public static ResponseSpecification okResponse() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                build();
    }
    public static ResponseSpecification noContentResponse() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_NO_CONTENT).
                build();
    }
}
