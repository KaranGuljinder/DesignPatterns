package mohkarmon.a4moc.lbj.Models;

import mohkarmon.a4moc.lbj.Facade.Informations;

public class ConnectedUser implements Informations {
    private static final ConnectedUser INSTANCE = new ConnectedUser();

    private String username;
    private int userid;

    public int getNbAds() {
        return nbAds;
    }

    public void setNbAds(int nbAds) {
        this.nbAds = nbAds;
    }

    public void setNbSold(int nbSold) {
        this.nbSold = nbSold;
    }

    public int getNbSold() {
        return nbSold;

    }

    private String authType;
    private String email;
    private int nbAds;
    private int nbSold;




    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConnectedUser() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public static ConnectedUser getInstance() {
        return(INSTANCE);
    }
}
