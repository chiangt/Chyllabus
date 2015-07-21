package com.example.tim.chyllabus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class ClassPage extends FragmentActivity {

    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_class_page);

        viewPager = (ViewPager) findViewById(R.id.pagerClasspage);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));

        final ParseUser currentUser = ParseUser.getCurrentUser();
        final ParseRelation currentlyEnrolled = currentUser.getRelation("currentlyEnrolled");

        Intent intent = getIntent();
        final ParseProxyObject course = (ParseProxyObject) intent.getSerializableExtra("parseObject");
        final String objectId = intent.getStringExtra("objectId");

        TextView courseCodeTextView = (TextView) findViewById(R.id.textCourseCode);
        TextView courseNameTextView = (TextView) findViewById(R.id.textCourseName);
        TextView professorTextView = (TextView) findViewById(R.id.textProfessor);

        courseCodeTextView.setText(course.getString("department") + Integer.toString(course.getInt("courseCode")) + " " + course.getString("sectionCode"));
        courseNameTextView.setText(course.getString("courseName"));
        professorTextView.setText("Professor " + course.getString("professor"));

        Button dropClassButton = (Button) findViewById(R.id.buttonDropClass);
        dropClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery query = currentlyEnrolled.getQuery();
                query.whereEqualTo("objectId", objectId);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> courseToDrop, ParseException e) {
                        if (e == null && courseToDrop != null) {
                            ParseObject toDrop = courseToDrop.get(0);
                            String DP = toDrop.getString("department");
                            int CC = toDrop.getInt("courseCode");
                            ParseRelation enrolledStudents = toDrop.getRelation("enrolledStudents");
                            enrolledStudents.remove(currentUser);
                            toDrop.saveInBackground();
                            currentlyEnrolled.remove(toDrop);
                            currentUser.saveInBackground();
                            Toast.makeText(getApplicationContext(), DP + CC + " dropped", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Userpage.class));
                        }
                    }
                });
            }
        });
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new FragmentRoster();
            }
            if (position == 1) {
                fragment = new FragmentClassmates();
            }
            if (position == 2) {
                fragment = new FragmentStudyGroups();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Roster";
            }
            if (position == 1) {
                return "Classmates";
            }
            if (position == 2) {
                return "Study Groups";
            }
            return null;
        }
    }
}
