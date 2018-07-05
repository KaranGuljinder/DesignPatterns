package mohkarmon.a4moc.lebonjoint.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("nbAds")
    @Expose
    private int nbAds;
    @SerializedName("nbSold")
    @Expose
    private int nbSold;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("authMethod")
    @Expose
    private String authMethod;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param authMethod
     * @param email
     * @param nbAds
     * @param nbSold
     * @param userName
     * @param password
     * @param city
     */
    public User(String userName, String password, String city, int nbAds, int nbSold, String email, String authMethod) {
        super();
        this.userName = userName;
        this.password = password;
        this.city = city;
        this.nbAds = nbAds;
        this.nbSold = nbSold;
        this.email = email;
        this.authMethod = authMethod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNbAds() {
        return nbAds;
    }

    public void setNbAds(int nbAds) {
        this.nbAds = nbAds;
    }

    public int getNbSold() {
        return nbSold;
    }

    public void setNbSold(int nbSold) {
        this.nbSold = nbSold;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

}

