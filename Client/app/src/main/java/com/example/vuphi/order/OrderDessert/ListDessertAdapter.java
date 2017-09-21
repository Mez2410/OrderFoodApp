package com.example.vuphi.order.OrderDessert;

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
 * Created by vuphi on 4/2/2017.
 */

public class ListDessertAdapter extends RecyclerView.Adapter<ListDessertAdapter.ViewHolder> implements Filterable {

    public List<ItemDessert> dessertList;
    public List<ItemDessert> lstDessertFilter;
    public Context dessertContext;
    private FragmentManager fragManager;
    FragmentDessert fragDessert;
    private ImageLoader imageLoader;

    public ListDessertAdapter(List<ItemDessert> dessertList, Context dessertContext, FragmentDessert fragDessert) {
        this.dessertList = dessertList;
        this.lstDessertFilter = dessertList;
        this.dessertContext = dessertContext;
        this.fragDessert = fragDessert;
    }

    public Context getContext() {
        return dessertContext;
    }

    @Override
    public ListDessertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View dessertView = inflater.inflate(R.layout.item_dessert, parent, false);

        ViewHolder viewHolder = new ViewHolder(dessertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListDessertAdapter.ViewHolder holder, int position) {

        final String nameFood = lstDessertFilter.get(position).getNameDessert();
        final Integer costFood = lstDessertFilter.get(position).getCostDessert();
        final String imageDessert = lstDessertFilter.get(position).getImageDessert();

        imageLoader = MySingleton.getInstance(getContext()).getImageLoader();
        imageLoader.get(imageDessert, ImageLoader.getImageListener(holder.imgDessert,
                R.drawable.icon_gallery, android.R.drawable.ic_dialog_alert));

        holder.txtDessertName.setText(nameFood);

//        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        holder.txtCostDessert.setText(costFood + "");

        holder.imgDessert.setImageUrl(imageDessert, imageLoader);

        holder.setOnItemDesClkListener(new OnItemDessertClickListener() {
            @Override
            public void onItemDessertClickListener(int position) {
                openDialogFragment(nameFood, costFood, imageDessert);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstDessertFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    lstDessertFilter = dessertList;
                } else {
                    List<ItemDessert> filterList = new ArrayList<>();
                    for (ItemDessert itemDessert : dessertList) {
                        if (itemDessert.getNameDessert().toString().contains(charString)
                                || (String.valueOf(itemDessert.getCostDessert().intValue()).contains(charString))) {
                            filterList.add(itemDessert);
                        }
                    }
                    lstDessertFilter = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = lstDessertFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lstDessertFilter = (List<ItemDessert>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        NetworkImageView imgDessert;
        TextView txtDessertName, txtCostDessert;
        OnItemDessertClickListener OnItemDesClickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            imgDessert = (NetworkImageView) itemView.findViewById(R.id.img_dessert);
            txtDessertName = (TextView) itemView.findViewById(R.id.txt_dessert_name);
            txtCostDessert = (TextView) itemView.findViewById(R.id.txt_dessert_cost);

            itemView.setOnClickListener(this);

        }

        public void setOnItemDesClkListener (OnItemDessertClickListener onItemDesClkListen) {
            OnItemDesClickListener = onItemDesClkListen;
        }

        @Override
        public void onClick(View v) {
            this.OnItemDesClickListener.onItemDessertClickListener(this.getLayoutPosition());
        }
    }

    private void openDialogFragment(String name, Integer cost, String image) {
        fragManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putString("name_des", name);
        bundle.putInt("cost_des", cost);
        bundle.putString("image_des", image);

        CustomDialogDessert cusDlDes = CustomDialogDessert.newInstance("");
        cusDlDes.setArguments(bundle);

        // để gọi đc FragmentMainDish thì ta cần phải tạo Constructor cho nó
        cusDlDes.setTargetFragment(fragDessert, 111);
        cusDlDes.show(fragManager, "CustomDialogDessert");
    }
}
