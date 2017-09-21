package com.example.vuphi.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuphi.loginregister.LoginActivity;
import com.example.vuphi.orderfoodapp.R;
import com.example.vuphi.table.TableActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by vuphi on 4/22/2017.
 */

public class GridHomeAdapter extends RecyclerView.Adapter<GridHomeAdapter.ViewHolder> {

    public List<ItemMenuHome> mItem = new ArrayList<>();
    public Context mContext;
    private FragmentTransaction fragmentTransaction;


    public GridHomeAdapter(List<ItemMenuHome> mItem, Context mContext) {
        this.mItem = mItem;
        this.mContext = mContext;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemHomeView = inflater.inflate(R.layout.item_home_activity, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemHomeView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageView imageItem = holder.imgItem;
        imageItem.setImageResource(mItem.get(position).getImageItem());

        TextView textViewItem = holder.txtItem;
        textViewItem.setText(mItem.get(position).getNameItem());

        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgItem;
        public TextView txtItem;
        View cardView;
        SharedPreferences shPref;

        public ViewHolder(View itemView) {
            super(itemView);
            // ánh xạ
            imgItem = (ImageView) itemView.findViewById(R.id.image_item);
            txtItem = (TextView) itemView.findViewById(R.id.text_view_item_trang_chu);
            cardView = itemView.findViewById(R.id.card_view_home);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = (int) v.getTag();
            switch (position) {
                case 0:

                    break;

                case 1:
                    getContext().startActivity(new Intent(getContext(), TableActivity.class));
                    break;
                case 8:
                    shPref  = getContext().getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shPref.edit();
                    editor.clear();
                    editor.commit();
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                    break;

                default:

            }
        }
    }


}
