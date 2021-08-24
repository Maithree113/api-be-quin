package md.quin.api.be.qa.builder;

public class KeyCloak
{
    private String refresh_token;

    private String access_token;

    public void setRefresh_token(String refresh_token){
        this.refresh_token = refresh_token;
    }
    public String getRefresh_token(){return this.refresh_token;}
    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }
    public String getAccess_token(){
        return this.access_token;
    }
}
