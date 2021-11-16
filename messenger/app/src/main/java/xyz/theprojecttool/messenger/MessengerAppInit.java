package xyz.theprojecttool.messenger;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import cn.leancloud.LCInstallation;
import cn.leancloud.LCLogger;
import cn.leancloud.LCObject;
import cn.leancloud.LeanCloud;
import cn.leancloud.push.PushService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.theprojecttool.messenger.activity.MainActivity;

public class MessengerAppInit {

    public static final String channelIdWechat = "MessengerChannel_1";

    public static void init(Context context){
        initPushService(context);
    }

    // 初始化推送服务
    static void initPushService(Context context){
        //开启调试日志
        LeanCloud.setLogLevel(LCLogger.Level.DEBUG);
        // 初始化应用信息
        // appId appKey serverUrl
        LeanCloud.initialize(context, "", "", "");
        System.out.println("初始化");

        // 订阅频道，当该频道消息到来的时候，打开对应的 Activity
        // 参数依次为：当前的 context、频道名称、回调对象的类
        PushService.subscribe(context, "public", MainActivity.class);
        PushService.subscribe(context, "private", MainActivity.class);
        PushService.subscribe(context, "protected", MainActivity.class);
        PushService.subscribe(context, channelIdWechat, MainActivity.class);

        //保存 Installation
        LCInstallation.getCurrentInstallation().saveInBackground().subscribe(new Observer<LCObject>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe 方法");
            }
            @Override
            public void onNext(LCObject avObject) {
                // 关联 installationId 到用户表等操作。
                String installationId = LCInstallation.getCurrentInstallation().getInstallationId();
                System.out.println("保存成功：" + installationId );

            }
            @Override
            public void onError(Throwable e) {
                System.out.println("保存失败，错误信息：" + e.getMessage());
            }
            @Override
            public void onComplete() {
                System.out.println("onComplete 方法");
            }
        });

        // 设置通知展示的默认 channel
        PushService.setDefaultChannelId(context, channelIdWechat);
        // 设置默认打开的 Activity
        PushService.setDefaultPushCallback(context, MainActivity.class);
        // create notification channel
        createNotificationChannel(context);
    }

    // 创建微信通知channel
    private static void createNotificationChannel(Context context) {
        CharSequence name = "微信推送";
        String description = "专门用来推送微信的channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelIdWechat, name, importance);
            channel.setDescription(description);
            channel.enableVibration(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
