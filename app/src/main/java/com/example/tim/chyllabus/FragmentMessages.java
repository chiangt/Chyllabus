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
import android.widget.TabHost;

public class FragmentMessages extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabMessages", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null) {
            Log.d("TabMessages","onCreate FIRST TIME");
        } else {
            Log.d("TabMessages","onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        final TabHost tabHost = (TabHost) view.findViewById(R.id.tabHostMessages);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("classmates");
        tabSpec.setContent(R.id.tabClassmateMessages);
        tabSpec.setIndicator("Classmates");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("groups");
        tabSpec.setContent(R.id.tabGroupMessages);
        tabSpec.setIndicator("Groups");
        tabHost.addTab(tabSpec);

        final Button createNewMessageButton = (Button) view.findViewById(R.id.buttonNewConversation);
        createNewMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Classmates.class));
            }
        });

        Log.d("TabMessages","onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabMessages","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabMessages","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TabMessages","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabMessages","onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabMessages","onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabMessages","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabMessages","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabMessages","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabMessages","onDetach");
    }
}
