package com.example.tim.chyllabus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentRoster extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabRoster", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d("TabRoster", "onCreate FIRST TIME");
        } else {
            Log.d("TabRoster", "onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_roster, container, false);
        Log.d("TabRoster", "onCreateView");



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabRoster", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabRoster", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TabRoster", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabRoster", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabRoster", "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabRoster", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabRoster", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabRoster", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabRoster", "onDetach");
    }
}
