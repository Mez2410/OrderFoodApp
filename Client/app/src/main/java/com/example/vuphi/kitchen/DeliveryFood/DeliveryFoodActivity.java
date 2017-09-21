package com.example.vuphi.kitchen.DeliveryFood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.vuphi.orderfoodapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeliveryFoodActivity extends AppCompatActivity {

    Toolbar toolbarTable;
    List<FoodOrder> arrDelivery;
    RecyclerView rvDelivery;
    TextView txtCodeBill, txtNumTab, txtEmployName, txtEmployCode, txtTimeOrder;
    RecyclerView.LayoutManager layoutMan;
    DeliveryAdapter delAdapter;
    private DatabaseReference delDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_food);


        rvDelivery = (RecyclerView) findViewById(R.id.rv_delivery);
        rvDelivery.setHasFixedSize(true);

        txtCodeBill = (TextView) findViewById(R.id.txt_bill_code_del);
        txtNumTab = (TextView) findViewById(R.id.txt_num_tab_del);
        txtEmployName = (TextView) findViewById(R.id.txt_employ_name_del);
        txtEmployCode = (TextView) findViewById(R.id.txt_employ_code_del);
        txtTimeOrder = (TextView) findViewById(R.id.txt_time_order_del);

        layoutMan = new LinearLayoutManager(getApplicationContext());
        rvDelivery.setLayoutManager(layoutMan);

        delDataRef = FirebaseDatabase.getInstance().getReference().child("DataChef");

        arrDelivery = new ArrayList<>();

        Intent iReceive = getIntent();
        Bundle getBundle = iReceive.getBundleExtra("MyData");
        int bill_code = getBundle.getInt("BillCode");
        String employ_code = getBundle.getString("EmployeeCode");
        String employ_name = getBundle.getString("EmployeeName");
        String num_tab = getBundle.getString("NumTab");
        String time_order = getBundle.getString("TimeOrder");

        ReceiveData(bill_code, num_tab, employ_name, employ_code, time_order);

        getDataDel(bill_code);

        delAdapter = new DeliveryAdapter(arrDelivery, DeliveryFoodActivity.this);
        rvDelivery.setAdapter(delAdapter);
    }

    private void getDataDel(Integer bill_code) {
        delDataRef.child(String.valueOf(bill_code)).child("FoodOrder")
             .addChildEventListener(new ChildEventListener() {
                 @Override
                 public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                     // tên class và tên node gốc phải đặt giống nhau
                     FoodOrder itemDel = dataSnapshot.getValue(FoodOrder.class);
                     arrDelivery.add(new FoodOrder(itemDel.getNameFood(), itemDel.getCostFood(), itemDel.getQuantumFood()));
                     delAdapter.notifyDataSetChanged();
                 }

                 @Override
                 public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                 }

                 @Override
                 public void onChildRemoved(DataSnapshot dataSnapshot) {

                 }

                 @Override
                 public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });
    }

    private void ReceiveData(Integer bill_code, String num_tab, String employ_name, String employ_code, String time_order) {
        txtCodeBill.setText(bill_code + "");
        txtNumTab.setText(num_tab);
        txtEmployName.setText(employ_name);
        txtEmployCode.setText(employ_code);
        txtTimeOrder.setText(time_order);
    }
}
