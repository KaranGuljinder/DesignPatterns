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
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import mohkarmon.a4moc.lbj.Models.Category;
import mohkarmon.a4moc.lbj.R;
import mohkarmon.a4moc.lbj.Screens.AdsList;

public class CategoriesAdapter extends RecyclerView.Adapter  {

    private final Context mContext;
    private final List<Category> categoriesList;

    public CategoriesAdapter(Context mContext, List<Category> categories) {
        this.mContext = mContext;
        this.categoriesList = categories;
    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_unit, parent, false);

        return new CategoryHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Category cat= categoriesList.get(position);
        ((CategoryHolder) holder).bind(cat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdsList adsList= new AdsList();
                Bundle bundleV = new Bundle();
                bundleV.putInt("categoryID",cat.getId());
                bundleV.putString("categoryName",cat.getName());
                adsList.setArguments(bundleV);
                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.navFrame, adsList)
                        .commit();
            }});
    }


    private class CategoryHolder extends RecyclerView.ViewHolder {
        final ImageView categoryImg;
        final TextView categoryName;

        CategoryHolder(View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.categoryImg);
            categoryName = itemView.findViewById(R.id.categoryName);

        }

        void bind(Category cat) {
            categoryName.setText(cat.getName());
            RequestOptions cropOptions = new RequestOptions().circleCrop();
                Uri myUri = Uri.parse(cat.getImage());
                Glide.with(mContext)
                        .load(myUri)
                        .apply(cropOptions)
                        .into(categoryImg);

        }
    }
}
