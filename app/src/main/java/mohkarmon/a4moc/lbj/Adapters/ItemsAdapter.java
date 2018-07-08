package mohkarmon.a4moc.lebonjoint.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mohkarmon.a4moc.lebonjoint.Models.Item;
import mohkarmon.a4moc.lebonjoint.R;
import mohkarmon.a4moc.lebonjoint.Screens.Ad;

public class ItemsAdapter extends RecyclerView.Adapter  {

    private Context mContext;
    private List<Item> itemsList;

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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ad ad= new Ad();
                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.navFrame, ad)
                        .commit();
            }
        });
        return new ItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item fav= itemsList.get(position);

        ((ItemHolder) holder).bind(fav);

    }


    private class ItemHolder extends RecyclerView.ViewHolder {

        ItemHolder(View itemView) {
            super(itemView);
        }

        void bind(Item itm) {

        }
    }
}
