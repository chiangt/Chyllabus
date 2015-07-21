package com.example.tim.chyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class FragmentClasses extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabClasses", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d("TabClasses", "onCreate FIRST TIME");
        } else {
            Log.d("TabClasses", "onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseRelation currentlyEnrolled = currentUser.getRelation("currentlyEnrolled");
        ParseQuery query = currentlyEnrolled.getQuery();
        query.orderByAscending("department");
        query.addAscendingOrder("courseCode");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> courses, ParseException e) {
                if (courses != null) {
                    int n = courses.size();
                    for (int i = 0; i < n; i++) {
                        populateClassButtons(courses.get(i));
                    }
                    populateClassButtons(null);
                }
            }
        });

        Log.d("TabClasses", "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabClasses", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabClasses", "onStart");
    }

    @Override
    public void onResume() {

        super.onResume();
        Log.d("TabClasses", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabClasses", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabClasses", "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabClasses", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabClasses", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabClasses", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabClasses", "onDetach");
    }

    private void populateClassButtons(final ParseObject course) {

        TableLayout table = (TableLayout) getView().findViewById(R.id.layoutClassButtons);

        TableRow tableRow = new TableRow(getActivity());
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                2.0f));
        table.addView(tableRow);
        Button button = new Button(getActivity());
        //button.setTextColor(getResources().getColor(R.color.white));
        if (course != null) {
            final String courseName = course.getString("courseName");
            final String department = course.getString("department");
            final int courseCode = course.getInt("courseCode");
            final String section = course.getString("sectionCode");
            final String professor = course.getString("professor");

            button.setText(courseName + "\n" + department + courseCode + " " + section + "    " + professor);
            button.setBackgroundColor(getResources().getColor(R.color.light_blue));
            tableRow.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toClasses(course);
                }
            });
        } else {
            button.setTextColor(getResources().getColor(R.color.white));
            button.setText("Add class");
            button.setBackgroundColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), AddClass.class));
                }
            });
        }
    }

    private void toClasses(ParseObject course) {
        Intent intent = new Intent(getActivity(), ClassPage.class);

        ParseProxyObject ppo = new ParseProxyObject(course);
        intent.putExtra("parseObject", ppo);
        String objectId = course.getObjectId();
        intent.putExtra("objectId", objectId);

        startActivity(intent);
    }
}
