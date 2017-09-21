package com.example.vuphi.table;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.vuphi.orderfoodapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    Toolbar toolbarTable;
    List<TableItem> arrTab;
    RecyclerView rvTables;
    GridTableAdapter grdTabAdapter;

    private DatabaseReference tabDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        toolbarTable = (Toolbar) findViewById(R.id.toolbar_table);
        rvTables = (RecyclerView) findViewById(R.id.rvTable);

        tabDataRef = FirebaseDatabase.getInstance().getReference().child("DataTable");

        // lấy ra Toolbar trong Activity
        setSupportActionBar(toolbarTable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Table");

        arrTab = new ArrayList<>();

        //Add dữ liệu vào firebase
//        for (int i = 1; i <= 30; i++) {
//            TableItem itemTable = new TableItem("http://sv1.upsieutoc.com/2017/05/04/table_icon.png",
//                    i, false, false);
//            tabDataRef.child(String.valueOf(i)).SetValueToActivity(itemTable);
//        }

        UpdateListTable();

        // Create adapter passing in the sample user data
        grdTabAdapter = new GridTableAdapter(arrTab, getApplicationContext());

        // Attach the adapter to the recycler view to populate items
        rvTables.setAdapter(grdTabAdapter);

        // Set layout manager to position the items
        rvTables.setHasFixedSize(true);
        rvTables.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
    }

    private void UpdateListTable() {

        tabDataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TableItem tabItem = dataSnapshot.getValue(TableItem.class);
                arrTab.add(new TableItem(tabItem.getImageTable(), tabItem.getNumberTable(),
                        tabItem.getSttTab(), tabItem.getSttBtn()));
                grdTabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int index = getItemIndexTab(dataSnapshot.getValue(TableItem.class));

                arrTab.set(index, dataSnapshot.getValue(TableItem.class));
                grdTabAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                TableItem tabItem = dataSnapshot.getValue(TableItem.class);
                int index = getItemIndexTab(tabItem);
                arrTab.set(index, tabItem);
                grdTabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndexTab(TableItem tableItem) {

        int index = -1;
        for (int i = 0; i < arrTab.size(); i++) {
            if (arrTab.get(i).getNumberTable() == tableItem.getNumberTable()) {
                index = i;
                break;
            }
        }
        return index;
    }
}
