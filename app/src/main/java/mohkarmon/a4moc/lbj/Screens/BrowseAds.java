package mohkarmon.a4moc.lebonjoint.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mohkarmon.a4moc.lebonjoint.Adapters.CategoriesAdapter;
import mohkarmon.a4moc.lebonjoint.Models.Category;
import mohkarmon.a4moc.lebonjoint.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseAds extends Fragment {

    private RecyclerView categoriesRecycler;
    private CategoriesAdapter catAdapter;
    private List<Category> categoryList = new ArrayList<>();

    public BrowseAds() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_ads, container, false);
        categoriesRecycler = rootView.findViewById(R.id.categoriesRecycler);
        catAdapter = new CategoriesAdapter(this.getContext(),categoryList);
        Category cat = new Category();
        cat.setName("test");
        categoryList.add(cat);
        categoryList.add(cat);
        categoryList.add(cat);
        categoryList.add(cat);
        categoryList.add(cat);

        catAdapter.notifyDataSetChanged();
        categoriesRecycler.setAdapter(catAdapter);
        RecyclerView.ItemDecoration dividerItemDecorationVert = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        RecyclerView.ItemDecoration dividerItemDecorationHor = new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL);
        categoriesRecycler.addItemDecoration(dividerItemDecorationHor);
        categoriesRecycler.addItemDecoration(dividerItemDecorationVert);
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
        categoriesRecycler.setLayoutManager(glm);
        categoriesRecycler.setHasFixedSize(true);

        return rootView;
    }

}
