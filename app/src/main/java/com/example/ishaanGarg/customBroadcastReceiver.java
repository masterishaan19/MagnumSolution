package com.example.ishaanGarg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class customBroadcastReceiver extends BroadcastReceiver {
    static boolean isConnected;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            isConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            Log.i("BroadCastActivity", context.getClass().getName());
            if(isConnected){
                if(context.getClass().getName().equals("com.example.ishaanGarg.MainActivity") || context.getClass().getName().equals("com.example.ishaanGarg.Chat_Activity")){
                    Intent intent1 = new Intent(context.getApplicationContext(), Error_Connectivity_Activity.class);
                    context.startActivity(intent1);
                }
            }
        }
    }
}
