package mohkarmon.a4moc.lebonjoint.Screens;

import android.content.Context;
import android.net.Uri;
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


public class Profile extends Fragment {
    private RecyclerView myAdsRecycler;
    private ItemsAdapter itemsAdapter;
    private List<Item> myAdsList = new ArrayList<>();
    public Profile() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_profile, container, false);
        myAdsRecycler = rootView.findViewById(R.id.myAdsRecycler);
        itemsAdapter = new ItemsAdapter(this.getContext(),myAdsList);
        Item item = new Item();
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);
        myAdsList.add(item);

        itemsAdapter.notifyDataSetChanged();
        myAdsRecycler.setAdapter(itemsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        myAdsRecycler.setLayoutManager(llm);
        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
