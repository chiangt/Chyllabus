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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

public class FragmentProfile extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabProfile","onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState==null) {
            Log.d("TabProfile","onCreate FIRST TIME");
        } else {
            Log.d("TabProfile","onCreate SUBSEQUENT");
        }
    }

    private TextView classesBodyText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)   {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final ParseUser currentUser = ParseUser.getCurrentUser();

        ImageView profilePicture = (ImageView) view.findViewById(R.id.imageProfilePicture);

        TextView nameText = (TextView) view.findViewById(R.id.textName);
        nameText.setText(currentUser.getString("firstName").trim() + " " + currentUser.getString("lastName").trim());
        TextView universityText = (TextView) view.findViewById(R.id.textUniversity);
        if (currentUser.getString("university")!=null) {
            universityText.setText(currentUser.getString("university").trim());
        }
        TextView classYearText = (TextView) view.findViewById(R.id.textClassYear);
        if (!Integer.toString(currentUser.getInt("classYear")).equals(""+0)) {
            classYearText.setText("Class of " + Integer.toString(currentUser.getInt("classYear")));
        }
        TextView majorTitleText = (TextView) view.findViewById(R.id.textMajor);
        TextView majorText = (TextView) view.findViewById(R.id.textYourMajor);
        if (currentUser.getString("major")!=null) {
            if (currentUser.getString("majorSecond")!=null) {
                majorTitleText.setText(R.string.majors);
                majorText.setText(currentUser.getString("major") + "\n" + currentUser.getString("majorSecond"));
            } else {
                majorText.setText(currentUser.getString("major"));
            }
        }

        classesBodyText = (TextView) view.findViewById(R.id.textClassesBody);
        ParseRelation currentlyEnrolled = currentUser.getRelation("currentlyEnrolled");
        ParseQuery query = currentlyEnrolled.getQuery();
        query.orderByAscending("department");
        query.addAscendingOrder("courseCode");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> courses, ParseException e) {
                if (e == null) {
                    final int n = courses.size();
                    String classes = "";
                    for (int i=0; i<n; i++) {
                        String CL = courses.get(i).getString("collegeCode");
                        String DP = courses.get(i).getString("department");
                        int CC = courses.get(i).getInt("courseCode");
                        String CN = courses.get(i).getString("courseName");
                        classes = classes + CL + " " + DP + CC + " " + CN + "\n";
                    }
                    if (!classes.isEmpty()) {
                        classesBodyText.setText(classes);
                    } else {
                        classesBodyText.setText(R.string.empty_class_list);
                    }
                }
            }
        });


        final TextView bioBodyText = (TextView) view.findViewById(R.id.textBioBody);
        final String bio = currentUser.getString("bio");
        if (bio!=null) {
            if (!bio.isEmpty()) {
                bioBodyText.setText(bio);
            } else {
                bioBodyText.setText(R.string.empty_bio);
            }
        }

        final Button settingsButton = (Button) view.findViewById(R.id.buttonSettings);
        final Button logoutButton = (Button) view.findViewById(R.id.buttonLogout);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Settings button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Toast.makeText(getActivity(), "Log out successful", Toast.LENGTH_SHORT).show();
                toLogin(currentUser);
            }
        });

        Log.d("TabProfile","onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabProfile","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabProfile","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TabProfile","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabProfile","onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabProfile","onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabProfile","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabProfile","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabProfile","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabProfile","onDetach");
    }

    private void toLogin(ParseUser currentUser) {
        Intent intent = new Intent(getActivity(), Login.class);
        String email = currentUser.getEmail();
        intent.putExtra("tag",2);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}
