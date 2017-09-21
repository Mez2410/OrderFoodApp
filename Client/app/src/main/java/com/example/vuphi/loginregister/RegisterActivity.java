package com.example.vuphi.loginregister;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmployCode, edtNameReg, edtPassReg, edtConPassReg;
    Spinner spnPosition;
    Button btnRegister;
    TextView txtBackLog;
    String employee_code, name, password, confirmPass, position;
    AlertDialog.Builder builder;
    UrlData urlData = new UrlData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // tạo hộp thoại AlertDialog
        builder = new AlertDialog.Builder(RegisterActivity.this);

        // Hàm khởi tạo và ánh xạ các controller
        addControls();
        // Hàm bắt sự kiện cho các control
        addEvents();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Truyền các giá trị trong spinner vào trong Adapter
        spnPosition.setAdapter(adapter);
    }

    public void addEvents() {
        // bắt sự kiện cho nút bấm Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy chuỗi từ Edit text
                employee_code = edtEmployCode.getText().toString();
                name = edtNameReg.getText().toString();
                password = edtPassReg.getText().toString();
                confirmPass = edtConPassReg.getText().toString();
                position = spnPosition.getSelectedItem().toString();

                // user không điền đầy đủ thông tin
                if ((employee_code.equals("")||name.equals("")||password.equals("")||confirmPass.equals(""))) {
                    builder.setTitle("Error! :((");
                    builder.setMessage("Bạn vui lòng điền đầy đủ thông tin!");
//                            .setNegativeButton("OK", null)
//                            .create().show();
                    displayAlert("input_error");
                }
                else {
                    if (password.length() >= 6) {
                        // user nhập pass không trùng khớp
                        if (!password.equals(confirmPass)) {
                            builder.setTitle("Error! :((");
                            builder.setMessage("Mật khẩu của bạn không khớp!");
//                                .setNegativeButton("OK", null)
//                                .create().show();
                            displayAlert("input_error");
                        }
                        else {
                            // Request a string response from the provided URL.
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlData.URL_REGISTER,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {

                                                JSONArray jsonArray = new JSONArray(response);
                                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                                String code = jsonObject.getString("code");
                                                String massage = jsonObject.getString("message");
                                                if (code.equals("id_overlap")) {
                                                    builder.setTitle("Error!!! :((");
                                                    builder.setMessage(massage);
                                                } else if (code.equals("reg_success")) {
                                                    builder.setTitle("Successfully! :))");
                                                    builder.setMessage(massage);
                                                }
                                                displayAlert(code);
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
                                    params.put("EmployeeCode", employee_code);
                                    params.put("Name", name);
                                    params.put("Password", password);
                                    params.put("Position", position);
                                    return params;
                                }
                            };
                            // Access the RequestQueue through your singleton class.
                            MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu phải có độ dài tối thiểu 6 ký tự!!!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        // Bắt sự kiện quay lại màn hình đăng nhập
        txtBackLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void displayAlert (final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")) {
                    edtPassReg.setText("");
                    edtConPassReg.setText("");
                }
                else if (code.equals("reg_success")) {
                    finish();
                }
                else if (code.equals("id_overlap")) {
                    edtEmployCode.setText("");
//                    edtNameReg.setText("");
//                    edtPassReg.setText("");
//                    edtConPassReg.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void addControls() {
        edtEmployCode = (EditText) findViewById(R.id.edt_employee_code);
        edtNameReg = (EditText) findViewById(R.id.edt_last_first_name);
        edtPassReg = (EditText) findViewById(R.id.edtPassReg);
        edtConPassReg = (EditText) findViewById(R.id.edtConPassReg);
        spnPosition = (Spinner) findViewById(R.id.spn_position);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        txtBackLog = (TextView) findViewById(R.id.txtBackLog);
    }
}
