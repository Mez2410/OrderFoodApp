package com.example.vuphi.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vuphi.loginregister.LoginActivity;
import com.example.vuphi.orderfoodapp.R;
import com.example.vuphi.storingstatuslogin.SessionManager;
import com.example.vuphi.table.TableActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView rvItem;

    ArrayList<ItemMenuHome> arrItem;
    TextView txtID, txtName;
    SessionManager sessionManager;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        nvDrawer = (NavigationView) findViewById(R.id.navigation_view);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        setSupportActionBar(toolbar);

        rvItem = (RecyclerView) findViewById(R.id.recycler_view_item);

        collapsingToolbarLayout.setTitle("Mezcal Restaurant");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,
                R.color.colorPrimaryDark));

        GridMenuHome();

        nvDrawer.setItemTextColor(null);
        nvDrawer.setItemIconTintList(null);

        handler = new Handler();
        // lấy ra tên của các item trong mảng

        View header = nvDrawer.getHeaderView(0);
        txtID = (TextView) header.findViewById(R.id.txtMaNV);
        txtName = (TextView) header.findViewById(R.id.txtNameNav);

//        selectDrawerItem();
        setupDrawerContent(nvDrawer);

        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mDrawer.addDrawerListener(drawerToggle);

        sessionManager.checkLogin();

        // lấy ra thông tin của NV từ SessionManager
        HashMap<String, String> dataUser = sessionManager.getUserDetails();
        txtID.setText(dataUser.get(SessionManager.KEY_ID));
        txtName.setText(dataUser.get(SessionManager.KEY_NAME));
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);

                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:

                break;

            case R.id.nav_menu:

                break;

            case R.id.nav_order:
                startActivity(new Intent(HomeActivity.this, TableActivity.class));
                finish();
                break;
            case R.id.nav_logout:

                // Xóa dữ liệu đăng nhập của người dùng trước khi nhảy tới màn hình Login
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawer.openDrawer(GravityCompat.START);
//                return true;
//        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        LogoutApp();
    }

    private void GridMenuHome() {
        arrItem = new ArrayList<>();
        arrItem.add(new ItemMenuHome(R.drawable.image_menu, "Menu"));
        arrItem.add(new ItemMenuHome(R.drawable.image_order, "Order"));
        arrItem.add(new ItemMenuHome(R.drawable.image_reservation, "Reservation"));
        arrItem.add(new ItemMenuHome(R.drawable.image_pay_bill, "Bill Pay"));
        arrItem.add(new ItemMenuHome(R.drawable.image_news, "News"));
        arrItem.add(new ItemMenuHome(R.drawable.image_history, "History"));
        arrItem.add(new ItemMenuHome(R.drawable.image_info, "Information"));
        arrItem.add(new ItemMenuHome(R.drawable.image_setting, "Setting"));
        arrItem.add(new ItemMenuHome(R.drawable.image_logout, "Logout"));

        GridHomeAdapter adapter = new GridHomeAdapter(arrItem, HomeActivity.this);

        rvItem.setAdapter(adapter);
        // Set layout cho recycler view
        rvItem.setLayoutManager(new GridLayoutManager(HomeActivity.this, 3));
    }

    // hàm thực hiện hỏi người dùng có muốn thoát ứng dụng hay không?
    private void LogoutApp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Bạn có muốn thoát ứng dụng?");
        alertDialogBuilder.setMessage("Nhấn Yes để thoát!").setCancelable(false)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        finish();
                        System.exit(0);
                    }
                })

                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
//        super.onBackPressed();
    }
}
