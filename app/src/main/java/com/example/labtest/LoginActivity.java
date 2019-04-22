package com.example.labtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText StudName, StudNo;
    String username;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        sharedPreferences = getApplicationContext().getSharedPreferences("Calculator",0);
        editor = sharedPreferences.edit();

        StudName = findViewById(R.id.edtName);
        StudNo = findViewById(R.id.editstudentNo);

        username = sharedPreferences.getString("username","");

        if(!username.equalsIgnoreCase(""))
        {
            Intent intent = new Intent(this,calculator.class);
            startActivity(intent);
        }

    }

    public void fnLogin(View vw)
    {
        editor.putString("username",StudName.getText().toString());
        editor.putString("studentNo",StudNo.getText().toString());
        editor.commit();

        Intent intent = new Intent(LoginActivity.this, calculator.class);
        startActivity(intent);



    }
}


