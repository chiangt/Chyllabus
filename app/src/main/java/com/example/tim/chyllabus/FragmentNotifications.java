package com.example.tim.chyllabus;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentNotifications extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TabNotifications", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null) {
            Log.d("TabNotifications","onCreate FIRST TIME");
        } else {
            Log.d("TabNotifications","onCreate SUBSEQUENT");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        Log.d("TabNotifications","onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabNotifications","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TabNotifications","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TabNotifications","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TabNotifications","onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TabNotifications","onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TabNotifications","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TabNotifications","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TabNotifications","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TabNotifications","onDetach");
    }
}
