package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class BrowseActivity extends AppCompatActivity {
    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;
    public static final String TAG = "BrowseActivity";
    private Context myContext;
    private User dfcUser;
    private FileCabinet cabinet;

    @Override
    protected void onCreate(Bundle savedInstateState){
        super.onCreate(savedInstateState);
        setContentView(R.layout.activity_browse);
        myContext = getApplicationContext();
        cabinet = FileCabinet.getInstance(myContext);
        listView = findViewById(R.id.docList);
//        list = cabinet.getFileBrowser().getList();
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
        list.add("Test4");
        list.add("Test5");
        list.add("Test6");
        list.add("Test7");
        list.add("Test8");
        list.add("Test9");
        list.add("Test10");
        list.add("Test11");
        list.add("Test12");
        list.add("Test13");
        list.add("Test14");

//        final RecyclerView fileListView = findViewById(R.id.listRecycleListView);
        adapter = new ArrayAdapter(myContext, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Log.d(TAG, "Currently Browsing");

    }
}
