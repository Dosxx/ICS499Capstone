package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class LogoutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Logout of the application */
        Intent intent = new Intent();
        intent.setClass(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);

        /* Inflate the layout for this fragment */
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}