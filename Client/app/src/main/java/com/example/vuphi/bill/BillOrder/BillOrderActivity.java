package com.example.vuphi.bill.BillOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vuphi.networkvolley.MySingleton;
import com.example.vuphi.networkvolley.UrlData;
import com.example.vuphi.orderfoodapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillOrderActivity extends AppCompatActivity {

    List<ItemBillOrder> arrBilOr;
    RecyclerView rvBillOr;
    RecyclerView.LayoutManager layoutMan;
    BillOrderAdapter adapterBiOr;
    UrlData urlData = new UrlData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_order);

        rvBillOr = (RecyclerView) findViewById(R.id.rv_bill_order);
        rvBillOr.setHasFixedSize(true);

        layoutMan = new LinearLayoutManager(getApplicationContext());
        rvBillOr.setLayoutManager(layoutMan);

        arrBilOr = new ArrayList<>();

        // lấy value bill code từ OrderActivity truyền qua
        Intent getBillCode = getIntent();
        Bundle bundle = getBillCode.getBundleExtra("bill_order");
        int billCode = bundle.getInt("bill_code");

        getData(billCode);

        adapterBiOr = new BillOrderAdapter(arrBilOr, BillOrderActivity.this);
        rvBillOr.setAdapter(adapterBiOr);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapterBiOr);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvBillOr);
    }

    private void getData(final int bill_code) {
        StringRequest strRequest = new StringRequest(Request.Method.POST, urlData.URL_ORDER_DETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsOb = new JSONObject(response);
                            String stt = jsOb.getString("status");
                            JSONArray jsArr = jsOb.getJSONArray("data");

                            for (int i = 0; i <= response.length(); i++) {

                                ItemBillOrder itemBO = new ItemBillOrder();

                                JSONObject jsItem = jsArr.getJSONObject(i);

                                itemBO.setNameFood(jsItem.getString("NameFood"));
                                itemBO.setQuantumFood(jsItem.getInt("QuantumFood"));
                                itemBO.setCostFood(jsItem.getInt("CostFood"));

                                arrBilOr.add(itemBO);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CodeBill", String.valueOf(bill_code));
                return params;

            }
        };
        MySingleton.getInstance(BillOrderActivity.this).addToRequestQueue(strRequest);
    }

}
