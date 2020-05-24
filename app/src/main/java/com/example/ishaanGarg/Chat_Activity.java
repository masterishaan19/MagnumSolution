package com.example.ishaanGarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Chat_Activity extends AppCompatActivity{
    ListView listView;
    customAdapterDefinition arrayAdapter;
    ArrayList < chatMessageDefinition > arr;
    // First is message/ image id and second is the type
    ArrayList < Pair < String, Integer > > contentArr;
    customBroadcastReceiver customBroadcastReceiver;
    RelativeLayout relativeLayout;
    int positioned = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);

        // Code generation start from here!!
        getSupportActionBar().hide();
        final Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        setTitle(title);
        customBroadcastReceiver = new customBroadcastReceiver();
        arr = new ArrayList<>();
        assert title != null;
        if(title.equals("Friendly chat")) {
            friendly_chat();
        } else if(title.equals("Game chat")) {
            game_chat();
        } else if(title.equals("Job Chat")) {
            job_chat();
        } else if(title.equals("Trip Planning chat")) {
            trip_plan_chat();
        } else if(title.equals("Discussing hobbies chat")) {
            discuss_hobby_chat();
        }
        relativeLayout = findViewById(R.id.relativeLayout);
        listView = findViewById(R.id.chatListView);
        arrayAdapter = new customAdapterDefinition(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(arrayAdapter);
        // When the user clicks at the empty space of the application that is not in the list View arena
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!com.example.ishaanGarg.customBroadcastReceiver.isConnected && positioned < contentArr.size()){
                    String message = contentArr.get(positioned).first;
                    int type = contentArr.get(positioned).second;
                    if(type == 2 || type == 1)
                        arr.add(new chatMessageDefinition(message, type));
                    else
                        arr.add(new chatMessageDefinition(Integer.parseInt(message), type));
                    arrayAdapter.notifyDataSetChanged();
                    positioned++;
                    listView.setSelection(listView.getAdapter().getCount() - 1);
                }else if(com.example.ishaanGarg.customBroadcastReceiver.isConnected) {
                    Intent intent1 = new Intent(getApplicationContext(), Error_Connectivity_Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Chat Ends !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // When the user taps over the item in the listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!com.example.ishaanGarg.customBroadcastReceiver.isConnected && positioned < contentArr.size()){
                    String message = contentArr.get(positioned).first;
                    int type = contentArr.get(positioned).second;
                    if(type == 2 || type == 1)
                        arr.add(new chatMessageDefinition(message, type));
                    else
                        arr.add(new chatMessageDefinition(Integer.parseInt(message), type));
                    arrayAdapter.notifyDataSetChanged();
                    positioned++;
                    listView.setSelection(listView.getAdapter().getCount() -1);
                }else if(com.example.ishaanGarg.customBroadcastReceiver.isConnected) {
                    Intent intent1 = new Intent(getApplicationContext(), Error_Connectivity_Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Chat Ends !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    For the broadcast activity such that when the internet is turned off the error page fragment is being displayed to the user
//    This will start whenever the intent redirect to this page ie chat activity
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(customBroadcastReceiver, filter);
    }
//    After this activity finished this closes the real broadcast receiver for the activity.
//    The android cycle leads to next activity cycle.
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(customBroadcastReceiver);
    }
    /*Type definition:
     *   Type = 1 => Text by Sender/You/Left Side
     *   Type = 2 => Text by receiver/Me/Right Side
     *   Type = 3 => Image by sender
     *   Type = 4 => Image by receiver
     */
    public void friendly_chat(){
        contentArr = new ArrayList<>();
        contentArr.add(new Pair <> ("Hello Bro!", 1));
        contentArr.add(new Pair <> ("Hey Buddy !!", 2));
        contentArr.add(new Pair <> ("Long time no see!! Where were you busy?", 2));
        contentArr.add(new Pair <> ("Actually I was busy with some internship work for the upcoming summers", 1));
        contentArr.add(new Pair <> ("Well !! good", 2));
        contentArr.add(new Pair <> ("By the way how is everything going?", 2));
        contentArr.add(new Pair <> ("Its fine ", 1));
        contentArr.add(new Pair <> ("What about you? how are you doing?", 1));
        contentArr.add(new Pair <> ("Ahh !! well I am bored and lazy", 2));
        contentArr.add(new Pair <> ("Why bruh?", 1));
        contentArr.add(new Pair <> ("Bro !! We are in lock-down. I am fed up with everything, I have tried all things !! Nothing new to try for now", 2));
        contentArr.add(new Pair <> ("Bro have you tried for the summer internships??", 1));
        contentArr.add(new Pair <> ("Nope !! Its too much cumbersome and daunting task", 2));
        contentArr.add(new Pair <> ("You asshole! Internshala is the best platform", 1));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.internshala), 3)); // Image resource to be put into it !!
        contentArr.add(new Pair <> ("Just fill in your interest and search for the work from home internships", 1));
        contentArr.add(new Pair <> ("Related to your interest", 1));
        contentArr.add(new Pair <> ("You will also be able to learn new things and get incentives for your future", 1));
        contentArr.add(new Pair <> ("Ohhh !! Wow !! Is it like that easy?", 2));
        contentArr.add(new Pair <> ("Yes!! And I am using that platform for myself", 1));
        contentArr.add(new Pair <> ("Well !! I will look into it surely", 2));
        contentArr.add(new Pair <> ("Buddy missing you!! Want to party hard with you mates", 1));
        contentArr.add(new Pair <> ("Seriously mann those days were too much fun !! We used to party like crazy typical party🥳🥳 animals, those were days eating, playing, mocking each other ,and mimicry of raghav was awesome", 2));
        contentArr.add(new Pair <> ("Yupp buddyy !! Remember that incident when we all pulled his legs off at that tea stall !!", 1));
        contentArr.add(new Pair <> ("Yeah !! Yeah !!", 2));
        contentArr.add(new Pair <> ("Don't know yar when this corona thing will get over and life would be on roads again!", 2));
        contentArr.add(new Pair <> ("Hope so it ends fast!! I don't want to be graduated at home like my seniors did this year !!", 1));
        contentArr.add(new Pair <> ("They were too sad about that!! They could not properly say a good bye to each other at completion of graduation, could not enjoy the last few months over that lovely college", 1));
        contentArr.add(new Pair <> ("Hmm ! Sad !! hope so it ends fast!", 2));
        contentArr.add(new Pair <> ("Do you know before lock-down, my father bought a new phone and you would be amazed to know that its camrea quality is like best i have ever seen", 1));
        contentArr.add(new Pair <> ("Let me send you some of the fabulous pic taken by me!!", 1));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.photo), 3));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.photo2), 3));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.photo3), 3));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.photo4), 3));
        contentArr.add(new Pair <> ("Awesome man ! Too gooood", 2));
        contentArr.add(new Pair <> ("My talented boy😁😁", 2));
        contentArr.add(new Pair <> ("Well carry onn..", 2));
        contentArr.add(new Pair <> ("hey, gotta go , mumma is calling for the dinner", 1));
        contentArr.add(new Pair <> ("Oh sure!", 2));
        contentArr.add(new Pair <> ("See you soon buddy", 2));
        contentArr.add(new Pair <> ("Yup !! Bye for now", 1));
        contentArr.add(new Pair <> ("Bye ", 2));
    }
    public void game_chat(){
        contentArr = new ArrayList<>();
        contentArr.add(new Pair <> ("Hey brother, have you heard about this new Game, its getting over people's mind", 2));
        contentArr.add(new Pair <> ("Players Unknown Battle Ground", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.pubg), 4));
        contentArr.add(new Pair <> ("Its a good shooting game", 2));
        contentArr.add(new Pair <> ("Yeah !! I have heard about it from my friends", 1));
        contentArr.add(new Pair <> ("I have just started its download few moments ago", 1));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.download_pubg), 3));
        contentArr.add(new Pair <> ("It will take a while to get downloaded", 1));
        contentArr.add(new Pair <> ("Then I will tell you about it😁😁", 1));
        contentArr.add(new Pair <> ("Good mann !! We will then play together", 2));
        contentArr.add(new Pair <> ("Sure buddy !! Hey have you heard about that GTA Vice City V", 1));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.gta), 3));
        contentArr.add(new Pair <> ("Its rockstar games one more good launch", 1));
        contentArr.add(new Pair <> ("Have known it a while, But the game is too large to be saved and executed", 2));
        contentArr.add(new Pair <> ("Yeah !! Its compressed file size is 34GB", 1));
        contentArr.add(new Pair <> ("Do you knw after unpacking it expands and acquire 70 GB of the memory after installation", 1));
        contentArr.add(new Pair <> ("Are you serious?", 2));
        contentArr.add(new Pair <> ("Is it that much larger??", 2));
        contentArr.add(new Pair <> ("Yeah!! I myself couldn't believe it for the first time I saw it !! But yeah graphics and other features are good, So its okay with that quality", 1));
        contentArr.add(new Pair <> ("You know that a game on steam is available with name pacify", 1));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.pacify), 3));
        contentArr.add(new Pair <> ("It seems horrifying..", 2));
        contentArr.add(new Pair <> ("I have seen it in a youtube stream's where that streamer plays that game", 1));
        contentArr.add(new Pair <> ("It a type of horror game !! I liked it", 1));
        contentArr.add(new Pair <> ("I don't know about it !! Well I don't watch streams", 2));
        contentArr.add(new Pair <> ("Just tell me about the game?", 2));
        contentArr.add(new Pair <> ("Well you know the pacify story begins like an other horror film", 1));
        contentArr.add(new Pair <> ("With those ugly looking dolls", 1));
        contentArr.add(new Pair <> ("Why they have to always make dolls for all this, Can't they use anything else😂😂", 2));
        contentArr.add(new Pair <> ("Well don't know why this is always in their mind", 1));
        contentArr.add(new Pair <> ("In that game we are tasked to find the keys for different rooms and the rooms contains the various dolls", 1));
        contentArr.add(new Pair <> ("There is that ghost lady who wanders and after particular interval she would haunt that place and if she caught somebody they would become the doll", 1));
        contentArr.add(new Pair <> ("Its very interesting !! You have to prevent yourself from being caught and also you have to find all those dolls and burn them entirely in the etic in the  basement of the building", 1));
        contentArr.add(new Pair <> ("Its too good to watch !!", 1));
        contentArr.add(new Pair <> ("Wow! it sounds too much interesting !", 2));
        contentArr.add(new Pair <> ("Well if you download it then keep setup with you i would be taking it from you ", 2));
        contentArr.add(new Pair <> ("Actually Buddy its over steam so it cannot be shared like this", 1));
        contentArr.add(new Pair <> ("It have to be personally be purchased for playing", 1));
        contentArr.add(new Pair <> ("Oops that's the thing !! Well its ok then !! ", 2));
        contentArr.add(new Pair <> ("If you are going to buy this tell me maybe I will be buying it with you", 2));
        contentArr.add(new Pair <> ("Yeah ! Sure buddy, but now I gotta go and play some match for COD with my mates", 1));
        contentArr.add(new Pair <> ("See you later, Bye", 1));
        contentArr.add(new Pair <> ("My Pubg download has also completed, I am also going to play it", 2));
        contentArr.add(new Pair <> ("See you soon too, Byee😁", 2));
    }
    public void job_chat(){
        contentArr = new ArrayList<>();
        contentArr.add(new Pair <> ("Good Morning, John. I am Mike.", 1));
        contentArr.add(new Pair <> ("Good Morning.", 2));
        contentArr.add(new Pair <> ("How are you doing?", 1));
        contentArr.add(new Pair <> ("I am doing fine. Thank you.", 2));
        contentArr.add(new Pair <> ("How was the traffic coming over here?", 1));
        contentArr.add(new Pair <> ("I am so glad that the traffic was light this morning.", 2));
        contentArr.add(new Pair <> ("That is good. John, let’s start the interview. Are you ready?", 1));
        contentArr.add(new Pair <> ("Yes, I am.", 2));
        contentArr.add(new Pair <> ("I am the Finance Department Manager. As you know there is an open position in my department, and I need to fill this position as soon as possible.", 1));
        contentArr.add(new Pair <> ("Please, tell me a little bit about the position.", 2));
        contentArr.add(new Pair <> ("It is an entry-level position. The new employee will have to work closely with the Accounting department. He will also have to deal with the bank on a daily basis.", 1));
        contentArr.add(new Pair <> ("What type of qualifications do you require?", 2));
        contentArr.add(new Pair <> ("I require a four-year college degree in Finance. Some working experience would be helpful.", 1));
        contentArr.add(new Pair <> ("How much year experience are you looking for?", 2));
        contentArr.add(new Pair <> ("Doing office work is good. However, since this is an entry-level position, I do not require a lot of experience a work experience of 1 year would be okay for the position. I am willing to train the new person.", 1));
        contentArr.add(new Pair <> ("That is great! Well then I am on of the suitable candidate for the post", 2));
        contentArr.add(new Pair <> ("John, tell me a little bit about yourself.", 1));
        contentArr.add(new Pair <> ("I was a student at West Coast University, and I just graduated with a Bachelor degree in Finance. I have been working part-time as a payroll clerk for the last two years.", 2));
        contentArr.add(new Pair <> ("What are you looking for in a job?", 1));
        contentArr.add(new Pair <> ("The job should help me see what Finance is all about. I have learned a lot of Finance theories at school, and now it is time for me to put them into practice.", 2));
        contentArr.add(new Pair <> ("Anything else?", 1));
        contentArr.add(new Pair <> ("I also hope that it will help me grow in my field.", 2));
        contentArr.add(new Pair <> ("What are your strengths? Why should I hire you?", 1));
        contentArr.add(new Pair <> ("I am a hard-working person and a fast learner. I am very eager to learn, and I get along fine with people.", 2));
        contentArr.add(new Pair <> ("OK. Now, let me ask you a few quick questions. You do not mind working long hours, do you?", 1));
        contentArr.add(new Pair <> ("No, I do not.", 2));
        contentArr.add(new Pair <> ("Can you handle pressure?", 1));
        contentArr.add(new Pair <> ("Yes, I can. When I was going to school, I took quite a few courses each semester while working at least twenty hours every week. And, I handled that situation very well.", 2));
        contentArr.add(new Pair <> ("Do you still have any questions for me?", 1));
        contentArr.add(new Pair <> ("No, I think I have a pretty good understanding of the job. I believe that I can handle it with ease, and I hope to have the opportunity to work for you.", 2));
        contentArr.add(new Pair <> ("John, nice meeting you. Thank you for coming. You will be notified about your result for selection over the mail to you.", 1));
        contentArr.add(new Pair <> ("Nice meeting you too. Thank you for seeing me.", 2));
    }
    public void trip_plan_chat(){
        contentArr = new ArrayList<>();
        contentArr.add(new Pair <> ("Hello buddy!", 1));
        contentArr.add(new Pair <> ("Heyaa !!", 2));
        contentArr.add(new Pair <> ("As you know that winter vacations are about to end and then we all will be busy with our daily college life", 1));
        contentArr.add(new Pair <> ("So I am just planning that shouldn't we make a good trip some where so as to enjoy with each other before starting of our next semester", 1));
        contentArr.add(new Pair <> ("Bro !! you just nailed it !! I was thinking same and was about to ask to you about that thing", 2));
        contentArr.add(new Pair <> ("I was literally doing research over the places best for this time for hiking or trekking experience", 1));
        contentArr.add(new Pair <> ("Ohh !! Well then lets make a plan !!", 2));
        contentArr.add(new Pair <> ("I was thinking about the triund trek its one of difficult treks in India and its been rated for adventurous people", 1));
        contentArr.add(new Pair <> ("Ohh !! Well I have done that trek the last year with my school mates", 2));
        contentArr.add(new Pair <> ("Its an interesting place and have a good and a steep trek", 2));
        contentArr.add(new Pair <> ("Its view is too much adorable and peaceful", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.triund), 4));
        contentArr.add(new Pair <> ("It has a good trek.", 2));
        contentArr.add(new Pair <> ("How long is its trek and what about its difficulty?", 1));
        contentArr.add(new Pair <> ("Well the trek is almost 7Km long", 2));
        contentArr.add(new Pair <> ("But it difficulty level is variant", 2));
        contentArr.add(new Pair <> ("Sometimes trekking elevation is 20-30 degree, while sometimes it rise to 40-50 degree",2 ));
        contentArr.add(new Pair <> ("Its a long trek and it took us about 4 hours to completely trek through it", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.trek), 4));
        contentArr.add(new Pair <> ("Also you know at the height everything is too much costly, so we have to carry items with us", 2));
        contentArr.add(new Pair <> ("Umm !! I thing it should be out of budget because it may require money", 1));
        contentArr.add(new Pair <> ("Nope buddy !! We made that trip in 3.5K previous year and that too very comfortably", 2));
        contentArr.add(new Pair <> ("Also we never made any sort of shortcutting during the trip", 2));
        contentArr.add(new Pair <> ("Are you serious will it be covered in that much going staying and coming back", 1));
        contentArr.add(new Pair <> ("We stayed for 2 Nights and 3 Days there so we just made the best deals out of everything and enjoyed too much out there", 2));
        contentArr.add(new Pair <> ("Wooww man !! you made that much !! bravoo ", 1));
        contentArr.add(new Pair <> ("Then we are set for going there !! We also have a trip adviser with us😂", 1));
        contentArr.add(new Pair <> ("Also you know that there is a fall as bhatta fall we bathed in that in icy chilled water", 2));
        contentArr.add(new Pair <> ("It was so chilling but we enjoyed it too much in the pure water", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.bhat_valley), 4));
        contentArr.add(new Pair <> ("This is the view for the the part fall", 2));
        contentArr.add(new Pair <> ("Actually I have to look and find the place in pics where we bathed !! I will search for it", 2));
        contentArr.add(new Pair <> ("Okay ! Well I am interested to go to this place", 1));
        contentArr.add(new Pair <> ("Lets ask our buddies who else want to go", 1));
        contentArr.add(new Pair <> ("And then we would finalise our tickets", 1));
        contentArr.add(new Pair <> ("and the final plan for the trip", 1));
        contentArr.add(new Pair <> ("Yupp sure !! I'm already in 😂😂", 2));
        contentArr.add(new Pair <> ("Yeah😂Yeah😂 !! Well Talk to you after asking other friends 😁", 1));
        contentArr.add(new Pair <> ("Okay !! Bye", 2));
    }
    public void discuss_hobby_chat(){
        contentArr = new ArrayList<>();
        contentArr.add(new Pair <> ("Hello Sis", 1));
        contentArr.add(new Pair <> ("Hello", 2));
        contentArr.add(new Pair <> ("How are you", 2));
        contentArr.add(new Pair <> ("I'm fine what about you?", 1));
        contentArr.add(new Pair <> ("I am awesome !!", 2));
        contentArr.add(new Pair <> ("Good ! What were you doing?", 1));
        contentArr.add(new Pair <> ("Nothing much just engaged in few silly things", 2));
        contentArr.add(new Pair <> ("Well, still i want to know what my lil sister was doing", 1));
        contentArr.add(new Pair <> ("Just practicing some calligraphy", 2));
        contentArr.add(new Pair <> ("Ohh good!! How did you pick up calligraphy?", 1));
        contentArr.add(new Pair <> ("Actually i was discovering various interest about myself, so I was trying new and new things", 2));
        contentArr.add(new Pair <> ("Ohh good !!! So, tell me what are you interested in ?", 1));
        contentArr.add(new Pair <> ("I have learned that I am a good artist, a good calligrapher as well as a good dancer", 2));
        contentArr.add(new Pair <> ("So much in that little soul, amazing well keep growing", 1));
        contentArr.add(new Pair <> ("Send me few pictures for your drawing and your calligraphy", 1));
        contentArr.add(new Pair <> ("Sure, wait I am sending them", 2));
        contentArr.add(new Pair <> ("Calligraphy...", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.callligraphy), 4));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.calli), 4));
        contentArr.add(new Pair <> ("Drawing...", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.skull), 4));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.girl), 4));
        contentArr.add(new Pair <> ("And one of my favourite images that took me a week to complete it", 2));
        contentArr.add(new Pair <> (String.valueOf(R.drawable.goku), 4));
        contentArr.add(new Pair <> ("Are you kidding me !! Did you made that Goku's image!!", 1));
        contentArr.add(new Pair <> ("That's so perfect and flawless and so smooth drawing", 1));
        contentArr.add(new Pair <> ("Well done, so much proud of you!!", 1));
        contentArr.add(new Pair <> ("So you are not great you are just awesome at your drawing skills", 1));
        contentArr.add(new Pair <> ("Thanks, Its just time pass", 2));
        contentArr.add(new Pair <> ("What you like to do in you free time?", 2));
        contentArr.add(new Pair <> ("Well I am very fond of playing video games🎮🎮 and watching movies and sleeping😴😴", 1));
        contentArr.add(new Pair <> ("Apart from that lazy things I also like playing badminton and football", 1));
        contentArr.add(new Pair <> ("And talking about swimming I can do it for my whole life, I am so much attracted to it" , 1));
        contentArr.add(new Pair <> ("Its like a kind of bliss for me", 1));
        contentArr.add(new Pair <> ("So you are much like a fish😂😂" , 2));
        contentArr.add(new Pair <> ("😂😂Well you can say so !!" , 1));
        contentArr.add(new Pair <> ("I want to meet you" , 1));
        contentArr.add(new Pair <> ("Me too !!" , 2));
        contentArr.add(new Pair <> ("So I will be come to you this week's friday evening and then we will have a lot of fun during weekends" , 1));
        contentArr.add(new Pair <> ("Yeahh !! suree" , 2));
        contentArr.add(new Pair <> ("Waiting for the time" , 2));
        contentArr.add(new Pair <> ("Yupp !! Till then take care sis" , 1));
        contentArr.add(new Pair <> ("You too!!" , 2));
        contentArr.add(new Pair <> ("Love you, Bye" , 1));
    }
}



