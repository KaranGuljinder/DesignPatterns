package mohkarmon.a4moc.lbj.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mohkarmon.a4moc.lbj.APIEndpointInterface;
import mohkarmon.a4moc.lbj.Adapters.CategoriesAdapter;
import mohkarmon.a4moc.lbj.Models.Category;
import mohkarmon.a4moc.lbj.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseAds extends Fragment {

    private CategoriesAdapter catAdapter;
    private final List<Category> categoryList = new ArrayList<>();
    private APIEndpointInterface apiEndpointInterface;

    public BrowseAds() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_ads, container, false);
        RecyclerView categoriesRecycler = rootView.findViewById(R.id.categoriesRecycler);
        catAdapter = new CategoriesAdapter(this.getContext(),categoryList);

        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);

        catAdapter.notifyDataSetChanged();
        categoriesRecycler.setAdapter(catAdapter);
        RecyclerView.ItemDecoration dividerItemDecorationVert = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        RecyclerView.ItemDecoration dividerItemDecorationHor = new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL);
        categoriesRecycler.addItemDecoration(dividerItemDecorationHor);
        categoriesRecycler.addItemDecoration(dividerItemDecorationVert);
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
        categoriesRecycler.setLayoutManager(glm);
        categoriesRecycler.setHasFixedSize(true);
        getCategories();

        return rootView;
    }

    private void getCategories(){
        categoryList.clear();
        catAdapter.notifyDataSetChanged();
        Call< Category[]> call = apiEndpointInterface.getCategories();
        call.enqueue(new Callback< Category[]>() {

            @Override
            public void onResponse(Call< Category[]> call, Response< Category[]> response) {
                Category[] categories = response.body();
                categoryList.addAll(Arrays.asList(categories));
                catAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call< Category[]> call, Throwable t) {

            }
        });
    }

}
