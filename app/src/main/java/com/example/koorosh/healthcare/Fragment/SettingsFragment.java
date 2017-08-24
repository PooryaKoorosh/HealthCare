package com.example.koorosh.healthcare.Fragment;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.koorosh.healthcare.ChooseActionActivity;
import com.example.koorosh.healthcare.G;
import com.example.koorosh.healthcare.Gonnect;
import com.example.koorosh.healthcare.ProfileActivity;
import com.example.koorosh.healthcare.R;
import com.example.koorosh.healthcare.SharedPrefrencesManager;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Koorosh on 8/15/2017.
 */

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        G.context=getContext();
        G.activity=getActivity();

rootView.findViewById(R.id.LogoutBtn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        LogoutUser();
    }
});

        rootView.findViewById(R.id.ProfileBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        G.context=getContext();
        G.activity=getActivity();
    }

    private void LogoutUser() {

        final SharedPrefrencesManager sp = new SharedPrefrencesManager(getContext());
        G.ShowLoading(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Gonnect.cancelRequest("logout");
            }
        });
        ContentValues cv=new ContentValues();
        cv.put("token",sp.getToken());
        Gonnect.sendCancelableRequest(G.server + "logout.php", cv, "logout", new Gonnect.ResponseSuccessListener() {
            @Override
            public void responseRecieved(String response) {
                G.HideLoading();
                if(response.contains("success")){
                    sp.removeEmail();
                    sp.removeName();
                    sp.removePassword();
                    sp.removeToken();
                    sp.removeUsername();
                    sp.removeWeight();
                    sp.removeHeight();
                    sp.removeGender();
                    sp.removeProfileImageName();


                    startActivity(new Intent(getContext(), ChooseActionActivity.class));

                    getActivity().finish();
                }else{
                    G.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), G.ERROR, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }, new Gonnect.ResponseFailureListener() {
            @Override
            public void responseFailed(IOException exception) {G.HideLoading();
                G.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), G.ERROR, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
