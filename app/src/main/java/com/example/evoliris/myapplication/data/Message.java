package com.example.evoliris.myapplication.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey
    public int id;

    public String message;
}
