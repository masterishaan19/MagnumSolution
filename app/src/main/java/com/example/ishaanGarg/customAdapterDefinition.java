package com.example.ishaanGarg;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class customAdapterDefinition extends ArrayAdapter < chatMessageDefinition > {

    private Context myContext;
    private ArrayList< chatMessageDefinition > arr;
    public customAdapterDefinition(@NonNull Context context, int resource, @NonNull List<chatMessageDefinition> objects) {
        super(context, resource, objects);
        arr = (ArrayList<chatMessageDefinition>) objects;
        myContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        chatMessageDefinition chat = arr.get(position);
        if(chat.getType() == 1){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.chat_left, parent, false);
        }else if(chat.getType() == 2){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.chat_right, parent, false);
        }else if(chat.getType() == 3){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.chat_left, parent, false);
        }else if(chat.getType() == 4){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.chat_right, parent, false);
        }
        if(chat.getType() == 1){
            TextView show_message = listItem.findViewById(R.id.show_message);
            show_message.setText(chat.getMessage());
        }else if(chat.getType() == 2){
            TextView show_message = listItem.findViewById(R.id.show_message);
            show_message.setText(chat.getMessage());
        }else if(chat.getType() == 3){
            Log.i("Url", chat.getMessage() + chat.getImageId());
            ImageView imageView = listItem.findViewById(R.id.show_image);
            imageView.setImageResource(chat.getImageId());
        }else if(chat.getType() == 4){
            Log.i("Url", chat.getMessage() + chat.getImageId());
            ImageView imageView = listItem.findViewById(R.id.show_image);
            imageView.setImageResource(chat.getImageId());
        }
        return listItem;
    }
}
