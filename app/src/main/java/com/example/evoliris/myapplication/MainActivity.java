package com.example.evoliris.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evoliris.myapplication.data.Dao;
import com.example.evoliris.myapplication.data.Database;
import com.example.evoliris.myapplication.data.Message;
import com.example.evoliris.myapplication.model.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{

    Dao dao;
    @BindView(R.id.recyclerView)
    RecyclerView rc;
    Adapter adapter;

    @BindView(R.id.editText)
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yrd);
        ButterKnife.bind(this);
        rc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        rc.setAdapter(adapter);
        AsyncTask<Void,Void,List<Message>> at = new AsyncTask<Void, Void, List<Message>>() {
            @Override
            protected List<Message> doInBackground(Void... voids) {
                return Arrays.asList(Database
                        .getInstance(MainActivity.this)
                        .getDao()
                        .loadAllMessages());
            }

            @Override
            protected void onPostExecute(List<Message> messages) {
                adapter.setDate(messages);
                Log.d("asynctask","message length:"+messages.size());
                super.onPostExecute(messages);
            }
        };
        at.execute();
        text.setOnEditorActionListener(this);


    }

    @Override
    public boolean onEditorAction(final TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            if (event == null || !event.isShiftPressed()) {
                // the user is done typing.
                Log.e("main",textView.getText().toString());


                AsyncTask<Void,Void,Void> at = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... strings) {

                        Message msg = new Message(textView.getText().toString());
                        Database
                                .getInstance(MainActivity.this)
                                .getDao()
                                .insertMessage(msg);
                                 textView.setText("");
                        return null;
                    }
                };

                at.execute();




                return true; // consume.
            }
        }
        return false; // pass on to other listeners.
    }
}
