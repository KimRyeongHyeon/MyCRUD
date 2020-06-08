package com.myandroid.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvid, tvname, tvemail, tvcontact, tvaddress;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtName);
        tvemail = findViewById(R.id.txtEmail);
        tvcontact = findViewById(R.id.txtContact);
        tvaddress = findViewById(R.id.txtAddress);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID : " + MainActivity.employeeArrayList.get(position).getId());
        tvname.setText("Name : " + MainActivity.employeeArrayList.get(position).getName());
        tvemail.setText("Email : " + MainActivity.employeeArrayList.get(position).getEmail());
        tvcontact.setText("Contact : " + MainActivity.employeeArrayList.get(position).getContact());
        tvaddress.setText("Address : " + MainActivity.employeeArrayList.get(position).getAddress());
    }
}
