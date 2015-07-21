package com.example.tim.chyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class AddClass extends Activity {

    private Button addClassButton;

    private ParseUser currentUser = ParseUser.getCurrentUser();
    private ParseRelation<ParseObject> currentlyEnrolled = currentUser.getRelation("currentlyEnrolled");

    private ListView coursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_add_class);

        coursesListView = (ListView) findViewById(R.id.listviewCourses);

        addClassButton = (Button) findViewById(R.id.buttonAddClass);
        addClassButton.setEnabled(false);

        ParseQuery query = ParseQuery.getQuery("Course");
        query.orderByAscending("department");
        query.addAscendingOrder("courseCode");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> allCourses, ParseException e) {
                if (e == null) {
                    final int n = allCourses.size();
                    String[] course = new String[n];
                    for (int i = 0; i < n; i++) {
                        String CL = allCourses.get(i).getString("collegeCode");
                        String DP = allCourses.get(i).getString("department");
                        int CC = allCourses.get(i).getInt("courseCode");
                        String CN = allCourses.get(i).getString("courseName");
                        course[i] = CL + " " + DP + CC + " " + CN;
                    }
                    coursesListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, course));
                    coursesListView.setVisibility(View.VISIBLE);
                    coursesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            addClassButton.setTextColor(getResources().getColor(R.color.white));
                            addClassButton.setEnabled(true);
                        }
                    });
                    addClassButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SparseBooleanArray checked = coursesListView.getCheckedItemPositions();
                            if (checked != null) {
                                Intent intent = new Intent(getApplicationContext(), Userpage.class);
                                String TXT = "";
                                for (int i = 0; i < n; i++) {
                                    if (checked.get(i)) {
                                        ParseRelation enrolledStudents = allCourses.get(i).getRelation("enrolledStudents");
                                        enrolledStudents.add(currentUser);
                                        allCourses.get(i).saveInBackground();
                                        currentlyEnrolled.add(allCourses.get(i));
                                        String CL = allCourses.get(i).getString("collegeCode");
                                        String DP = allCourses.get(i).getString("department");
                                        int CC = allCourses.get(i).getInt("courseCode");
                                        TXT = TXT + CL + " " + DP + CC + "\n";
                                    }
                                }
                                currentUser.saveInBackground();
                                Toast.makeText(getApplicationContext(), TXT + "Added", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        final Button requestClassButton = (Button) findViewById(R.id.buttonDontSeeClass);
        requestClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Request Class button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}