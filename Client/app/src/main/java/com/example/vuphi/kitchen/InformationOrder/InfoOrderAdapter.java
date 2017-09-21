package com.example.vuphi.kitchen.InformationOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vuphi.orderfoodapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vuphi on 7/13/2017.
 */

public class InfoOrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<ItemInfoOrder> lstInfo = new ArrayList<>();
    private LayoutInflater inflater;

    public InfoOrderAdapter(Context mContext, List<ItemInfoOrder> lstInfo) {
        this.mContext = mContext;
        this.lstInfo = lstInfo;
        inflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return lstInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return lstInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_view_cook_chef, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtBillCode.setText(lstInfo.get(position).getBillCode() + "");
        viewHolder.txtNumTab.setText(lstInfo.get(position).getNumberTable());

        return convertView;
    }

    public class ViewHolder {
        TextView txtNumTab, txtBillCode;

        public ViewHolder(View view) {
            txtBillCode = (TextView) view.findViewById(R.id.txt_bill_code_info_order);
            txtNumTab = (TextView) view.findViewById(R.id.txt_number_tab_info_order);
        }
    }
}
