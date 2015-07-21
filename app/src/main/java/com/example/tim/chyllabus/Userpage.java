package com.example.tim.chyllabus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.parse.Parse;

public class Userpage extends FragmentActivity {

    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);

        viewPager = (ViewPager) findViewById(R.id.pagerUserpage);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));
    }
}

class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragmentProfile();
        }
        if (position == 1) {
            fragment = new FragmentClasses();
        }
        if (position == 2) {
            fragment = new FragmentClassmatesMain();
        }
        if (position == 3) {
            fragment = new FragmentMessages();
        }
        if (position == 4) {
            fragment = new FragmentNotifications();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Profile";
        }
        if (position == 1) {
            return "Classes";
        }
        if (position == 2) {
            return "Classmates";
        }
        if (position == 3) {
            return "Messages";
        }
        if (position == 4) {
            return "Notifications";
        }
        return null;
    }
}