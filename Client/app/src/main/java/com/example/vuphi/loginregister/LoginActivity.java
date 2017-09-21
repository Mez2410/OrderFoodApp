package com.example.vuphi.loginregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vuphi.kitchen.KitchenActivity;
import com.example.vuphi.main.HomeActivity;
import com.example.vuphi.networkvolley.MySingleton;
import com.example.vuphi.networkvolley.UrlData;
import com.example.vuphi.orderfoodapp.R;
import com.example.vuphi.storingstatuslogin.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtMaNV, edtPassword;
    Button btnLogin;
    TextView txtSign;
    String maNV, password;
    UrlData urlData = new UrlData();
    AlertDialog.Builder builder;
    SharedPreferences sharedPreferences;
    SessionManager sessionManager;
//    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // chuyển tới trang chủ mà không cần đăng nhập (ghi nhớ trạng thái đăng nhập)
        if ((sharedPreferences.getString("position", "")).equals("Employee")) {
            if (sharedPreferences.contains("id") && sharedPreferences.contains("password")) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            if (sharedPreferences.contains("id") && sharedPreferences.contains("password")) {
                Intent intent = new Intent(LoginActivity.this, KitchenActivity.class);
                startActivity(intent);
                finish();
            }
        }

        builder = new AlertDialog.Builder(LoginActivity.this);

        addControls();
        addEvents();

        edtMaNV.setHint("Mã Nhân viên");
        edtPassword.setHint("Password");

    }

    // hàm thực hiện hỏi người dùng có muốn thoát ứng dụng hay không?
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Bạn có muốn thoát ứng dụng?");
        alertDialogBuilder.setMessage("Nhấn Yes để thoát!").setCancelable(false)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
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

    public void addEvents() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maNV = edtMaNV.getText().toString();
                password = edtPassword.getText().toString();

                if (maNV.equals("") || password.equals("")) {
                    builder.setTitle("Login Failed! :((");
                    displayAlert("Bạn cần điền đầy đủ thông tin đăng nhập!");
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlData.URL_LOGIN, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("login_failed")) {
                                    String mess = jsonObject.getString("message");
                                    builder.setTitle("Login Failed! :((");
                                    displayAlert(mess);
                                }
                                else {
                                    String position = jsonObject.getString("position");
                                    // lưu lại id và password
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.putString("id", jsonObject.getString("employee_code"));
                                    editor.putString("password", password);
                                    editor.putString("position", position);
                                    editor.commit();

                                    sessionManager.createLoginSession(jsonObject.getString("employee_code"), jsonObject.getString("name"), position);

                                    if(position.equals("Employee")) {
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intentChef = new Intent(LoginActivity.this, KitchenActivity.class);
                                        startActivity(intentChef);
                                        finish();
                                    }

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
                        // truyền dữ liệu lên database để server xử lý xem id và password có đúng hay ko?
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("EmployeeCode", maNV);
                            params.put("Password", password);
                            return params;
                        }
                    };
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                    // Add the request to the RequestQueue.
//                    mRequestQueue.add(stringRequest);

                }
            }
        });

        txtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayAlert (String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtMaNV.setText("");
                edtPassword.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void addControls() {
        edtMaNV = (EditText) findViewById(R.id.edtMaNVLog);
        edtPassword = (EditText) findViewById(R.id.edtPassLog);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtSign = (TextView) findViewById(R.id.txtSign);
    }
}
