package com.example.vuphi.table;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.vuphi.networkvolley.MySingleton;
import com.example.vuphi.networkvolley.UrlData;
import com.example.vuphi.order.OrderActivity;
import com.example.vuphi.orderfoodapp.R;
import com.example.vuphi.storingstatuslogin.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vuphi on 3/26/2017.
 */

public class GridTableAdapter extends RecyclerView.Adapter<GridTableAdapter.ViewHolder> {

    public List<TableItem> lstTab = new ArrayList<>();
    public Context mContext;
    private DatabaseReference dataRef;
    UrlData urlData = new UrlData();

    public GridTableAdapter(List<TableItem> lstTab, Context mContext) {
        this.lstTab = lstTab;
        this.mContext = mContext;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public GridTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View tableView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);

        ViewHolder viewHolder = new ViewHolder(tableView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GridTableAdapter.ViewHolder holder, int position) {

        holder.txtNumTable.setText("Bàn " + lstTab.get(position).getNumberTable());
        Picasso.with(getContext()).load(lstTab.get(position).getImageTable()).into(holder.imgTable);

        if (lstTab.get(position).getSttBtn()) {
            holder.btnOrder.setVisibility(View.VISIBLE);
            holder.btnPay.setVisibility(View.VISIBLE);
        } else {
            holder.btnOrder.setVisibility(View.INVISIBLE);
            holder.btnPay.setVisibility(View.INVISIBLE);
        }

        holder.imgTable.setTag(position);
        holder.btnOrder.setTag(position);
        holder.btnPay.setTag(position);

    }

    @Override
    public int getItemCount() {
        return lstTab.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtNumTable;
        public Button btnOrder, btnPay;
        public ImageView imgTable;

        public ViewHolder (View itemView) {
            super(itemView);
            txtNumTable = (TextView) itemView.findViewById(R.id.txtNumTable);
            btnOrder = (Button) itemView.findViewById(R.id.btnOrder);
            btnPay = (Button) itemView.findViewById(R.id.btnPay);
            imgTable = (ImageView) itemView.findViewById(R.id.imgTable);

            btnOrder.setOnClickListener(this);
            btnPay.setOnClickListener(this);
            imgTable.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            dataRef = FirebaseDatabase.getInstance().getReference().child("DataTable");

            final int position = (int) v.getTag();

            switch (v.getId()) {

                case R.id.imgTable:

                    lstTab.get(position).setSttBtn(true);

                    btnOrder.setVisibility(View.VISIBLE);
                    btnPay.setVisibility(View.VISIBLE);
                    break;

                case R.id.btnOrder:
                    // lấy ra số bàn (số bàn = key trong firebase)
                    // => khi click vào bàn nào thì sẽ truy cập tới dữ liệu của key đó
                    final String numTab = String.valueOf(lstTab.get(position).getNumberTable());

                    dataRef.child(numTab).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            TableItem tabItem = dataSnapshot.getValue(TableItem.class);

                            if (tabItem.getSttTab() == false) {

                                // ẩn các button trước khi chuyển màn hình => nhìn sẽ không mất thẩm mĩ
                                btnOrder.setVisibility(View.INVISIBLE);
                                btnPay.setVisibility(View.INVISIBLE);
                                ParseJSON(numTab);

                            } else {

                                btnOrder.setVisibility(View.INVISIBLE);
                                btnPay.setVisibility(View.INVISIBLE);

                                Toast.makeText(getContext(), "Đã Order, Bạn có muốn update!", Toast.LENGTH_LONG).show();

                                dataRef.child(numTab).child("sttTab").setValue(false);
                                dataRef.child(numTab).child("imageTable").setValue("http://sv1.upsieutoc.com/2017/05/04/table_icon.png");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    // lưu lại số bàn, để send qua nhà bếp
                    SharedPreferences sharePref = getContext().getSharedPreferences("NumTab", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharePref.edit();
                    editor.clear();
                    editor.putString("NumberTable", numTab);
                    editor.commit();

                    break;

                case R.id.btnPay:

                    break;
            }
        }
    }

    private void ParseJSON(final String numTab) {

        // Lấy ra thông tin của nhân viên (ID, tên)
        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> dataUser = sessionManager.getUserDetails();
        final String employee_code = dataUser.get(SessionManager.KEY_ID);
        final String employee_name = dataUser.get(SessionManager.KEY_NAME);

        // Lấy ra ngày giờ hiện tại
//        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
//        final String time = df.format(Calendar.getInstance().getTime());

//        Log.d("TIME", time);

        StringRequest strRequest = new StringRequest(Request.Method.POST, urlData.URL_BILL_ORDER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("DATA", response);
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            String error_code = jsonObject.getString("StatusCode");

                            if (error_code.equals("error")) {
//                                alertDialogNotice();
                                Toast.makeText(getContext(), "Order không thành công", Toast.LENGTH_LONG).show();
                            } else if (error_code.equals("success")) {
                                dataRef.child(numTab).child("sttTab").setValue(true);
                                dataRef.child(numTab).child("imageTable").setValue("http://sv1.upsieutoc.com/2017/05/04/table_icon_false.png");

                                Integer bill_code = jsonObject.getInt("BillCode");

                                SharedPreferences sharedPref = getContext().getSharedPreferences("BillCode", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.clear();
                                editor.putInt("bill_code", bill_code);
                                editor.commit();

                                passBillCode(bill_code);

                            }

                            // Lấy time từ database Mysql lưu nó vào SharePreferences
                            String timeOrder = jsonObject.getString("TimeOrder");
                            SharedPreferences sharePref = getContext().getSharedPreferences("Time", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharePref.edit();
                            editor.clear();
                            editor.putString("TimeOrder", timeOrder);
                            editor.commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ErrorNet", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("StatusCode", "success");
                params.put("NumberTable", numTab);
                params.put("EmployeeCode", employee_code);
                params.put("EmployeeName", employee_name);
//                params.put("TimeOrderTab", time);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(strRequest);
    }

    private void passBillCode(int bill_code) {
        Intent intent = new Intent(getContext(), OrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("BillCode", bill_code);
        intent.putExtra("BillOrder", bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

    private void alertDialogNotice() {
        AlertDialog.Builder alertDl = new AlertDialog.Builder(getContext());
        alertDl.setTitle("Error! :((");
        alertDl.setMessage("Error");
        alertDl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AppCompatActivity)getContext()).finish();
            }
        });
        alertDl.create().show();
    }

}
