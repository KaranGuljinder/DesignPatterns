package mohkarmon.a4moc.lbj.Screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.rilixtech.materialfancybutton.MaterialFancyButton;

import mohkarmon.a4moc.lbj.APIEndpointInterface;
import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.Favorite;
import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Ad extends Fragment {

    private MaterialFancyButton addFav;
    private boolean isFav=false;
    private APIEndpointInterface apiEndpointInterface;
    private ConnectedUser cUser;
    private Item cItem;
    public Ad() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ad, container, false);
        cItem= getArguments().getParcelable("item");
        TextView itemName = rootView.findViewById(R.id.itemPageName);
        TextView itemPrice = rootView.findViewById(R.id.itemPagePrice);
        TextView itemDescription = rootView.findViewById(R.id.itemPageDescription);
        addFav = rootView.findViewById(R.id.addAdFav);
        MaterialFancyButton sendMsg = rootView.findViewById(R.id.adSendMsg);
        MaterialFancyButton call = rootView.findViewById(R.id.adCall);
        Toolbar appToolbar = rootView.findViewById(R.id.adToolbar);
        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);
        cUser = ConnectedUser.getInstance();
        appToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        appToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        TextView itemCity = rootView.findViewById(R.id.itemPageCity);
        ImageView itemImg = rootView.findViewById(R.id.itemPagePhoto);
        itemName.setText(cItem.getName());
        itemPrice.setText(cItem.getPrice().toString());
        itemDescription.setText(cItem.getDescription());
        itemCity.setText(cItem.getCity());
        appToolbar.setTitle(cItem.getName());
        addFav.setText("Favorite");

        Uri myUri = Uri.parse(cItem.getImgPath());
        Glide.with(getContext())
                .load(myUri)
                .into(itemImg);
        isItemFav();
        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFav){
                    addFavorite(cItem);

                }
                else{
                    removeFav(cItem);
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:"+cItem.getPhoneNb());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:"+cItem.getPhoneNb());
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(it);
            }
        });
        return rootView;
    }

    private void isItemFav(){
        Call<Favorite[]> call = apiEndpointInterface.getUserFavs(cUser.getUserid());
        call.enqueue(new Callback<Favorite[]>() {

            @Override
            public void onResponse(Call<Favorite[]> call, Response<Favorite[]> response) {
                Favorite[] favs = response.body();
                for(Favorite fav: favs){
                    if(fav.getItemID() == cItem.getId()){
                        isFav = true;
                        addFav.setTextColor(getResources().getColor(R.color.yellow));
                        addFav.setText("Fav !");
                    }

                }


            }

            @Override
            public void onFailure(Call<Favorite[]> call, Throwable t) {

            }
        });
    }

    private void addFavorite(Item cItem){

        Favorite fav = new Favorite();
        fav.setItemID(cItem.getId());
        fav.setUserID(cUser.getUserid());
        Call<Favorite> call = apiEndpointInterface.newFavorite(fav);
        call.enqueue(new Callback<Favorite>() {

            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                isFav = true;
                addFav.setTextColor(getResources().getColor(R.color.yellow));
                addFav.setText("Fav !");
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });
    }
    private void removeFav(Item cItem){


        Call<Favorite> call = apiEndpointInterface.delFav(cUser.getUserid(),cItem.getId());
        call.enqueue(new Callback<Favorite>() {

            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                isFav = false;
                addFav.setTextColor(getResources().getColor(R.color.white));
                addFav.setText("Favorite");            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });
    }
}
