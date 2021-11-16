package xyz.theprojecttool.messenger.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cn.leancloud.LCInstallation;
import xyz.theprojecttool.messenger.R;
import xyz.theprojecttool.messenger.activity.utility.NotificationAction;

public class MainActivity extends Activity {

    private static final String TAG = "test";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // show installation id for testing
        final TextView installationIdLabelTextView = (TextView) this.findViewById(R.id.installationIdLabel);
        if(LCInstallation.getCurrentInstallation().getInstallationId() != null){
            installationIdLabelTextView.setText(LCInstallation.getCurrentInstallation().getInstallationId());
        }else {
            installationIdLabelTextView.setText("installation有问题");
        }

        // get context for button onClick functions
        Context context = this;
        // test notification
        final Button testNotificationButton = this.findViewById(R.id.TestNotificationButton);
        testNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "Onclick");
                NotificationAction.sendNotification(context, "测试信息", "测试信息内容");
            }
        });

        // show Notification History Activity
        final Button viewNotificationHistoryButton = this.findViewById(R.id.ViewNotificationHistoryButton);
        viewNotificationHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "view history on click");
                Intent intent = new Intent(context, NotificationHistoryActivity.class);
                startActivity(intent);
            }
        });
    }


}
