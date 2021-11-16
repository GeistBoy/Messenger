package xyz.theprojecttool.messenger.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import xyz.theprojecttool.messenger.R;

public class NotificationHistoryActivity extends Activity {

    // Array of strings...
    String[] countryArray = {"China", "India", "Pakistan", "USA", "UK"};//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/android/android_list_view.html


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_history);

    }
}
