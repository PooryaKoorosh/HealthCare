package com.example.koorosh.healthcare;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Koorosh on 8/11/2017.
 */

public class G extends Application{
public static Typeface yekan;

    public static String server="http://pk.pabbasi.ir/hc/";
    public static SweetAlertDialog alertDialog;


    public static String SUCCESS="عملیات مورد نظر با موفقیت انجام شد";
    public static String ERROR="خطایی در اتصال به اینترنت رخ داد";
    public static String LOADING="در حال بارگذاری ...";

    public static  boolean ChoosedDocument = false;

    public static Context context;
    public static Activity activity;



    public static void showAlert(String Message , Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("هشدار");
        alertDialog.setMessage(Message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"تایید",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static void ShowLoading(DialogInterface.OnCancelListener ocl){
        alertDialog = new SweetAlertDialog(G.context, SweetAlertDialog.PROGRESS_TYPE);
        alertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        alertDialog.setTitleText(LOADING);
        //alertDialog.setCancelable(false);
        alertDialog.setOnCancelListener(ocl);
        G.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();

            }
        });
    }

    public static void HideLoading(){
        G.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    alertDialog.dismissWithAnimation();
                }catch (Exception ex){

                }
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void setCustomTypeface(View view) {
        yekan=Typeface.createFromAsset(G.context.getAssets(),"fonts/yekan.TTF");
        Typeface typeFace=yekan;
        if (view instanceof TextView) {
            ((TextView)view).setTypeface(typeFace);
        } else if (view instanceof EditText) {
            ((EditText)view).setTypeface(typeFace);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                setCustomTypeface(viewGroup.getChildAt(i));
            }
        }
    }
}
