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

import mohkarmon.a4moc.lebonjoint.Adapters.ItemsAdapter;
import mohkarmon.a4moc.lebonjoint.Models.Item;
import mohkarmon.a4moc.lebonjoint.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkedAds extends Fragment {
    private RecyclerView bookmarkedRecycler;
    private ItemsAdapter itemsAdapter;
    private List<Item> bookmarkedList = new ArrayList<>();

    public BookmarkedAds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookmarked_ads, container, false);
        bookmarkedRecycler = rootView.findViewById(R.id.bookmarkedRecycler);
        itemsAdapter = new ItemsAdapter(this.getContext(),bookmarkedList);
        Item item = new Item();
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);
        bookmarkedList.add(item);

        itemsAdapter.notifyDataSetChanged();
        bookmarkedRecycler.setAdapter(itemsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        bookmarkedRecycler.setLayoutManager(llm);
        return rootView;
    }

}
