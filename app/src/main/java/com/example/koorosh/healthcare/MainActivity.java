package com.example.koorosh.healthcare;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.koorosh.healthcare.Fragment.CalorieFragment;
import com.example.koorosh.healthcare.Fragment.HomeFragment;
import com.example.koorosh.healthcare.Fragment.SettingsFragment;
import com.example.koorosh.healthcare.Fragment.WalkingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    HomeFragment homeFragment;
    SettingsFragment settingsFragment;
    WalkingFragment walkingFragment;
    CalorieFragment calorieFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        G.context=this;
        G.activity=this;

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView


        BottomBar bar=(BottomBar)findViewById(R.id.bottom_navigation);
        G.setCustomTypeface(bar);
        bar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_walking:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_calorie:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_settings:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment=new HomeFragment();
        walkingFragment = new WalkingFragment();
        calorieFragment =new CalorieFragment();
        settingsFragment = new SettingsFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(walkingFragment);
        adapter.addFragment(calorieFragment);
        adapter.addFragment(settingsFragment);

        viewPager.setAdapter(adapter);
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
        startActivity(new Intent(MainActivity.this,ChooseActionActivity.class));
        finish();
    }
}
