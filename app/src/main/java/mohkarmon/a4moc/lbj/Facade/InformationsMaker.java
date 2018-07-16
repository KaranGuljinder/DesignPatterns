package mohkarmon.a4moc.lbj.Facade;

import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.User;

public class InformationsMaker {
    private Informations user;
    private Informations connectedUser;
    private String email;
    private String username;
    private int nbAds;
    private int nbSold;

    public InformationsMaker() {
        user = new User();
        connectedUser = new ConnectedUser();
    }

    public void userInformations(){
        user.getEmail();
        user.getNbAds();
        user.getNbSold();
        user.getUsername();
        user.setEmail(email);
        user.setUsername(username);
        user.setNbSold(nbSold);
        user.setNbAds(nbAds);
    }
    public void connectedUserInformations(){
        connectedUser.getUsername();
        connectedUser.getNbSold();
        connectedUser.getNbAds();
        connectedUser.getEmail();
        connectedUser.setNbAds(nbAds);
        connectedUser.setNbSold(nbSold);
        connectedUser.setUsername(username);
        connectedUser.setEmail(email);
    }
}
