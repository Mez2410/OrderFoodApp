package com.example.vuphi.bill.BillOrder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vuphi.orderfoodapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.List;

/**
 * Created by vuphi on 5/26/2017.
 */

public class BillOrderAdapter extends RecyclerView.Adapter<BillOrderAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    public List<ItemBillOrder> lstBillOrder;
    public Context mContext;

    public BillOrderAdapter(List<ItemBillOrder> lstBillOrder, Context mContext) {
        this.lstBillOrder = lstBillOrder;
        this.mContext = mContext;
    }

    private Context getContext () {
        return mContext;
    }

    @Override
    public BillOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_order, parent, false);
        ViewHolder vHdl = new ViewHolder(view);
        return vHdl;
    }

    @Override
    public void onBindViewHolder(BillOrderAdapter.ViewHolder holder, int position) {
        String name_food = lstBillOrder.get(position).getNameFood();
        Integer cost_food = lstBillOrder.get(position).getCostFood();
        Integer quantum_food = lstBillOrder.get(position).getQuantumFood();

        holder.txtNaOrBi.setText(name_food);

        holder.txtCoOrBi.setText(cost_food + "");
        holder.edtQuaOrBi.setText(quantum_food + "");

        holder.btnPlOrBi.setTag(position);
        holder.btnMiOrBi.setTag(position);
        holder.btnSendChef.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lstBillOrder.size();
    }
    
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if(fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(lstBillOrder, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(lstBillOrder, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        lstBillOrder.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNaOrBi, txtCoOrBi;
        EditText edtQuaOrBi;
        Button btnPlOrBi, btnMiOrBi, btnSendChef;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNaOrBi = (TextView) itemView.findViewById(R.id.txt_name_bill_order);
            txtCoOrBi = (TextView) itemView.findViewById(R.id.txt_cost_bill_order);
            edtQuaOrBi = (EditText) itemView.findViewById(R.id.edt_quantum_bill_order);
            btnPlOrBi = (Button) itemView.findViewById(R.id.btn_plus_bill_order);
            btnMiOrBi = (Button) itemView.findViewById(R.id.btn_minus_bill_order);
            btnSendChef = (Button) itemView.findViewById(R.id.btn_send_item_food);

            btnMiOrBi.setOnClickListener(this);
            btnPlOrBi.setOnClickListener(this);
            btnSendChef.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {
            SharedPreferences sharedPref = getContext().getSharedPreferences("BillCode", Context.MODE_PRIVATE);
            Integer billCode = sharedPref.getInt("bill_code", 0);

            DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference()
                    .child("DataChef").child(String.valueOf(billCode)).child("FoodOrder").push();

            int position = (int) v.getTag();

            switch (v.getId()) {
                case R.id.btn_minus_bill_order:

                    int k = Integer.valueOf(edtQuaOrBi.getText().toString());
                    if (k > 1) {
                        k--;
                    }
                    edtQuaOrBi.setText(k + "");

                    break;

                case R.id.btn_plus_bill_order:

                    int i = Integer.valueOf(edtQuaOrBi.getText().toString());
                    i++;
                    edtQuaOrBi.setText(i + "");

                    break;

                case R.id.btn_send_item_food:

                    String nameFood = lstBillOrder.get(position).getNameFood();
                    Integer costFood = lstBillOrder.get(position).getCostFood();
                    Integer quantumFood = lstBillOrder.get(position).getQuantumFood();
                    
                    dataRef.child("NameFood").setValue(nameFood);
                    dataRef.child("QuantumFood").setValue(quantumFood);
                    dataRef.child("CostFood").setValue(costFood);

                    btnSendChef.setBackgroundColor(Color.WHITE);
                    break;
            }
        }
    }

}
