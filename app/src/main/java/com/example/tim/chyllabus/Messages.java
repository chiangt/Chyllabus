package com.example.tim.chyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class Messages extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messages);

        Intent intent = getIntent();
        final int NumRecipients = intent.getIntExtra("NumRecipients", 0);
        final String[] recipientsId = new String[NumRecipients];
        for (int i = 0; i < NumRecipients; i++) {
            recipientsId[i] = intent.getStringExtra("IdCheckedUser" + i);
        }
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseRelation classmates = currentUser.getRelation("classmate");
        ParseQuery query = classmates.getQuery();
        query.orderByAscending("firstName");
        query.addAscendingOrder("lastName");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> recipients, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < recipients.size(); i++) {
                        for (int j = 0; j < NumRecipients; j++) {
                            if (recipients.get(i).getObjectId().equals(recipientsId[j])) {
                                Toast.makeText(getApplicationContext(), recipients.get(i).getString("firstName"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
