package com.example.vuphi.order.OrderDrink;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vuphi.order.OrderMainDish.CustomDialogMainDish;
import com.example.vuphi.orderfoodapp.R;

/**
 * Created by vuphi on 5/22/2017.
 */

public class CustomDialogDrink extends DialogFragment {
    private ImageView imgDlDrink;
    private TextView txtDlNameDr, txtDlCostDr;
    private EditText edtDlQuantumDr;
    private Button btnDlMinusDr, btnDlPlusDr, btnDlAddDr, btnDlCancelDr;
    String name_drink, image_drink;
    Integer cost_drink;

    public CustomDialogDrink() {
        // Empty constructor required for DialogFragment
    }

    public static CustomDialogDrink newInstance(String data) {

        CustomDialogDrink customDlDr = new CustomDialogDrink();

        Bundle args = new Bundle();
        args.putString("Data", data);
        customDlDr.setArguments(args);
        return customDlDr;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_dialog_drink);
        dialog.show();

        imgDlDrink = (ImageView) dialog.findViewById(R.id.img_dialog_drink);
        txtDlNameDr = (TextView) dialog.findViewById(R.id.txt_dialog_name_drink);
        txtDlCostDr = (TextView) dialog.findViewById(R.id.txt_dialog_cost_drink);
        btnDlMinusDr = (Button) dialog.findViewById(R.id.btn_minus_drink_dialog);
        btnDlPlusDr = (Button) dialog.findViewById(R.id.btn_plus_drink_dialog);
        btnDlAddDr = (Button) dialog.findViewById(R.id.btn_add_drink_dialog);
        btnDlCancelDr = (Button) dialog.findViewById(R.id.btn_cancel_drink_dl);
        edtDlQuantumDr = (EditText) dialog.findViewById(R.id.edt_quantum_drink_dialog);

        // Lấy dữ liệu từ Adapter truyền qua
        name_drink = this.getArguments().getString("name_drink");
        cost_drink = this.getArguments().getInt("cost_drink");
        image_drink = this.getArguments().getString("image_drink");

        txtDlNameDr.setText(name_drink);
        txtDlCostDr.setText(cost_drink + "");
        Glide.with(getActivity()).load(image_drink).into(imgDlDrink);

        AddEvent();

        return dialog;
    }

    private void AddEvent() {
        btnDlMinusDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.valueOf(edtDlQuantumDr.getText().toString());
                if (k > 1) {
                    k--;
                }
                edtDlQuantumDr.setText(k + "");
            }
        });

        btnDlPlusDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(edtDlQuantumDr.getText().toString());
                i++;
                edtDlQuantumDr.setText(i + "");
            }
        });

        btnDlAddDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getBillCode = getActivity().getIntent();
                Bundle bundle = getBillCode.getBundleExtra("BillOrder");
                int billCode = bundle.getInt("BillCode");

                String quantum = edtDlQuantumDr.getText().toString();
                String cost = txtDlCostDr.getText().toString();

                Integer cost_food = Integer.parseInt(cost);

                CustomDialogMainDish customMain = new CustomDialogMainDish();
                customMain.ParseJSON(name_drink, quantum, cost_food, billCode);

                dismiss();
            }
        });

        btnDlCancelDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
