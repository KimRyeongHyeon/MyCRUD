package com.myandroid.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText edId, edName, edEmail, edContact, edAddress;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edEmail = findViewById(R.id.ed_email);
        edContact = findViewById(R.id.ed_contact);
        edAddress = findViewById(R.id.ed_address);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edId.setText(MainActivity.employeeArrayList.get(position).getId());
        edName.setText(MainActivity.employeeArrayList.get(position).getName());
        edEmail.setText(MainActivity.employeeArrayList.get(position).getEmail());
        edContact.setText(MainActivity.employeeArrayList.get(position).getContact());
        edAddress.setText(MainActivity.employeeArrayList.get(position).getAddress());
    }

    public void btn_updateData(View view) {
        final String name = edName.getText().toString();
        final String email = edEmail.getText().toString();
        final String contact = edContact.getText().toString();
        final String address = edAddress.getText().toString();
        final String id = edId.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://soulstring94.cafe24.com/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EditActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id);
                params.put("name", name);
                params.put("email", email);
                params.put("contact", contact);
                params.put("address", address);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
        requestQueue.add(request);
    }
}
