package com.example.evoliris.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.evoliris.myapplication.data.Dao;
import com.example.evoliris.myapplication.data.Database;
import com.example.evoliris.myapplication.data.Message;
import com.example.evoliris.myapplication.model.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindAnim;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    Dao dao;
    @BindView(R.id.recyclerView)
    RecyclerView rc;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yrd);
        rc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        rc.setAdapter(adapter);
        List<Message> msg = Arrays.asList(Database
                .getInstance(MainActivity.this)
                .getDao()
                .loadAllMessages());
        adapter.setDate(msg);
    }
}
