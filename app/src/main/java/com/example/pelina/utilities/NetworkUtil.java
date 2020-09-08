package com.example.pelina.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isConnected(Context context) {
        if (context == null)
            return false;
        ConnectivityManager manager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (manager != null) {
            NetworkInfo ni = manager.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }
        return false;
    }
}
