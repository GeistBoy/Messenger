package xyz.theprojecttool.messenger.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notification {
    @PrimaryKey
    public int id;

    public String contentTitle;
    public String contentText;

    public Notification(){
        this.contentTitle = "No Title";
        this.contentText = "No Text";
    }
}
