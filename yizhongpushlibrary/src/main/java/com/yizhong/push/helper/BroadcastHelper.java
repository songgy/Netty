package com.yizhong.push.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by admin on 2018/4/4.
 */

public class BroadcastHelper {

    public static String ad_data_yizhong = "AD_DATA_YIZHONG";

    public static void  send(Context context, String data) {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent();
        intent.putExtra("data", data);
        intent.setAction(ad_data_yizhong);
        manager.sendBroadcast(intent);
    }
}
