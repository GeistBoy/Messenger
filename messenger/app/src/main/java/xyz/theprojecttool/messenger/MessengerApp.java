package xyz.theprojecttool.messenger;

import android.app.Application;

import androidx.room.Room;

import xyz.theprojecttool.messenger.database.AppDatabase;

public class MessengerApp extends Application {

    private static final String TAG = "test";
    private static int counter = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("initialize");

        MessengerAppInit.init(this);

        // Create database
        AppDatabase database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "messengerDB").build();
    }


}
