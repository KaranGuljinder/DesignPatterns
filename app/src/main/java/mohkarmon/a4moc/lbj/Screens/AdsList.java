package mohkarmon.a4moc.lbj.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mohkarmon.a4moc.lbj.APIEndpointInterface;
import mohkarmon.a4moc.lbj.Adapters.ItemsAdapter;
import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdsList extends Fragment {

    private ItemsAdapter itemsAdapter;
    private final List<Item> itemsList = new ArrayList<>();
    private APIEndpointInterface apiEndpointInterface;


    public AdsList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ads_list, container, false);
        Toolbar appToolbar = rootView.findViewById(R.id.appToolbar);
        Bundle b = getArguments();
        String catName = b.getString("categoryName");
        final int catID = b.getInt("categoryID");
        appToolbar.setTitle(catName);
        appToolbar.inflateMenu(R.menu.actionbar);
        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);

        appToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.action_additem)
                {
                    AddAd addAd= new AddAd();
                    Bundle bd = new Bundle();
                    bd.putInt("catID",catID);
                    addAd.setArguments(bd);
                    ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.navFrame, addAd)
                            .commit();
                }

                return false;
            }
        });
        appToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        appToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        RecyclerView itemsRecycler = rootView.findViewById(R.id.itemsRecycler);
        itemsAdapter = new ItemsAdapter(this.getContext(),itemsList);

        itemsRecycler.setAdapter(itemsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        itemsRecycler.setLayoutManager(llm);

        LoadItems(catID);

        return rootView;
    }
    private void LoadItems(int id){
        itemsList.clear();
        itemsAdapter.notifyDataSetChanged();
        Call<Item[]> call = apiEndpointInterface.getItems(id);
        call.enqueue(new Callback<Item[]>() {
            @Override
            public void onResponse(Call<Item[]> call, Response<Item[]> response) {
                Item[] items = response.body();
                itemsList.addAll(Arrays.asList(items));
                itemsAdapter.notifyDataSetChanged();
                Log.d("azeza","yees");
            }

            @Override
            public void onFailure(Call<Item[]> call, Throwable t) {
            Log.d("azeza",t.getMessage());
            }

        });
    }


}
