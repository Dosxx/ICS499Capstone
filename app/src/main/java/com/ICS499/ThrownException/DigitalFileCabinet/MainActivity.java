/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private final Context myContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");





    }
}