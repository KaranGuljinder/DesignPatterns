package mohkarmon.a4moc.lbj.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorite {

    @SerializedName("userID")
    @Expose
    private int userID;
    @SerializedName("itemID")
    @Expose
    private int itemID;

    /**
     * No args constructor for use in serialization
     *
     */
    public Favorite() {
    }

    /**
     *
     * @param userID
     * @param itemID
     */
    public Favorite(int userID, int itemID) {
        super();
        this.userID = userID;
        this.itemID = itemID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

}