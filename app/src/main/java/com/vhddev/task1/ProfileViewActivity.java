package com.vhddev.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileViewActivity extends AppCompatActivity {

    TextView tv_name, tv_contact, tv_catid, tv_desc, tv_empaddress, tv_empcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        String userName = getIntent().getExtras().getString("user_name");
        String userId = getIntent().getExtras().getString("user_id");
        String userContact = getIntent().getExtras().getString("user_contact");
        String userCategory = getIntent().getExtras().getString("user_catid");
        String userDescription = getIntent().getExtras().getString("user_desc");
        String userAddress = getIntent().getExtras().getString("user_addr");
        String userEmpCode = getIntent().getExtras().getString("user_code");

        tv_name = findViewById(R.id.vals_name);
        tv_contact = findViewById(R.id.vals_contact);
        tv_catid = findViewById(R.id.vals_category);
        tv_desc = findViewById(R.id.vals_desc);
        tv_empaddress = findViewById(R.id.vals_address);
        tv_empcode = findViewById(R.id.vals_empcode);

        tv_name.setText(userName);
        tv_contact.setText(userContact);
        tv_catid.setText(userCategory);
        tv_desc.setText(userDescription);
        tv_empaddress.setText(userAddress);
        tv_empcode.setText(userEmpCode);


    }
}