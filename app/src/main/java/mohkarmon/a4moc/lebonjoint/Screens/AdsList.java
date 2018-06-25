package mohkarmon.a4moc.lebonjoint.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mohkarmon.a4moc.lebonjoint.Adapters.CategoriesAdapter;
import mohkarmon.a4moc.lebonjoint.Adapters.ItemsAdapter;
import mohkarmon.a4moc.lebonjoint.Models.Item;
import mohkarmon.a4moc.lebonjoint.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdsList extends Fragment {

    private RecyclerView itemsRecycler;
    private ItemsAdapter itemsAdapter;
    private List<Item> itemsList = new ArrayList<>();


    public AdsList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ads_list, container, false);

        itemsRecycler = rootView.findViewById(R.id.itemsRecycler);
        itemsAdapter = new ItemsAdapter(this.getContext(),itemsList);
        Item item = new Item();
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);
        itemsList.add(item);

        itemsAdapter.notifyDataSetChanged();
        itemsRecycler.setAdapter(itemsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        itemsRecycler.setLayoutManager(llm);
        return rootView;
    }

}
