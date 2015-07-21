package com.example.tim.chyllabus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class Classmates extends Activity {

    private ParseUser currentUser = ParseUser.getCurrentUser();
    private Button newConvoButton;

    private ListView classmatesToMessageListView;
    private String[] classmates, classmatesID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_classmates);

        newConvoButton = (Button) findViewById(R.id.buttonStartNewConvo);
        newConvoButton.setEnabled(false);

        classmatesToMessageListView = (ListView) findViewById(R.id.listviewClassmatesToMessage);

        ParseRelation<ParseUser> relation = currentUser.getRelation("classmate");
        ParseQuery query = relation.getQuery();
        query.orderByAscending("firstName");
        query.addAscendingOrder("lastName");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> classmatesParseUser, ParseException e) {
                if (classmatesParseUser != null) {
                    final int n = classmatesParseUser.size();
                    classmates = new String[n];
                    classmatesID = new String[n];
                    for (int i = 0; i < n; i++) {
                        String firstName = classmatesParseUser.get(i).getString("firstName");
                        String lastName = classmatesParseUser.get(i).getString("lastName");
                        String UserId = classmatesParseUser.get(i).getObjectId();
                        classmates[i] = firstName + " " + lastName;
                        classmatesID[i] = UserId;
                    }
                    classmatesToMessageListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, classmates));
                    classmatesToMessageListView.setVisibility(View.VISIBLE);
                    classmatesToMessageListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    classmatesToMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            newConvoButton.setTextColor(getResources().getColor(R.color.white));
                            newConvoButton.setEnabled(true);
                        }
                    });

                    newConvoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SparseBooleanArray checked = classmatesToMessageListView.getCheckedItemPositions();
                            if (checked != null) {
                                Intent intent = new Intent(getApplicationContext(), Messages.class);
                                intent.putExtra("NumRecipients", checked.size());
                                int index = 0;
                                for (int i = 0; i < n; i++) {
                                    if (checked.get(i)) {
                                        intent.putExtra("IdCheckedUser" + index, classmatesID[i]);
                                        index++;
                                    }
                                }
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Please choose at least one Classmate to message", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
