package mohkarmon.a4moc.lbj.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mohkarmon.a4moc.lbj.APIEndpointInterface;
import mohkarmon.a4moc.lbj.Adapters.ItemsAdapter;
import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.Favorite;
import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkedAds extends Fragment {
    private final List<Item> bookmarkedList = new ArrayList<>();
    private APIEndpointInterface apiEndpointInterface;
    private ConnectedUser cUser;

    public BookmarkedAds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookmarked_ads, container, false);
        RecyclerView bookmarkedRecycler = rootView.findViewById(R.id.bookmarkedRecycler);
        ItemsAdapter itemsAdapter = new ItemsAdapter(this.getContext(), bookmarkedList);

        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);
        cUser = ConnectedUser.getInstance();

        itemsAdapter.notifyDataSetChanged();
        bookmarkedRecycler.setAdapter(itemsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        bookmarkedRecycler.setLayoutManager(llm);
        return rootView;
    }
    private void getFavs(){
        Call<Favorite[]> call = apiEndpointInterface.getUserFavs(cUser.getUserid());
        call.enqueue(new Callback<Favorite[]>() {

            @Override
            public void onResponse(Call<Favorite[]> call, Response<Favorite[]> response) {
                Favorite[] favs = response.body();
                for(Favorite fav: favs){

                }


            }

            @Override
            public void onFailure(Call<Favorite[]> call, Throwable t) {

            }
        });
    }

}
