package mohkarmon.a4moc.lebonjoint.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohkarmon.a4moc.lebonjoint.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkedAds extends Fragment {


    public BookmarkedAds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarked_ads, container, false);
    }

}
