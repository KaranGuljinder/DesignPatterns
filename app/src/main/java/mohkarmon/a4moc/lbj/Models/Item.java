package mohkarmon.a4moc.lbj.Models;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable
{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("ImgPath")
    @Expose
    private String imgPath;
    @SerializedName("phoneNb")
    @Expose
    private String phoneNb;
    @SerializedName("SellerID")
    @Expose
    private Integer sellerID;
    @SerializedName("id")
    @Expose
    private Integer id;
    public final static Parcelable.Creator<Item> CREATOR = new Creator<Item>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return (new Item[size]);
        }

    }
            ;

    protected Item(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.categoryID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.imgPath = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNb = ((String) in.readValue((String.class.getClassLoader())));
        this.sellerID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPhoneNb() {
        return phoneNb;
    }

    public void setPhoneNb(String phoneNb) {
        this.phoneNb = phoneNb;
    }

    public Integer getSellerID() {
        return sellerID;
    }

    public void setSellerID(Integer sellerID) {
        this.sellerID = sellerID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(city);
        dest.writeValue(description);
        dest.writeValue(state);
        dest.writeValue(price);
        dest.writeValue(categoryID);
        dest.writeValue(imgPath);
        dest.writeValue(phoneNb);
        dest.writeValue(sellerID);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }


public Item(String name, String city, String description, String state, Integer price,String imgUrl ,Integer categoryID, Integer sellerID,String phoneNb) {
        this.name = name;
        this.city = city;
        this.description = description;
        this.state = state;
        this.price = price;
        this.phoneNb = phoneNb;
        this.imgPath = imgUrl;
        this.categoryID = categoryID;
        this.sellerID = sellerID;
    }


}