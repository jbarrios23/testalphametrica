package com.android.testalphametrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static String CLASS_TAG=LoginActivity.class.getSimpleName();
    public Button login;
    public TextView link_forgot;
    public TextView link_back;
    public EditText userName;
    public EditText password;
    public String name_login;
    public String pass_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.button_login);
        link_forgot=findViewById(R.id.link_forgot);
        link_back=findViewById(R.id.link_login_back);
        userName=findViewById(R.id.email_login);
        password=findViewById(R.id.pass_login);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            name_login=extras.getString("username");
            userName.setText(name_login);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateField();
            }
        });

        link_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHomeActivity();
            }
        });
        link_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToRegister();
            }
        });
    }
    private void validateField() {
        name_login=userName.getText().toString();
        pass_login=password.getText().toString();
        if (name_login.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass_login.matches("")) {
            Toast.makeText(this, "You did not enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Utils.isValidMail(name_login)){
            Toast.makeText(this, "Invalid Email of user", Toast.LENGTH_LONG).show();
            return;
        }

        if(!Utils.isValidPassAndConfirm(name_login,Utils.USERNAME)){
            Toast.makeText(this, "Invalid User", Toast.LENGTH_LONG).show();
            return;
        }

        if(!Utils.isValidPassAndConfirm(pass_login,Utils.PASSWORD)){
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show();
            return;
        }

        gotoHomeActivity();
    }

    private void gotoHomeActivity() {

        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }
    private void backToRegister() {

        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }


}