package mohkarmon.a4moc.lbj.Screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mohkarmon.a4moc.lbj.APIEndpointInterface;
import mohkarmon.a4moc.lbj.Adapters.ItemsAdapter;
import mohkarmon.a4moc.lbj.LoginActivity;
import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.PrefUtils;
import mohkarmon.a4moc.lbj.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile extends Fragment {
    private ItemsAdapter itemsAdapter;
    private ConnectedUser cUser;
    private TextView pUsername;
    private TextView pSoldNb;
    private TextView pNbAds;
    private APIEndpointInterface apiEndpointInterface;
    private final List<Item> myAdsList = new ArrayList<>();
    public Profile() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_profile, container, false);
        RecyclerView myAdsRecycler = rootView.findViewById(R.id.myAdsRecycler);
        itemsAdapter = new ItemsAdapter(this.getContext(),myAdsList);
        cUser = ConnectedUser.getInstance();
        pUsername = rootView.findViewById(R.id.profileUsername);
        pSoldNb = rootView.findViewById(R.id.profileSold);
        pNbAds = rootView.findViewById(R.id.profileAdsNb);
        Button logout = rootView.findViewById(R.id.profileLogout);
        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);

        initUI();
        myAdsRecycler.setAdapter(itemsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        myAdsRecycler.setLayoutManager(llm);
        LoadItems();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*      if(cUser.getAuthType() == "Google"){
                    //TODO Handle google logout
                }*/
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                PrefUtils pref = new PrefUtils(getActivity());
                pref.clearToken();
                startActivity(intent);

            }
        });
        return rootView;
    }

    private void initUI(){
        pUsername.setText(cUser.getUsername());
        pNbAds.setText(String.valueOf(cUser.getNbAds()));
        pSoldNb.setText(String.valueOf(cUser.getNbSold()));

    }

    private void LoadItems(){
        Call<Item[]> call = apiEndpointInterface.GetUserItems(cUser.getUserid());
        call.enqueue(new Callback<Item[]>() {
            @Override
            public void onResponse(Call<Item[]> call, Response<Item[]> response) {
                Item[] items = response.body();
                myAdsList.addAll(Arrays.asList(items));
                itemsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Item[]> call, Throwable t) {
                Log.d("azeza",t.getMessage());
            }

        });
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
