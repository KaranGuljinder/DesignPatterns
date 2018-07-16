package mohkarmon.a4moc.lbj.Factory;

import mohkarmon.a4moc.lbj.Facade.Informations;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        InformationsFactory infoFactory = new InformationsFactory();
        Informations  user =  infoFactory.getInformations("USER");
        user.getUsername();

        Informations connected = infoFactory.getInformations("CONNECTED");
        connected.getUsername();

    }
}
