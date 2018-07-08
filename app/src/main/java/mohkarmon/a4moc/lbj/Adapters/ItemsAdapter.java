package mohkarmon.a4moc.lbj.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.R;
import mohkarmon.a4moc.lbj.Screens.Ad;

public class ItemsAdapter extends RecyclerView.Adapter  {

    private final Context mContext;
    private final List<Item> itemsList;

    public ItemsAdapter(Context mContext, List<Item> items) {
        this.mContext = mContext;
        this.itemsList = items;
    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unit, parent, false);

        return new ItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item= itemsList.get(position);

        ((ItemHolder) holder).bind(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ad ad= new Ad();
                Bundle bd = new Bundle();
                bd.putParcelable("item",item);
                ad.setArguments(bd);
                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.navFrame, ad)
                        .commit();
            }
        });

    }


    private class ItemHolder extends RecyclerView.ViewHolder {
        final ImageView itemImg;
        final TextView itemName;
        final TextView itemPrice;
        final TextView itemCategory;
        final TextView itemState;
        TextView itemStateofSale;
        final TextView itemCity;
        ItemHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemCategory = itemView.findViewById(R.id.itemCategory);
            itemState = itemView.findViewById(R.id.itemState);
            itemCity = itemView.findViewById(R.id.itemCity);
            itemImg = itemView.findViewById(R.id.fuItemPic);
        }

        void bind(Item itm) {
            itemName.setText(itm.getName());
            itemPrice.setText(itm.getPrice().toString());
            itemState.setText(itm.getState());
            itemCity.setText(itm.getCity());
            Uri myUri = Uri.parse(itm.getImgPath());
            Glide.with(mContext)
                    .load(myUri)
                    .into(itemImg);
        }
    }
}
