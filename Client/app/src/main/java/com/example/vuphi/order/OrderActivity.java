package com.example.vuphi.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vuphi.bill.BillOrder.BillOrderActivity;
import com.example.vuphi.networkvolley.MySingleton;
import com.example.vuphi.networkvolley.UrlData;
import com.example.vuphi.order.OrderDessert.FragmentDessert;
import com.example.vuphi.order.OrderDrink.FragmentDrink;
import com.example.vuphi.order.OrderMainDish.FragmentMainDish;
import com.example.vuphi.orderfoodapp.R;
import com.example.vuphi.storingstatuslogin.SessionManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbarOrder;
    Button btnCancel, btnOK;
    int billCode;
    UrlData urlData = new UrlData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnOK = (Button) findViewById(R.id.btnOK);

        toolbarOrder = (Toolbar) findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbarOrder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tìm kiếm món ăn");

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.AddFragment(new FragmentMainDish(), "FOODS");
        viewPageAdapter.AddFragment(new FragmentDrink(), "DRINKS");
        viewPageAdapter.AddFragment(new FragmentDessert(), "DESSERTS");

        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);

        Intent getBillCode = getIntent();
        Bundle bundle = getBillCode.getBundleExtra("BillOrder");
        billCode = bundle.getInt("BillCode");

        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference()
                .child("DataChef").child(String.valueOf(billCode));

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển màn hình đồng thời truyền value bill code qua BillOrderActivity
                GetData(dataRef);
                LoadData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });
    }

    // hàm lấy ra các value
    private void GetData(DatabaseReference dataRef) {

        // Lấy ra số bàn đã lưu ở bên GridTableAdapter
        SharedPreferences sharePref = getSharedPreferences("NumTab", MODE_PRIVATE);
        String numTab = sharePref.getString("NumberTable", "");

        // Lấy ra mã NV và tên Nv
        SessionManager sesMan = new SessionManager(getApplicationContext());
        HashMap<String, String> dataUser = sesMan.getUserDetails();

        // Lấy ra thời gian Order Table
        SharedPreferences shaPrefTime = getSharedPreferences("Time", MODE_PRIVATE);
        String timeOrder = shaPrefTime.getString("TimeOrder", "");

        // Up dữ liệu lên fire base database
        dataRef.child("BillCode").setValue(billCode);
        dataRef.child("NumberTable").setValue(numTab);
        dataRef.child("EmployeeCode").setValue(dataUser.get(SessionManager.KEY_ID));
        dataRef.child("EmployeeName").setValue(dataUser.get(SessionManager.KEY_NAME));
        dataRef.child("TimeOrder").setValue(timeOrder);
    }

    // hàm xóa dữ liệu các món vừa chọn
    private void DeleteData() {
        StringRequest strRequest = new StringRequest(Request.Method.POST, urlData.URL_DELETE_ROWS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsOb = new JSONObject(response);
                    String stt = jsOb.getString("status");
                    if (stt.equals("success")) {
                        displayAlertDialog();
                    } else {
                        Toast.makeText(getApplicationContext(), "Không tìm thấy dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CodeBill", String.valueOf(billCode));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(strRequest);
    }

    // hàm load dữ liệu các món vừa chọn
    private void LoadData() {
        StringRequest strRequest = new StringRequest(Request.Method.POST, urlData.URL_ORDER_DETAIL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsOb = new JSONObject(response);
                    String stt = jsOb.getString("status");
                    if (stt.equals("success")) {
                        Intent iBillOrder = new Intent(OrderActivity.this, BillOrderActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putInt("bill_code", billCode);

                        iBillOrder.putExtra("bill_order", bundle);
                        iBillOrder.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(iBillOrder);
                    } else {
                        Toast.makeText(getApplicationContext(), "Bạn chưa chọn món!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CodeBill", String.valueOf(billCode));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(strRequest);
    }

    // hàm hiển thị dialog
    private void displayAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
        builder.setTitle("Notification");
        builder.setMessage("Hủy món thành công!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
