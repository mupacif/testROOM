package com.example.evoliris.myapplication.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evoliris.myapplication.R;
import com.example.evoliris.myapplication.data.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evoliris on 06/02/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {
    Context context;
    List<Message> messages;
    public Adapter(Context ctx)
    {
        Log.e("adapter","adapterrr");
        this.context = ctx;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
    Message msg = messages.get(position);
    Log.e("adapter","position:"+position);
    holder.tv.setText(msg.message);
    }
public void setDate(List<Message> messages)
{
    this.messages = messages;
    Log.e("adapter","messages:"+messages.size());
    this.notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return this.messages!=null ? this.messages.size():0;
    }
    public class VH extends RecyclerView.ViewHolder
    {
        @BindView(R.id.item)
        TextView tv;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
