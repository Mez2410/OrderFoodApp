package com.example.vuphi.kitchen.InformationOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vuphi.kitchen.DeliveryFood.DeliveryFoodActivity;
import com.example.vuphi.orderfoodapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InfoOrderActivity extends AppCompatActivity {

    Toolbar toolbarTable;
    ListView lstInfo;
    List<ItemInfoOrder> arrInfo;
    InfoOrderAdapter infoAdapter;
    private DatabaseReference tabDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_order);

        toolbarTable = (Toolbar) findViewById(R.id.toolbar_table);
        lstInfo = (ListView) findViewById(R.id.lst_info_order);

        // lấy ra Toolbar trong Activity
        setSupportActionBar(toolbarTable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Information Order");

        arrInfo = new ArrayList<>();

        tabDataRef = FirebaseDatabase.getInstance().getReference().child("DataChef");

        UpdateListInfo();

        infoAdapter = new InfoOrderAdapter(getApplicationContext(), arrInfo);
        lstInfo.setAdapter(infoAdapter);

        lstInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(InfoOrderActivity.this, DeliveryFoodActivity.class);
                Bundle bundle = new Bundle();

                bundle.putInt("BillCode", arrInfo.get(position).getBillCode());
                bundle.putString("EmployeeCode", arrInfo.get(position).getEmployeeCode());
                bundle.putString("EmployeeName", arrInfo.get(position).getEmployeeName());
                bundle.putString("NumTab", arrInfo.get(position).getNumberTable());
                bundle.putString("TimeOrder", arrInfo.get(position).getTimeOrder());
                intent.putExtra("MyData", bundle);
                startActivity(intent);
            }
        });
    }

    private void UpdateListInfo() {
        tabDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrInfo.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ItemInfoOrder itemInfo = postSnapshot.getValue(ItemInfoOrder.class);
                    arrInfo.add(new ItemInfoOrder(itemInfo.getBillCode(), itemInfo.getEmployeeCode(), itemInfo.getEmployeeName(), itemInfo.getNumberTable(), itemInfo.getTimeOrder()));
                    infoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
