package mohkarmon.a4moc.lbj;


import mohkarmon.a4moc.lbj.Models.Category;
import mohkarmon.a4moc.lbj.Models.Favorite;
import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface APIEndpointInterface {
        // Request method and URL specified in the annotation


    @POST("Items/")
    Call<Item> createItem(@Body Item item);

    @GET("Items/{uID}/GetUserItems")
    Call<Item[]> GetUserItems(@Path("uID") int uID);

    @GET("Items/{CategoryID}/getItems")
    Call<Item[]> getItems(@Path("CategoryID") int CategoryID);

    @POST("Favorites/")
    Call<Favorite> newFavorite(@Body Favorite favorite);

    @DELETE("Favorites/{uID}/{iID}/delFav")
    Call<Favorite> delFav(@Path("uID") int uID,@Path("iID") int iID);


    @GET("Favorites/{uID}/GetUserFavs")
    Call<Favorite[]> getUserFavs(@Path("uID") int uID);

    @POST("Category/")
    Call<Category> createCategory(@Body Category category);

    @POST("users/")
    Call<User> registerUser(@Body User user);

    @GET("users/{email}/getUser")
    Call<User> getUser(@Path("email") String email);

    @GET("Categories")
    Call<Category[]> getCategories();


    }


