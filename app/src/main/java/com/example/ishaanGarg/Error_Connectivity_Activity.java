package com.example.ishaanGarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Error_Connectivity_Activity extends AppCompatActivity {
//Activity created when the user turn off the internet connectivity
//Refresh check if forward process could be initializes or connectivity is still missing
    customBroadcastReceiver customBroadcastReceiver = new customBroadcastReceiver();
    public void refresh(View view){
        if(!customBroadcastReceiver.isConnected)
            finish();
        else
            Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error__connectivity_);
        setTitle("Error Page");
    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(customBroadcastReceiver, filter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(customBroadcastReceiver);
    }
    @Override
    public void onBackPressed() {
        // This has to be override so as to prevent the user going back
        // to working fragment in case of internet connectivity not maintained
    }
}
