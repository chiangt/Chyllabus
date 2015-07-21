package com.example.tim.chyllabus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FragmentClassmatesMain extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabClassmatesMain", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d("TabClassmatesMain", "onCreate FIRST TIME");
        } else {
            Log.d("TabClassmatesMain", "onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_classmates_main, container, false);


        Log.d("TabClassmatesMain", "onCreateView");
        return view;
    }

    private ParseUser currentUser;
    private String currentUserId;
    private ArrayAdapter<String> namesArrayAdapter;
    private ArrayList<String> names;
    private ListView usersListView;

    private void setConversationsList() {
        currentUser = ParseUser.getCurrentUser();
        currentUserId = currentUser.getObjectId();
        names = new ArrayList<String>();

        ParseRelation classmates = currentUser.getRelation("classmate");
        ParseQuery query = classmates.getQuery();
        query.orderByAscending("firstName");
        query.addAscendingOrder("lastName");
        query.whereNotEqualTo("objectId", currentUserId);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < userList.size(); i++) {
                        String first = userList.get(i).getString("firstName");
                        String last = userList.get(i).getString("lastName");
                        names.add(first + " " + last);
                    }
                    usersListView = (ListView) getView().findViewById(R.id.listviewClassmatesMain);
                    namesArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.user_list_item, names);
                    usersListView.setAdapter(namesArrayAdapter);

                    usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "Error loading user list", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabClassmatesMain", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabClassmatesMain", "onStart");
    }

    @Override
    public void onResume() {
        setConversationsList();
        super.onResume();
        Log.d("TabClassmatesMain", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabClassmatesMain", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabClassmatesMain", "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabClassmatesMain", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabClassmatesMain", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabClassmatesMain", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabClassmatesMain", "onDetach");
    }
}