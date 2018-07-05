package mohkarmon.a4moc.lebonjoint.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("id")
    @Expose
    private int id;

    /**
     * No args constructor for use in serialization
     *
     */
    public Category() {
    }

    /**
     *
     * @param id
     * @param name
     * @param image
     */
    public Category(String name, String image, int id) {
        super();
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
