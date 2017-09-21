package com.example.vuphi.order.OrderDrink;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.vuphi.networkvolley.MySingleton;
import com.example.vuphi.orderfoodapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vuphi on 4/1/2017.
 */

public class ListDrinkAdapter extends RecyclerView.Adapter<ListDrinkAdapter.ViewHolder> implements Filterable {
    public List<ItemDrink> drinkList;
    public List<ItemDrink> drinkListFilter;
    public Context drinkContext;
    FragmentManager fragManager;
    FragmentDrink fragDrink;
    private ImageLoader imageLoaderDrink;

    public ListDrinkAdapter(List<ItemDrink> drinkList, Context drinkContext, FragmentDrink fragDrink) {
        this.drinkList = drinkList;
        drinkListFilter = drinkList;
        this.drinkContext = drinkContext;
        this.fragDrink = fragDrink;
    }

    public Context getContext() {
        return drinkContext;
    }

    @Override
    public ListDrinkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View drinkView = inflater.inflate(R.layout.item_drink, parent, false);

        ListDrinkAdapter.ViewHolder viewHolder = new ListDrinkAdapter.ViewHolder(drinkView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListDrinkAdapter.ViewHolder holder, int position) {

        final String nameDrink = drinkListFilter.get(position).getNameDrinks();
        final Integer costDrink = drinkListFilter.get(position).getCostDrinks();
        final String imageDrink = drinkListFilter.get(position).getImageDrinks();

        imageLoaderDrink = MySingleton.getInstance(getContext()).getImageLoader();
        imageLoaderDrink.get(imageDrink, ImageLoader.getImageListener(holder.imgDrink,
                R.drawable.icon_gallery, android.R.drawable.ic_dialog_alert));

        holder.imgDrink.setImageUrl(imageDrink, imageLoaderDrink);
        holder.txtDrinkName.setText(nameDrink);
//        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        holder.txtCostDrink.setText(costDrink + "");

        holder.setOnItemClickListener(new OnItemDrinkClickListener() {
            @Override
            public void OnItemDrinkClickListener(int position) {
                openDialogFragmentDrink(nameDrink, costDrink, imageDrink);
            }
        });


    }

    @Override
    public int getItemCount() {
        return drinkListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    drinkListFilter = drinkList;
                } else {
                    List<ItemDrink> filterList = new ArrayList<>();
                    for (ItemDrink itemDrink : drinkList) {
                        if (itemDrink.getNameDrinks().toString().contains(charString)
                                || (String.valueOf(itemDrink.getCostDrinks().intValue()).contains(charString))) {
                            filterList.add(itemDrink);
                        }
                    }
                    drinkListFilter = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = drinkListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                drinkListFilter = (List<ItemDrink>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        NetworkImageView imgDrink;
        TextView txtDrinkName, txtCostDrink;
        OnItemDrinkClickListener itemDrinkClick;

        public ViewHolder(View itemView) {
            super(itemView);
            imgDrink = (NetworkImageView) itemView.findViewById(R.id.img_drink);
            txtDrinkName = (TextView) itemView.findViewById(R.id.txt_drink_name);
            txtCostDrink = (TextView) itemView.findViewById(R.id.txt_drink_cost);

            itemView.setOnClickListener(this);

        }

        public void setOnItemClickListener(OnItemDrinkClickListener onItemDrinkClickListener) {
            itemDrinkClick = onItemDrinkClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemDrinkClick.OnItemDrinkClickListener(this.getLayoutPosition());
        }
    }

    private void openDialogFragmentDrink(String nameDrink, Integer costDrink, String imageDrink) {
        fragManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putString("name_drink", nameDrink);
        bundle.putInt("cost_drink", costDrink);
        bundle.putString("image_drink", imageDrink);

        CustomDialogDrink cusDlDrink = CustomDialogDrink.newInstance("");
        cusDlDrink.setArguments(bundle);

        // để gọi đc FragmentMainDish thì ta cần phải tạo Constructor cho nó
        cusDlDrink.setTargetFragment(fragDrink, 111);
        cusDlDrink.show(fragManager, "CustomDialogDrink");
    }
}
