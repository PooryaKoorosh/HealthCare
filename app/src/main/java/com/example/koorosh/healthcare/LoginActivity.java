package com.example.koorosh.healthcare;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONObject;

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

                LoginUser(inputEmail.getText().toString(),inputPassword.getText().toString());
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
                Log.e("responseRecieved: ", response);
                G.HideLoading();
                if (ResponseJsonParser(response)) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else if(response.equals("invalid")){
                    G.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "اطلاعات وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    G.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, G.ERROR, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

    private boolean ResponseJsonParser(String response) {
        try {
            JSONObject info = new JSONObject(response);
            JSONArray profile_array = info.getJSONArray("profile");
            String token = info.getString("token");


            SharedPrefrencesManager sp = new SharedPrefrencesManager(getBaseContext());
            sp.setName(profile_array.getJSONObject(0).getString("name").toString());
            sp.setPassword(profile_array.getJSONObject(0).getString("password").toString());
            sp.setUsername(profile_array.getJSONObject(0).getString("username").toString());
            sp.setWeight(profile_array.getJSONObject(0).getString("weight").toString());
            sp.setHeight(profile_array.getJSONObject(0).getString("size").toString());
            sp.setGender(profile_array.getJSONObject(0).getString("sex").toString());
            sp.setToken(token.toString());
            sp.setProfileImageName(profile_array.getJSONObject(0).getString("profile").toString());


            sp.setEmail(profile_array.getJSONObject(0).getString("email").toString());
return  true;
        } catch ( Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
