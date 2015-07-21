package com.example.tim.chyllabus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentStudyGroups extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabStudyGroups", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d("TabStudyGroups", "onCreate FIRST TIME");
        } else {
            Log.d("TabStudyGroups", "onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_studygroups, container, false);
        Log.d("TabStudyGroups", "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabStudyGroups", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabStudyGroups", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TabStudyGroups", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabStudyGroups", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabStudyGroups", "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabStudyGroups", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabStudyGroups", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabStudyGroups", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabStudyGroups", "onDetach");
    }
}
