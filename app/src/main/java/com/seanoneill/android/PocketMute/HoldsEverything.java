
package com.seanoneill.android.PocketMute;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public abstract class HoldsEverything extends ListActivity 
{
	 private DatabaseStore getDatabaseInfo;
	 private MainActivity getDatabaseInfo2;


protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
	getDatabaseInfo = new DatabaseStore(this);
	getDatabaseInfo2 = new MainActivity();
    //setContentView(R.layout.activity_default);    	
}
    
protected void onDestroy ()
{
   super.onDestroy ();
}

protected void onPause ()
{
   super.onPause ();
}


protected void onRestart ()
{
   super.onRestart ();
}


protected void onResume ()
{
	 getDatabaseInfo.open(); 
   super.onResume ();
}


protected void onStart ()
{
   super.onStart ();
}

protected void onStop ()
{
   super.onStop ();
}


public void onClickHome (View v)
{
    goHome (this);
}

public void onClickSearch (View v)
{
    goHome (this);
}

public void onClickFeature (View v)
{
    int id = v.getId ();
    switch (id) {
      case R.id.home_btn_feature1 :
           startActivity (new Intent(getApplicationContext(), AddTimeEntry.class));
           break;
           
     case R.id.home_btn_feature2:
    	 
    	 Cursor MainPageCursor1 = getDatabaseInfo.fetchAllQuciMute();
    	 if(MainPageCursor1!=null && MainPageCursor1.getCount()>0){
    		 cancelQuickMute();
    	 
    	 }else{ 
    	 TimePickerDialog();
    	 }
          break;
 
      default: 
    	   break;
    }
}

public void TimePickerDialog(){
	final Dialog dialog = new Dialog(this);
	Calendar now = Calendar.getInstance();

	final Calendar tmp = (Calendar) now.clone();
	
	
	dialog.setContentView(R.layout.quick_mute_dialog);
    final View timePicker1 = dialog.findViewById(R.id.timePicker1);
    
    ((TimePicker) timePicker1).setIs24HourView(true);
	
    //set 00:00 in timepicker so user can pick hours and mins its want for its quick mute timer
    ((TimePicker) timePicker1).setCurrentHour(00);
	 ((TimePicker) timePicker1).setCurrentMinute(00);
	dialog.setTitle("Timer");
	
	//Allow the dialog to be cancelable
	dialog.setCancelable(true);
	// Here we add functionality to our dialog box's content. In this example it's the two buttons
	//reference the button
	Button okButton = (Button) dialog.findViewById(R.id.dialog_ok);
	//Create a click listener
	okButton.setOnClickListener(new OnClickListener() {
	     //override the onClick function to do something
	     public void onClick(View v) {
	    		         	 
	    Integer go = ((TimePicker) timePicker1).getCurrentHour();
	    	   Integer go1 = ((TimePicker) timePicker1).getCurrentMinute();
	    	 
	    	   Calendar currenttime = Calendar.getInstance();
	    	
	    	   String got = currenttime + "";

	    int coverthourtomill = go * 60;
	      int millsecconds = 3600000 * coverthourtomill;
	      
	      // get mill seccond of mins
	      int covertmintomill = go1 * 60000;
	
	      long totalMillTime = millsecconds + covertmintomill;
	      long milseccondcurrent = totalMillTime + System.currentTimeMillis();
	      String totalMillTimeString = milseccondcurrent + "";
	    	    String goo = go + "";
	    	    Calendar calInstance =Calendar.getInstance();
	    	    				
	    	    // adds mute time that user picked on to current time and then diaplys it to the user to they know when the mute finisheds
	    	    calInstance.add(Calendar.MINUTE,go1);
				calInstance.add(Calendar.HOUR,go);
				
				// 12 hour clock time 
				 final String TIME_FORMAT_12 = "h:mm a";
				  SimpleDateFormat timeFormat12 = new SimpleDateFormat(TIME_FORMAT_12);
				   String saving12ToDatabase = timeFormat12.format(calInstance.getTime());
				   
				   final String TIME_FORMAT_24 = "HH:mm";
				   SimpleDateFormat timeFormat24 = new SimpleDateFormat(TIME_FORMAT_24);
				   String saving24ToDatabase = timeFormat24.format(calInstance.getTime());
	    	    
	   
	    	   
	    	 //  setalarm(20122012, milseccondcurrent, saving24ToDatabase);
	  getDatabaseInfo.createQuickMuteEntry(totalMillTimeString , "202034343", saving24ToDatabase, saving12ToDatabase);
	  getDatabaseInfo2.godofdf();
	    	   // finish();
	    	   dialog.dismiss();
	     }
	});

	//reference the button
	Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancel);
	//Create a click listener
	cancelButton.setOnClickListener(new OnClickListener() {
	     //override the onClick function to do something
	     public void onClick(View v) {
          //Close the dialog</span>
		          dialog.dismiss();
	    }
	});
	//finally show the dialog
	
	dialog.show();
}

public void cancelQuickMute(){
	
	  Cursor reminder = getDatabaseInfo.getQuickMute(202034343);
      startManagingCursor(reminder);
    
    	String StartTimeUpdate = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.QUICK_TIME_12));
      if (DateFormat.is24HourFormat(this)){
         StartTimeUpdate = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.QUICK_TIME_24));
      }
    	  
      
  	final long  endmutetime = reminder.getLong(reminder.getColumnIndexOrThrow(DatabaseStore.END_TIME_QUICK));
  	
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("Stop Quick Mute?");
	builder.setMessage("Finishing Time: "  +StartTimeUpdate )
	   .setCancelable(false)
	   .setPositiveButton("Stop Mute", new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog, int id) {
	    	   getDatabaseInfo.deleteQuickMute(202034343);
	    	   deleteAlarm(endmutetime);//============================================================================================if alarm does not delete its because endmutetime is string not long
	    	   dialog.dismiss();
	       }
	   })
	   .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog, int id) {
	            dialog.dismiss();
	       }
	   });
	AlertDialog alert = builder.create();
	alert.show();
}

public void setalarm(long setid, long getmill, String vibrateQuickMute2){
	  new SetAndroidAlarm(this).QuickMute(setid, getmill, vibrateQuickMute2); 
	  
}
public void deleteAlarm(long getmill1){
	  new SetAndroidAlarm(this).overrightDeleteQuickMute(getmill1);
	  
}


public void goHome(Context context) 
{
    final Intent intent = new Intent(context, MainActivity.class);
    intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity (intent);
}


public void setTitleFromActivityLabel (int textViewId)
{
    TextView tv = (TextView) findViewById (textViewId);
    if (tv != null) tv.setText (getTitle ());
} // end setTitleText


public void toast (String msg)
{
   
} // end toast


public void trace (String msg) 
{
    Log.d("Mute my phone", msg);
    toast (msg);
}

} 
