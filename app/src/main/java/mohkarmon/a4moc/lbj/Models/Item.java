package mohkarmon.a4moc.lebonjoint.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("Name")
    @Expose
    private Object name;
    @SerializedName("City")
    @Expose
    private Object city;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("State")
    @Expose
    private Object state;
    @SerializedName("Price")
    @Expose
    private Object price;
    @SerializedName("SellerID")
    @Expose
    private Object sellerID;
    @SerializedName("id")
    @Expose
    private Object id;

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getSellerID() {
        return sellerID;
    }

    public void setSellerID(Object sellerID) {
        this.sellerID = sellerID;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

}

