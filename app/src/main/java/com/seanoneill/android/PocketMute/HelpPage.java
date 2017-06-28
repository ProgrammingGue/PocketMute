package com.seanoneill.android.PocketMute;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flurry.android.FlurryAgent;

//Help Page
public class HelpPage extends Activity 
{

protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView (R.layout.help_page);
  //  setTitleFromActivityLabel (R.id.title_text);
    

    Button backtoMainScreen = (Button) findViewById(R.id.cancelbutton);

  //Listening to button event
  backtoMainScreen.setOnClickListener(new View.OnClickListener() {

      public void onClick(View arg0) {
          //Starting a new Intent
         finish();
        }
           });

                 }

public void onStart()
{
   super.onStart();
   FlurryAgent.onStartSession(this, "NDXH9YVY3Q586B9N6PVZ");
   // your code
}

public void onStop()
{
   super.onStop();
   FlurryAgent.onEndSession(this);
   // your code
}

}


