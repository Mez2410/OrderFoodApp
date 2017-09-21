package com.example.vuphi.kitchen.DeliveryFood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuphi.orderfoodapp.R;

import java.util.List;

/**
 * Created by vuphi on 7/20/2017.
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder>{

    public List<FoodOrder> lstDelivery;
    public Context mContext;

    public DeliveryAdapter(List<FoodOrder> lstDelivery, Context mContext) {
        this.lstDelivery = lstDelivery;
        this.mContext = mContext;
    }

    private Context getContext () {
        return mContext;
    }

    @Override
    public DeliveryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery, parent, false);
        MyViewHolder vHdl = new MyViewHolder(view);
        return vHdl;
    }

    @Override
    public void onBindViewHolder(DeliveryAdapter.MyViewHolder holder, int position) {
        String nameFood = lstDelivery.get(position).getNameFood();
        Integer costFood = lstDelivery.get(position).getCostFood();
        Integer quantumFood = lstDelivery.get(position).getQuantumFood();

        holder.txtNameFood.setText(nameFood);
        holder.txtCostFood.setText(costFood + "");
        holder.txtQuantumFood.setText(quantumFood + "");

        holder.imgDelivery.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lstDelivery.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameFood, txtCostFood, txtQuantumFood;
        ImageView imgDelivery;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtNameFood = (TextView) itemView.findViewById(R.id.txt_name_delivery);
            txtCostFood = (TextView) itemView.findViewById(R.id.txt_cost_delivery);
            txtQuantumFood = (TextView) itemView.findViewById(R.id.txt_quantum_delivery);
            imgDelivery = (ImageView) itemView.findViewById(R.id.img_delivery);

            imgDelivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();

                    imgDelivery.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}
