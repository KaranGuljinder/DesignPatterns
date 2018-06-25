package mohkarmon.a4moc.lebonjoint.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import mohkarmon.a4moc.lebonjoint.Models.Favorite;
import mohkarmon.a4moc.lebonjoint.R;

public class FavoriteAdapter extends RecyclerView.Adapter  {

    private Context mContext;
    private List<Favorite> favoriteList;

    public FavoriteAdapter(Context mContext, List<Favorite> favorites) {
        this.mContext = mContext;
        this.favoriteList = favorites;
    }


    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_unit, parent, false);
        return new FavoriteHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Favorite fav= favoriteList.get(position);

        ((FavoriteHolder) holder).bind(fav);

    }


    private class FavoriteHolder extends RecyclerView.ViewHolder {

        FavoriteHolder(View itemView) {
            super(itemView);
        }

        void bind(Favorite fav) {

        }
    }
}
