package com.example.evoliris.myapplication.data;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@android.arch.persistence.room.Dao
public interface Dao {
    @Insert
    public void insertMessage(Message... messages);
    @Query("Select * from message")
    public Message[] loadAllMessages();
}
