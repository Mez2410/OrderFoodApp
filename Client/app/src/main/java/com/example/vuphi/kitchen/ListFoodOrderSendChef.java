package com.example.vuphi.kitchen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vuphi.orderfoodapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListFoodOrderSendChef extends AppCompatActivity {

    Toolbar toolbarTable;
    ListView lstFoodChef;
    TextView txtSendChef;

    DatabaseReference tabDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food_order_send_chef);

        toolbarTable = (Toolbar) findViewById(R.id.toolbar_table);
//        lstFoodChef = (ListView) findViewById(R.id.lst_food_send_chef);
        txtSendChef = (TextView) findViewById(R.id.txt_send_chef);
        tabDataRef = FirebaseDatabase.getInstance().getReference().child("DataChef");

        getData();
    }

    private void getData() {
        tabDataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
//                    String employee_code = (String) childSnapShot.child("EmployeeCode").getValue();
                    txtSendChef.setText(childSnapShot.toString());

                }
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
}
