package com.example.vuphi.order.OrderMainDish;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.vuphi.networkvolley.MySingleton;
import com.example.vuphi.networkvolley.UrlData;
import com.example.vuphi.orderfoodapp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vuphi on 5/10/2017.
 */

public class CustomDialogMainDish extends DialogFragment {
    public ImageView imgDlFood;
    public TextView txtDlName, txtDlCost, txtDlQuantum;
    public Button btnDlMinus, btnDlPlus, btnDlAdd, btnDlCancel;
    String name, image;
    Integer cost;
//    DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
    UrlData urlData = new UrlData();

    public CustomDialogMainDish() {
        // Empty constructor required for DialogFragment
    }

    public static CustomDialogMainDish newInstance(String data) {

        CustomDialogMainDish customDl = new CustomDialogMainDish();

        Bundle args = new Bundle();
        args.putString("Data", data);
        customDl.setArguments(args);
        return customDl;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // set background cho dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_dialog_main_dish);
        dialog.show();

        imgDlFood = (ImageView) dialog.findViewById(R.id.img_dialog_food);
        txtDlName = (TextView) dialog.findViewById(R.id.txt_dialog_name_food);
        txtDlCost = (TextView) dialog.findViewById(R.id.txt_dialog_cost_food);
        btnDlMinus = (Button) dialog.findViewById(R.id.btn_minus_food_dialog);
        btnDlPlus = (Button) dialog.findViewById(R.id.btn_plus_food_dialog);
        btnDlAdd = (Button) dialog.findViewById(R.id.btn_add_food_dialog);
        btnDlCancel = (Button) dialog.findViewById(R.id.btn_cancel_food);
        txtDlQuantum = (TextView) dialog.findViewById(R.id.txt_quantum_food_dialog);

        // Lấy dữ liệu từ Adapter truyền qua
        name = this.getArguments().getString("name_food");
        cost = this.getArguments().getInt("cost_food");
        image = this.getArguments().getString("image_food");

        txtDlName.setText(name);
        txtDlCost.setText(cost + "");
        Glide.with(getActivity()).load(image).into(imgDlFood);

        AddEvent();

        return dialog;
    }

    private void AddEvent() {
        btnDlMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.valueOf(txtDlQuantum.getText().toString());
                if (k > 1) {
                    k--;
                }
                txtDlQuantum.setText(k + "");
            }
        });

        btnDlPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(txtDlQuantum.getText().toString());
                i++;
                txtDlQuantum.setText(i + "");
            }
        });

        btnDlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                 Lấy dữ liệu (bill_code) từ màn hình TableActivity truyền qua
                Intent getBillCode = getActivity().getIntent();
                Bundle bundle = getBillCode.getBundleExtra("BillOrder");
                int billCode = bundle.getInt("BillCode");

                String quantumF = txtDlQuantum.getText().toString();
                String costF = txtDlCost.getText().toString().trim();

                Integer gia = Integer.parseInt(costF);

                ParseJSON(name, quantumF, gia, billCode);

                dismiss();
            }
        });

        btnDlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void ParseJSON(final String name_food, final String qt_food, final Integer cost_food, final int code_bill) {

        StringRequest strRequest = new StringRequest(Request.Method.POST, urlData.URL_INSERT_FOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("DATA_BILL_ORDER", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NameFood", name_food);
                params.put("QuantumFood", qt_food);
                params.put("CostFood", String.valueOf(cost_food));
//                params.put("TimeOrder", time_order);
                params.put("CodeBill", String.valueOf(code_bill));
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(strRequest);
    }

}
