package xyz.theprojecttool.messenger.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.leancloud.json.JSON;
import cn.leancloud.json.JSONObject;
import xyz.theprojecttool.messenger.activity.MainActivity;
import xyz.theprojecttool.messenger.activity.utility.NotificationAction;

public class WechatPushReceiver extends BroadcastReceiver {

    private static final String TAG = "test";
    private static int counter = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 获取推送消息数据
        String message = intent.getStringExtra("com.avoscloud.Data");
        String channel = intent.getStringExtra("com.avoscloud.Channel");
        System.out.println("message=" + message + ", channel=" + channel);

        // default message
        String contentTitle = "微信";
        String contentText = "收到微信消息";

        try{
            JSONObject jsonObject = JSON.parseObject(message);
            contentTitle = jsonObject.get("title").toString();
            contentText = jsonObject.get("alert").toString();

            Log.e(TAG, jsonObject.get("alert").toString());
        } catch (Exception e){
            e.printStackTrace();
        }


        NotificationAction.sendNotification(context, contentTitle, contentText);
    }
}