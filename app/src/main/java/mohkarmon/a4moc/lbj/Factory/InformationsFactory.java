package mohkarmon.a4moc.lbj.Factory;

import mohkarmon.a4moc.lbj.Facade.Informations;
import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.User;

public class InformationsFactory {

    public Informations getInformations(String InfoType) {
        if (InfoType == null) {
            return null;
        }
        if(InfoType.equalsIgnoreCase("USER")){
            return new User();

        } else if(InfoType.equalsIgnoreCase("CONNECTED")) {
            return new ConnectedUser();
        }
    return null;
    }


}
