package md.quin.api.be.qa;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import md.quin.api.be.qa.builder.KeyCloak;
import md.quin.api.be.qa.client.AuthorizationClient;
import md.quin.api.be.qa.config.Configuration;
import md.quin.api.be.qa.config.ConfigurationManager;
import md.quin.api.be.qa.data.LoginDataFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AppBaseApi{

    protected static Configuration configuration = ConfigurationManager.getConfiguration();

    @BeforeAll
    public static void beforeAllAppTests() {
        RestAssured.useRelaxedHTTPSValidation();
        new AuthorizationClient().loginApp();
    }

    private RequestSpecification setAccessToken() {
        return new RequestSpecBuilder()
                .addHeader(LoginDataFactory.authorization,
                        LoginDataFactory.bearer + new KeyCloak().getAccess_token())
                .build();
    }

     static RequestSpecification set() {
        return new RequestSpecBuilder().
                setBaseUri(configuration.appBaseURI()).
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }

    @AfterAll
    public static void afterAllTests() {
        new AuthorizationClient().deleteUser();
    }

    /**@BeforeAll
    public static void beforeAllPortalTests() {
    cmsURI = configuration.cmsURI();
    RestAssured.useRelaxedHTTPSValidation();
    new AuthorizationClient().loginPortal();
    }**/

}
