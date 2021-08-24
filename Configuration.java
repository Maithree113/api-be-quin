package md.quin.api.be.qa.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
    "system:properties",
    "classpath:api.properties"})
public interface Configuration extends Config {

    @Key("api.keycloak.uri")
    String keyCloakURI();

    @Key("api.portalBase.uri")
    String portalBaseURI();

    @Key("api.appBase.uri")
    String appBaseURI();

    @Key("api.cms.uri")
    String cmsURI();

    @Key("api.keycloak.portal.token")
    String keyCloakPortalTokenUri();

    @Key("api.keycloak.master")
    String keyCloakMasterUri();

    @Key("api.keycloak.app.token")
    String keyCloakAppTokenUri();

    @Key("api.keycloak.app.user")
    String keyCloakAppUserUri();

    @Key("api.keycloak.passwordLess")
    String keyCloakPasswordLessUri();

    @Key("api.keycloak.app.resetPassword")
    String keyCloakResetPasswordUri();
}
