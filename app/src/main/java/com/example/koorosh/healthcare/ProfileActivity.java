package com.example.koorosh.healthcare;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Koorosh on 8/24/2017.
 */

public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPrefrencesManager sp = new SharedPrefrencesManager(getBaseContext());

        TextView tvUsername = findViewById(R.id.tvNumber1);
        TextView tvPassword = findViewById(R.id.tvNumber2);
        TextView tvEmail = findViewById(R.id.tvNumber3);
        TextView tvName = findViewById(R.id.tvNumber4);
        TextView tvWeight = findViewById(R.id.tvNumber5);
        TextView tvHeight = findViewById(R.id.tvNumber6);
        final ImageView ProfileImage = findViewById(R.id.ProfileImage);

        tvUsername.setText(sp.getUsername());
        tvPassword.setText(sp.getPassword());
        tvEmail.setText(sp.getEmail());
        tvName.setText(sp.getName());
        tvWeight.setText(sp.getWeight());
        tvHeight.setText(sp.getHeight());

        G.ShowLoadingWithOutCancel(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {

            }
        });

//        Log.e("onCreate: ",G.server_uploads + sp.getProfileImagePath());
//        Picasso.with(getBaseContext())
//                .load(G.server_uploads + sp.getProfileImagePath())
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                        G.HideLoading();
//                        ProfileImage.setImageBitmap(bitmap);
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//                        G.HideLoading();
//                        ProfileImage.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this,R.drawable.doctor));
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });

        Picasso.with(getBaseContext())
                .load(G.server_uploads + sp.getProfileImageName())
                .into(ProfileImage);

    }

}
