package com.example.administrador.myapplication.controllers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.models.persistence.UsersRepository;
import com.example.administrador.myapplication.util.AppUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_material);

        final EditText txtLogin = AppUtil.get(findViewById(R.id.editTextLogin));
        final EditText txtPassword = AppUtil.get(findViewById(R.id.editTextPass));
        final Button btnLogin = (Button) findViewById(R.id.buttonLogin);

        // Change typeface for the password field
        txtPassword.setTypeface(Typeface.DEFAULT);
        txtPassword.setTransformationMethod(new PasswordTransformationMethod());

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setLogin(txtLogin.getText().toString());
                user.setPassword(txtPassword.getText().toString());
                if(UsersRepository.getInstance().validateLoginUser(user)) {
                    startActivity(new Intent(MainActivity.this, ServiceOrderListActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, getString(R.string.msg_invalid_user), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
