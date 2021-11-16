package xyz.theprojecttool.messenger.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import xyz.theprojecttool.messenger.database.dao.NotificationDao;
import xyz.theprojecttool.messenger.database.entity.Notification;

@Database(entities = {Notification.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NotificationDao notificationDao();
}
