package mohkarmon.a4moc.lebonjoint;


import java.util.List;

import mohkarmon.a4moc.lebonjoint.Models.Category;
import mohkarmon.a4moc.lebonjoint.Models.Favorite;
import mohkarmon.a4moc.lebonjoint.Models.Item;
import mohkarmon.a4moc.lebonjoint.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIEndpointInterface {
        // Request method and URL specified in the annotation


    @POST("Item/")
    Call<Item> createItem(@Body Item item);

    @POST("Favorite/")
    Call<Favorite> newFavorite(@Body Favorite favorite);

    @POST("Category/")
    Call<Category> createCategory(@Body Category category);

    @POST("user/")
    Call<User> createUser(@Body User user);



    }


