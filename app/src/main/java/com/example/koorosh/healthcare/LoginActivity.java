package com.example.koorosh.healthcare;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Koorosh on 8/23/2017.
 */

public class LoginActivity extends Activity {

    EditText inputEmail,inputPassword;
    AppCompatButton btnLogin;
    TextView LinkSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        LinkSignUp = findViewById(R.id.link_signup);


        G.context=this;
        G.activity=this;

        G.setCustomTypeface(inputEmail);
        G.setCustomTypeface(inputPassword);
        G.setCustomTypeface(btnLogin);
        G.setCustomTypeface(LinkSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //LoginUser("","");
                //Test Code:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });

        LinkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

    }

    private void LoginUser(String Username, String Password) {
        G.ShowLoading(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Gonnect.cancelRequest("login");
            }
        });
        ContentValues cv = new ContentValues();
        cv.put("user", Username);
        cv.put("pass", Password);
        Gonnect.sendCancelableRequest(G.server + "login.php", cv, "login", new Gonnect.ResponseSuccessListener() {
            @Override
            public void responseRecieved(String response) {
                G.HideLoading();
//                if (ResponseJsonParser(response)) {
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                }else if(response.equals("invalid")){
//                    G.activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(LoginActivity.this, "اطلاعات وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    G.activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(LoginActivity.this, G.ERROR, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
            }
        }, new Gonnect.ResponseFailureListener() {
            @Override
            public void responseFailed(IOException exception) {
                G.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        G.HideLoading();
                        Toast.makeText(LoginActivity.this, G.ERROR, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        G.context=this;
        G.activity=this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this,ChooseActionActivity.class));
        finish();
    }
}
