package com.example.vuphi.order.OrderDessert;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vuphi.order.OrderMainDish.CustomDialogMainDish;
import com.example.vuphi.orderfoodapp.R;

/**
 * Created by vuphi on 5/22/2017.
 */

public class CustomDialogDessert extends DialogFragment {
    public ImageView imgDlDes;
    public TextView txtDlNameDes, txtDlCostDes, txtDlQuantumDes;
    public Button btnDlMinusDes, btnDlPlusDes, btnDlAddDes, btnDlCancelDes;
    String name, image;
    Integer cost;

    public CustomDialogDessert() {
        // Empty constructor required for DialogFragment
    }

    public static CustomDialogDessert newInstance(String data) {

        CustomDialogDessert customDl = new CustomDialogDessert();

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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_dialog_dessert);

        dialog.show();

        imgDlDes = (ImageView) dialog.findViewById(R.id.img_dialog_des);
        txtDlNameDes = (TextView) dialog.findViewById(R.id.txt_dialog_name_des);
        txtDlCostDes = (TextView) dialog.findViewById(R.id.txt_dialog_cost_des);
        btnDlMinusDes = (Button) dialog.findViewById(R.id.btn_minus_des_dialog);
        btnDlPlusDes = (Button) dialog.findViewById(R.id.btn_plus_des_dialog);
        btnDlAddDes = (Button) dialog.findViewById(R.id.btn_add_des_dialog);
        btnDlCancelDes = (Button) dialog.findViewById(R.id.btn_cancel_dessert_dl);
        txtDlQuantumDes = (TextView) dialog.findViewById(R.id.txt_quantum_des_dialog);

        // Lấy dữ liệu từ Adapter truyền qua
        name = this.getArguments().getString("name_des");
        cost = this.getArguments().getInt("cost_des");
        image = this.getArguments().getString("image_des");

        txtDlNameDes.setText(name);
        txtDlCostDes.setText(cost + "");
        Glide.with(getActivity()).load(image).into(imgDlDes);

        AddEvent();

        return dialog;
    }

    private void AddEvent() {
        btnDlMinusDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.valueOf(txtDlQuantumDes.getText().toString());
                if (k > 1) {
                    k--;
                }
                txtDlQuantumDes.setText(k + "");
            }
        });

        btnDlPlusDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(txtDlQuantumDes.getText().toString());
                i++;
                txtDlQuantumDes.setText(i + "");
            }
        });

        btnDlAddDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getBillCode = getActivity().getIntent();
                Bundle bundle = getBillCode.getBundleExtra("BillOrder");
                int billCode = bundle.getInt("BillCode");

                String quantum = txtDlQuantumDes.getText().toString();
                String cost = txtDlCostDes.getText().toString();

                Integer gia = Integer.parseInt(cost);

                CustomDialogMainDish customMain = new CustomDialogMainDish();
                customMain.ParseJSON(name, quantum, gia, billCode);

                dismiss();
            }
        });

        btnDlCancelDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
