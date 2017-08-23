package com.example.koorosh.healthcare;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Koorosh on 8/23/2017.
 */

public class RegisterActivity extends Activity {


    String path;Uri uri;
    EditText edEmail , edName, edPassoword , edHeight , edWeight;
    Spinner spGender;
    AppCompatButton btnRegister , btnChooseDocument;
    LinearLayout linearLayout;
    TextView LinkSignIn , SpinnerItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail = findViewById(R.id.ed_email);
        edName = findViewById(R.id.ed_Name);
        edPassoword = findViewById(R.id.ed_password);
        edHeight = findViewById(R.id.ed_Height);
        edWeight = findViewById(R.id.ed_Weight);
        spGender = findViewById(R.id.sp_gender);
        btnRegister = findViewById(R.id.btn_register);
        btnChooseDocument = findViewById(R.id.btn_chooseImage);
        linearLayout = findViewById(R.id.linearLayoutRegister);
        LinkSignIn = findViewById(R.id.link_signin);
        SpinnerItem = findViewById(R.id.SpinnerItem);

        G.context=this;
        G.activity=this;

        ArrayList<String> items=new ArrayList<>();
        items.add("مرد");
        items.add("زن");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_item,items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(arrayAdapter);

        G.setCustomTypeface(edEmail);
        G.setCustomTypeface(edName);
        G.setCustomTypeface(edPassoword);
        G.setCustomTypeface(edHeight);
        G.setCustomTypeface(edWeight);
        G.setCustomTypeface(SpinnerItem);
        G.setCustomTypeface(btnRegister);
        G.setCustomTypeface(btnChooseDocument);
        G.setCustomTypeface(LinkSignIn);


        btnChooseDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseImageIntent = GetImage.getPickImageIntent(G.context);
                startActivityForResult(chooseImageIntent, 1);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CheckForEmptyEditText()) {
                    Toast.makeText(RegisterActivity.this, "لطفا تمامی فیلد ها را پر بفرمایید", Toast.LENGTH_SHORT).show();
                }
                else {
                    // uploadImageFile( "","","","",);
                    G.ChoosedDocument = false;
                }
            }
        });

        LinkSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });


    }




    private void setImage(Bitmap b){
        G.ChoosedDocument=true;
        FileOutputStream out =null;
        try {
//            File yekanDir = new File(Environment.getExternalStorageDirectory().getPath() + "/CityReport/");
//            File yekanDir = new File(Environment.getDataDirectory().getPath() +"/");
            File yekanDir = new File(G.context.getFilesDir() + "/CityReport/");

            if (!yekanDir.exists()) {
                Log.d("file", yekanDir.mkdirs() + ""+yekanDir.getAbsolutePath());

            }
            String rootPath = yekanDir.getPath();
            out = new FileOutputStream(rootPath + "/" + "tempFile.png");
            path=rootPath + "/" + "tempFile.png";
            b.compress(Bitmap.CompressFormat.PNG, 100, out);

        }catch (Exception ex){
            Log.d("Err",ex.getMessage().toString());
            path="";

        }finally {
            try {
                if (out != null) {
                    out.close();

                }
            } catch (IOException e) {
                e.printStackTrace(); path="";
                Log.d("Err",e.getMessage().toString());
            }
        }

    }

    private void uploadImageFile(String name,String height,String weight , String gender , String email , String password){
        G.ShowLoading(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Gonnect.cancelRequest("post");
            }
        });
        String url=G.server+"add-image.php";
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("height",height);
        cv.put("weight",weight);
        cv.put("gender",gender);
        cv.put("email",gender);
        cv.put("password",gender);
        Gonnect.upload(url, cv,"post","image", "tempFile.png", "image/png", new File(path), new Gonnect.ResponseSuccessListener() {
            @Override
            public void responseRecieved(String response) {
                String res=response.trim();
                Log.d("RES",res);
                G.HideLoading();
                G.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(G.context, G.SUCCESS, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }, new Gonnect.ResponseFailureListener() {
            @Override
            public void responseFailed(IOException exception) {
                Log.d("ERR",exception.getMessage().toString());

                G.HideLoading();
                G.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(G.context, G.ERROR, Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        Log.d("LOG",reqCode+"");
        switch(reqCode) {
            case 1:
                Bitmap bitmap = GetImage.getImageFromResult(G.context, resultCode, data);
                setImage(bitmap);
                // TODO use bitmap
                break;
            default:
                super.onActivityResult(reqCode, resultCode, data);
                break;
        }
    }


    public boolean CheckForEmptyEditText() {
        ArrayList<EditText> myEditTextList = new ArrayList<EditText>();

        for( int i = 0; i < linearLayout.getChildCount(); i++ )
            if( linearLayout.getChildAt( i ) instanceof EditText )
                myEditTextList.add( (EditText) linearLayout.getChildAt( i ) );

        for (EditText ed:myEditTextList) {

            if(ed.getText().toString().equals(""))
            {
                return false;
            }
        }
        return true;
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
        startActivity(new Intent(RegisterActivity.this,ChooseActionActivity.class));
        finish();
    }
}
