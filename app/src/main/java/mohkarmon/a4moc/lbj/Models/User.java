package mohkarmon.a4moc.lbj.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("UserName")
    @Expose
    private String userName;

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
     * @param id
     * @param authMethod
     * @param email
     * @param nbAds
     * @param nbSold
     * @param userName
     */
    public User(int id ,String userName, int nbAds, int nbSold, String email, String authMethod) {
        super();
        this.id = id;
        this.userName = userName;
        this.nbAds = nbAds;
        this.nbSold = nbSold;
        this.email = email;
        this.authMethod = authMethod;
    }

    public User(String userName, String email, String authMethod) {
        this.userName = userName;
        this.email = email;
        this.authMethod = authMethod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getId() {
        return id;
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

