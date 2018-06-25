package mohkarmon.a4moc.lebonjoint.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mohkarmon.a4moc.lebonjoint.Models.Category;
import mohkarmon.a4moc.lebonjoint.Models.Item;
import mohkarmon.a4moc.lebonjoint.R;

public class CategoriesAdapter extends RecyclerView.Adapter  {

    private Context mContext;
    private List<Category> categoriesList;

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
        Category cat= categoriesList.get(position);
        ((CategoryHolder) holder).bind(cat);

    }


    private class CategoryHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView categoryName;

        CategoryHolder(View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.categoryImg);
            categoryName = itemView.findViewById(R.id.categoryName);

        }

        void bind(Category cat) {
            categoryName.setText("hi");
        }
    }
}
