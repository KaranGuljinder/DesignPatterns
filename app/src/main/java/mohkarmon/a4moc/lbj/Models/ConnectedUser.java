package mohkarmon.a4moc.lebonjoint.Models;

public class ConnectedUser {
    private static ConnectedUser INSTANCE = new ConnectedUser();

    private String username;
    private int userid;
    private String authType;
    private String email;




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

    private ConnectedUser() {};

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
