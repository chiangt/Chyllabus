package com.example.tim.chyllabus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentClassmates extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabClassmates", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d("TabClassmates", "onCreate FIRST TIME");
        } else {
            Log.d("TabClassmates", "onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_classmates, container, false);
        Log.d("TabClassmates", "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabClassmates", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabClassmates", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TabClassmates", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabClassmates", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabClassmates", "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabClassmates", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabClassmates", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabClassmates", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabClassmates", "onDetach");
    }
}
