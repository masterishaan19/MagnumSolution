package com.example.ishaanGarg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter < String > arrayAdapter;
    ArrayList < String > arr;
    customBroadcastReceiver customBroadcastReceiver = new customBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Declaration starts
        setTitle("Chat options");
        customBroadcastReceiver = new customBroadcastReceiver();
        listView = findViewById(R.id.listView); // list view
        arr = new ArrayList<>(); // array-List
        arr.add("Friendly chat");
        arr.add("Game chat");
        arr.add("Job Chat");
        arr.add("Trip Planning chat");
        arr.add("Discussing hobbies chat");
        // Added to array
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr); // Array Adapter for the listView
        listView.setAdapter(arrayAdapter); // setting the adapter for the listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // function call back for the
            @Override                                                            // clicking of the list item objects
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_2 = new Intent(getApplicationContext(), Chat_Activity.class); // starting the chat or story
                intent_2.putExtra("title", arr.get(position));
                startActivity(intent_2);
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.help){
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            intent.putExtra("title", "Help");
            intent.putExtra("message", "After Selection of your preferable chat\nClicking anywhere in the app in chat would reveal a new message in the chat until all chats are being shown");
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.contact){
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            intent.putExtra("title", "Contact Us");
            intent.putExtra("message", "Author: Master19\nName: Ishaan Garg\nEmail: ishaangarg192000@gmail.com");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
