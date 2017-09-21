package com.example.vuphi.order.OrderMainDish;

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
 * Created by vuphi on 3/29/2017.
 */

public class ListMainDishAdapter extends RecyclerView.Adapter <ListMainDishAdapter.ViewHolder> implements Filterable {
    public List<ItemMainDish> mFoodList;
    public List<ItemMainDish> mFoodListFilter;
    public Context mContext;
    FragmentManager fragManager;
    FragmentMainDish fragFood;
    private ImageLoader imageLoader;

//    DecimalFormat decimalFormat = new DecimalFormat("#,###,###");

    public ListMainDishAdapter(List<ItemMainDish> foodList, Context mContext, FragmentMainDish fragFood) {
        super();
        mFoodList = foodList;
        mFoodListFilter = foodList;
        this.mContext = mContext;
        this.fragFood = fragFood;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ListMainDishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View foodView = inflater.inflate(R.layout.item_food, parent, false);

        ViewHolder viewHolder = new ViewHolder(foodView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListMainDishAdapter.ViewHolder holder, int position) {

        final String nameFood = mFoodListFilter.get(position).getFoodName();
        final Integer costFood = mFoodListFilter.get(position).getCostFood();
        final String imageFood = mFoodListFilter.get(position).getImageFood();

        imageLoader = MySingleton.getInstance(getContext()).getImageLoader();
        imageLoader.get(imageFood, ImageLoader.getImageListener(holder.imgFood,
                R.drawable.icon_gallery, android.R.drawable.ic_dialog_alert));

        holder.imgFood.setImageUrl(imageFood, imageLoader);
        holder.txtFoodName.setText(nameFood);
        holder.txtCostFood.setText(costFood + "");

        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                openDialogFragment(nameFood, costFood, imageFood);
            }
        });

//        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mFoodListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mFoodListFilter = mFoodList;
                } else {
                    List<ItemMainDish> filterList = new ArrayList<>();
                    for (ItemMainDish itemMainDish : mFoodList) {
                        if (itemMainDish.getFoodName().toString().contains(charString)
                                || (String.valueOf(itemMainDish.getCostFood().intValue()).contains(charString))) {
                            filterList.add(itemMainDish);
                        }
                    }
                    mFoodListFilter = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFoodListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFoodListFilter = (List<ItemMainDish>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        NetworkImageView imgFood;
        TextView txtFoodName, txtCostFood;
        OnItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            imgFood = (NetworkImageView) itemView.findViewById(R.id.img_food);
            txtFoodName = (TextView) itemView.findViewById(R.id.txt_food_name);
            txtCostFood = (TextView) itemView.findViewById(R.id.txt_food_cost);

            itemView.setOnClickListener(this);

        }

        public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
            itemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.OnItemClickListener(this.getLayoutPosition());

        }
    }

    private void openDialogFragment(String nameFood, Integer costFood, String imageFood) {
        fragManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putString("name_food", nameFood);
        bundle.putInt("cost_food", costFood);
        bundle.putString("image_food", imageFood);

        CustomDialogMainDish cusDlFood = CustomDialogMainDish.newInstance("");
        cusDlFood.setArguments(bundle);

        // để gọi đc FragmentMainDish thì ta cần phải tạo Constructor cho nó
        cusDlFood.setTargetFragment(fragFood, 111);
        cusDlFood.show(fragManager, "CustomDialogMainDish");
    }
}


