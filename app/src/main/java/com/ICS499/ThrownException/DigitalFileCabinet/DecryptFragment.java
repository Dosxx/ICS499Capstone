package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class DecryptFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent = new Intent();
        intent.setClass(getActivity(), DecryptDocumentActivity.class);
        getActivity().startActivity(intent);
        return inflater.inflate(R.layout.fragment_decrypt, container, false);
    }
}