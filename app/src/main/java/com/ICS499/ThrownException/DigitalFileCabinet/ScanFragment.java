package com.ICS499.ThrownException.DigitalFileCabinet;


import androidx.fragment.app.Fragment;

import javax.swing.text.View;

public class ScanFragment extends Fragment {

    public ScanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);

        return rootView;
    }

}