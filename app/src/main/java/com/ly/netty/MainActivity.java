package com.ly.netty;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yizhong.push.client.NettyClientBootstrap;
import com.yizhong.push.helper.BroadcastHelper;


public class MainActivity extends AppCompatActivity {

    private MyReceiver mMyReceiver;
    String text;
    private LocalBroadcastManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = LocalBroadcastManager.getInstance(this);

        mMyReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadcastHelper.ad_data_yizhong);
        manager.registerReceiver(mMyReceiver, filter);
        text = getResources().getString(R.string.app_text);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NettyClientBootstrap.init(MainActivity.this, 9999, "192.168.18.28", "ANDROID", "HUAWEI-001");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void btnClick(View view) {
        BroadcastHelper.send(this, text);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("data");
            Log.e("MyReceiver",
                    "onReceive(MyReceiver.java:55)" + stringExtra);
            Log.e("MyReceiver",
                    "onReceive(MyReceiver.java:65)" + text);
//            if (TextUtils.equals(stringExtra, text)) {
            Log.e("MyReceiver",
                    "onReceive(MyReceiver.java:68)" + "123123");
            startNotification(stringExtra);
//            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(mMyReceiver);
    }

    /**
     * 到达登录页面后,重置通知栏点击跳转
     */
    private void startNotification(String content) {
        Intent notificationIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("测试消息")
                        .setContentText(content)
                        .setContentIntent(pendingIntent);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = mBuilder.build();
        notification.sound = uri;
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(10000, notification);
    }
}
