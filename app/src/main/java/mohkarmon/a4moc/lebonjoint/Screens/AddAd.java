package mohkarmon.a4moc.lebonjoint.Screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import mohkarmon.a4moc.lebonjoint.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAd extends Fragment {

    private MaterialEditText itemName, itemDescription, price;
    private Spinner itemState;

    public AddAd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_ad, container, false);
        itemName = rootView.findViewById(R.id.addItemName);
        itemDescription = rootView.findViewById(R.id.addItemDescription);
        price = rootView.findViewById(R.id.addItemPrice);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.addItemState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.itemState, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return rootView;
    }
}