package com.example.koorosh.healthcare;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class SharedPrefrencesManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String Email_KEY = "email";
    private static final String USERNAME_KEY = "username";
    private static final String Password_KEY = "password";
    private static final String Name_KEY = "name";
    private static final String Weight_KEY = "weight";
    private static final String Height_KEY = "height";
    private static final String Gender_KEY = "gender";
    private static final String Token_KEY = "token";
    private static final String ProfileImageName_KEY = "image";

    public SharedPrefrencesManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.apply();
    }

    public void setUsername(String username) {
        editor.putString(USERNAME_KEY, username);
        editor.apply();
        editor.commit();
    }

    public String getUsername() {
        return preferences.getString(USERNAME_KEY, "Default");
    }

    public void removeUsername() {
        editor.remove(USERNAME_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setPassword(String password) {
        editor.putString(Password_KEY, password);
        editor.apply();
        editor.commit();
    }

    public String getPassword() {
        return preferences.getString(Password_KEY, "Default");
    }

    public void removePassword() {
        editor.remove(Password_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setEmail(String email) {
        editor.putString(Email_KEY, email);
        editor.apply();
        editor.commit();
    }

    public String getEmail() {
        return preferences.getString(Email_KEY, "Default");
    }

    public void removeEmail() {
        editor.remove(Email_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setName(String name) {
        editor.putString(Name_KEY, name);
        editor.apply();
        editor.commit();
    }

    public String getName() {
        return preferences.getString(Name_KEY, "Default");
    }

    public void removeName() {
        editor.remove(Name_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setToken(String token) {
        editor.putString(Token_KEY, token);
        editor.apply();
        editor.commit();
    }

    public String getToken() {
        return preferences.getString(Token_KEY, "Default");
    }

    public void removeToken() {
        editor.remove(Token_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setWeight(String weight) {
        editor.putString(Weight_KEY, weight);
        editor.apply();
        editor.commit();
    }

    public String getWeight() {
        return preferences.getString(Weight_KEY, "Default");
    }

    public void removeWeight() {
        editor.remove(Weight_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setHeight(String height) {
        editor.putString(Height_KEY, height);
        editor.apply();
        editor.commit();
    }

    public String getHeight() {
        return preferences.getString(Height_KEY, "Default");
    }

    public void removeHeight() {
        editor.remove(Height_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setGender(String gender) {
        editor.putString(Gender_KEY, gender);
        editor.apply();
        editor.commit();
    }

    public String getGender() {
        return preferences.getString(Gender_KEY, "Default");
    }

    public void removeGender() {
        editor.remove(Gender_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================
    public void setProfileImageName(String imageName) {
        editor.putString(ProfileImageName_KEY, imageName);
        editor.apply();
        editor.commit();
    }

    public String getProfileImageName() {
        return preferences.getString(ProfileImageName_KEY, "Default");
    }

    public void removeProfileImageName() {
        editor.remove(ProfileImageName_KEY);
        editor.apply();
        editor.commit();
    }
    //==========================

//    public boolean saveArray(ArrayList<String> sKey)
//    {
//    /* sKey is an array */
//        editor.putInt("Status_size", sKey.size());
//
//        for(int i=0;i<sKey.size();i++)
//        {
//            editor.remove("Status_" + i);
//            editor.putString("Status_" + i, sKey.get(i));
//        }
//
//        return editor.commit();
//    }
//
//    public ArrayList<String> loadArray()
//    {
//        ArrayList<String> sKey = new ArrayList<>();
//        sKey.clear();
//        int size = preferences.getInt("Status_size", 0);
//
//        for(int i=0;i<size;i++)
//        {
//            sKey.add(preferences.getString("Status_" + i, null));
//        }
//        return sKey;
//    }

}