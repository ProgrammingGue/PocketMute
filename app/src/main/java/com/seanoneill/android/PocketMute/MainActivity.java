package com.seanoneill.android.PocketMute;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;

public class MainActivity extends ListActivity 
{
	 private Calendar AutoSilentCalendar;	
	 private DatabaseStore databaseHelper;
   	 private static final int ACTIVITY_CREATE=0;
     private static final int ACTIVITY_EDIT=1;
	 String VibrateQuickMute =   "Off";
     CheckBox ch;
     
     //****************************************************************************************************************************************************************************
                                                 //  Have a goal and want it bad enough, The sky is your limit 
     // ***************************************************************************************************************************************************************************
     
protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    
    databaseHelper = new DatabaseStore(this);
  
    databaseHelper.open();
    ListViewData();
    registerForContextMenu(getListView());
    
  ///////////////*********************************************************************************************************CODE FOR TRIAL VERSION ONLY ***** 1
    if (isFirstTime()) {
    	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
       	builder.setTitle("Reddit :)");
       	builder.setMessage("Thanks everyone for testing my app(again).")
       	   .setCancelable(false)
       	   .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
       	       public void onClick(DialogInterface dialog, int id) {
       	    
       	    	   dialog.dismiss();
       	       }
       	   })
       	   .setNegativeButton("", new DialogInterface.OnClickListener() {
       	       public void onClick(DialogInterface dialog, int id) {
       	            dialog.dismiss();
       	       }
       	   });
       	AlertDialog alert = builder.create();
       	alert.show();
    }
    
    ///////////////*********************************************************************************************************CODE FOR TRIAL VERSION ONLY **** 1
    
}

//////////////*********************************************************************************************************CODE FOR TRIAL VERSION ONLY **** 2

//Is to check if the app has been running allready if so show noraml main screen, if its first time running show dialog asking to buy app
private boolean isFirstTime()
{
 
    SharedPreferences settings = getSharedPreferences("MyPreferencesFileName", 0);
    boolean ranBefore = settings.getBoolean("RanBefore", false);
    if (!ranBefore) {
        // first time
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("RanBefore", true);
        editor.commit();
        
        
    }
    return !ranBefore;
}

//////////////*********************************************************************************************************CODE FOR TRIAL VERSION ONLY **** 2

public void ListViewData(){

	 String[] empty;
	 
	 if (DateFormat.is24HourFormat(this)){
		 Cursor MainPageCursor1 = databaseHelper.fetchAllReminders();
		 startManagingCursor(MainPageCursor1);
	 empty = new String[] { DatabaseStore.MUTE_TITLE,  DatabaseStore.START_KEY_TIME, DatabaseStore.END_KEY_TIME, DatabaseStore.COVERT_DAYS, DatabaseStore.ALARM_MODE }; 
	 
	   int[] notempty = new int[]{R.id.text1,R.id.text2, R.id.text4,  R.id.text5, R.id.text6};
	    SimpleCursorAdapter mainPageList = new SimpleCursorAdapter(this, R.layout.editinfo_row, MainPageCursor1, empty, notempty);
      setListAdapter(mainPageList); 
	 }else{
		 empty = new String[] { DatabaseStore.MUTE_TITLE,  DatabaseStore.START_TIME_12_HOUR, DatabaseStore.END_TIME_12_HOUR, DatabaseStore.COVERT_DAYS, DatabaseStore.ALARM_ON_NOW }; 
		 Cursor MainPageCursor11 = databaseHelper.fetchAllMutes12Hour();
		 
		   int[] notempty = new int[]{R.id.text1,R.id.text2, R.id.text4,  R.id.text5, R.id.text6};
		    SimpleCursorAdapter mainPageList = new SimpleCursorAdapter(this, R.layout.editinfo_row, MainPageCursor11, empty, notempty);
	       setListAdapter(mainPageList); 
	 }
	 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	boolean istrue = false;
	  
	  NotificationManager notificationManager = (NotificationManager) 
				getSystemService(NOTIFICATION_SERVICE);
	    
	    boolean go11 = databaseHelper.goggo();

		  Cursor MainPageCursor1 = databaseHelper.fetchAllQuciMute();
		   if(MainPageCursor1!=null && MainPageCursor1.getCount()>0){
			    TextView buttonview = (TextView)findViewById(R.id.home_btn_feature2);
			    buttonview.setText("Stop Quick Mute");
			    
			    //buttonview.setbacground(R.drawable.icon);
		   }
		  
		  if(go11 == true || MainPageCursor1.getCount()>0 ){
			
			  
			// Create Notifcation
			  Notification notification = new Notification(R.drawable.icon,
					  null , 0);
	  
			  // Cancel the notification after its selected
			 

			  // Specify the called Activity
			  Intent intent = new Intent(this, MainActivity.class);
			  
			  PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
			  notification.flags = Notification.FLAG_ONGOING_EVENT;

			  notification.setLatestEventInfo(this, "Pocket Mute - Active Mute(s)",
			  	"Touch to change mute(s)", activity);
			  notificationManager.notify(0, notification);
					   
		  }else{
			  notificationManager.cancel(0);
		  }
		  	  
	 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
              }

@Override
public void onCreateContextMenu(ContextMenu menu, View DeleteMenu, ContextMenuInfo menuInfo){
  super.onCreateContextMenu(menu, DeleteMenu, menuInfo);
  MenuInflater deleteMenu = getMenuInflater();
	   deleteMenu.inflate(R.menu.deleteentrymenu, menu);
  }

@Override
public boolean onCreateOptionsMenu(Menu menu){
	super.onCreateOptionsMenu(menu);
	MenuInflater BottomMenu = getMenuInflater();
	BottomMenu.inflate(R.menu.add_menu, menu);
	Cursor bottomMenuCursor = databaseHelper.getDatabaseEntries();
	startManagingCursor(bottomMenuCursor);

   return true;
   }
//**************************************************************************8 put htis back in later its for update******************************
protected void onListItemClick(ListView l, View v, int position, long id){
	super.onListItemClick(l, v, position, id);
	Intent i = new Intent(this, AddTimeEntry.class); /// fix this back to wha tit was
    i.putExtra(DatabaseStore.kEY_ROWID, id);
     startActivityForResult(i, ACTIVITY_EDIT); 
	
}
public boolean onMenuItemSelected(int id, MenuItem item){
	 Cursor BottomMenuCursor = databaseHelper.getDatabaseEntries();
	 startManagingCursor(BottomMenuCursor);
    int go = BottomMenuCursor.getCount();
	    switch(item.getItemId()){
	        case R.id.bottom_menu:
		
  		createNewEntry();
	}
	return super.onMenuItemSelected(id, item); //////// seems to be a problem here
	}

private void createNewEntry(){
	Intent i = new Intent(this, AddTimeEntry.class);
	startActivityForResult(i, ACTIVITY_CREATE);
   }	

 @Override
  public boolean onContextItemSelected(MenuItem item){
   	switch(item.getItemId()){
	        case R.id.delete_time:
  	
       AdapterContextMenuInfo DatabaseInfo = (AdapterContextMenuInfo) item.getMenuInfo();
       AutoSilentCalendar = Calendar.getInstance();
     databaseHelper.deleteEntry(DatabaseInfo.id);
     long goo = DatabaseInfo.id;
  /***************************************************************************************************************************************   
     Cursor reminder1 = databaseHelper.fetchSilent(goo);
     String StartTimeUpdate = reminder1.getString(reminder1.getColumnIndexOrThrow(DatabaseStore.START_KEY_TIME)); 
		String endTimeUpdate = reminder1.getString(reminder1.getColumnIndexOrThrow(DatabaseStore.END_TIME_QUICK)); 
		String getStates = reminder1.getString(reminder1.getColumnIndexOrThrow(DatabaseStore.ALARM_MODE)); 
     
    if(getStates == "On"){
    
    }
		*/
     
     
   
   String week =   DatabaseStore.KEY_STATE;
	  
   Cursor reminder = databaseHelper.getmax();

   String place = reminder.getString(0);
   
   long covertRow = Long.parseLong(place);
     new SetAndroidAlarm(this).overrightDelete(goo, AutoSilentCalendar);  
     ListViewData();
	    }
  
return super.onContextItemSelected(item);

 }

protected void onDestroy ()
{
   super.onDestroy ();
   SharedPreferences settings = getSharedPreferences("MyPreferencesFileName", 0);
   SharedPreferences.Editor preferencesEditor = settings.edit();
   preferencesEditor.clear();
   preferencesEditor.commit();            

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
   super.onResume ();
   ListViewData();
}

protected void onStart ()
{
   super.onStart ();
   
   // This is for me to check on whos using this app and how much and if there is any errors --- that is all
      FlurryAgent.onStartSession(this, "NDXH9YVY3Q586B9N6PVZ");
      
}

protected void onStop ()
{
   super.onStop ();
   FlurryAgent.onEndSession(this);
}


public void click(View view) {
    Intent i = new Intent( MainActivity.this, HelpPage.class);
    startActivity(i);
}

public void godofdf ()
{
    ListViewData();

}

public void onClickFeature (View v)
{
    int id = v.getId ();
    switch (id) {
      case R.id.home_btn_feature1 :
           startActivity (new Intent(getApplicationContext(), AddTimeEntry.class));
           break;
           
     case R.id.home_btn_feature2:
    	 
    	 Cursor MainPageCursor1 = databaseHelper.fetchAllQuciMute();
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
	  VibrateQuickMute =   "Off";	
	final Calendar tmp = (Calendar) now.clone();
	
	
	dialog.setContentView(R.layout.quick_mute_dialog);
    final View timePicker1 = dialog.findViewById(R.id.timePicker1);
    
    ((TimePicker) timePicker1).setIs24HourView(true);
 //   ((TimePicker) timePicker1).setOnTimeChangedListener(StartTimeChangedListener);
    ((TimePicker) timePicker1).setIs24HourView(true);
    //set 00:00 in timepicker so user can pick hours and mins its want for its quick mute timer
    ((TimePicker) timePicker1).setCurrentHour(00);
	 ((TimePicker) timePicker1).setCurrentMinute(00);
	
	//Set the title of the dialog. this space is always drawn even if blank so might as well use it
	//dialog.setIs24HourView(true);
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
	    	
	    	// int min=cal.get(Calendar.MINUTE);
	    	 
	    
	    	 
	    Integer go = ((TimePicker) timePicker1).getCurrentHour();
	    	   Integer go1 = ((TimePicker) timePicker1).getCurrentMinute();
	    	 //  final String TIME_FORMAT1 = "h:mm a";
	    	  // SimpleDateFormat endTimeFormat = new SimpleDateFormat(TIME_FORMAT1);
	    	 
	    	   Calendar currenttime = Calendar.getInstance();
	    	
	    	   String got = currenttime + "";
	
	    long coverthourtomill = go * 60;

	      long millsecconds = 3600000 * go;
	      
	      // get mill seccond of mins
	      long  covertmintomill = go1 * 60000;
	      
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
				   
				   final String TIME_FORMAT_24 = "kk:mm";
				   SimpleDateFormat timeFormat24 = new SimpleDateFormat(TIME_FORMAT_24);
				   String saving24ToDatabase = timeFormat24.format(calInstance.getTime());

	    	
				   if(go1 > 0 || go > 0){
			    	   setalarm(20122012, milseccondcurrent, VibrateQuickMute);
			    	   databaseHelper.createQuickMuteEntry(totalMillTimeString , "202034343", saving24ToDatabase, saving12ToDatabase);
			  ListViewData();	    	   // finish();
			    	   dialog.dismiss();
			    		 Toast.makeText(getApplicationContext(), "Quick Mute started" , Toast.LENGTH_SHORT).show();	  		
			     }else{
			    	 Toast.makeText(getApplicationContext(), "Forgot to enter hours or mins!" , Toast.LENGTH_SHORT).show();
			     }
				   
			
	     
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
	

	//track the check box and see if it was clicked if its checked save vibrate = "On" to database and "On" in alarm otherwise "Off"
	 CheckBox wBox = (CheckBox)dialog.findViewById(R.id.chkBoxCycling);
	   wBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
           
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          
        	  if(isChecked){
        		  VibrateQuickMute = "On";
              }
              else{
            	  VibrateQuickMute = "Off";
              }
               	
			}
      });
	dialog.show();
}

public void cancelQuickMute(){
	
	  Cursor reminder = databaseHelper.getQuickMute(202034343);
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
	    	   databaseHelper.deleteQuickMute(202034343);
	    	   deleteAlarm(endmutetime);//============================================================================================if alarm does not delete its because endmutetime is string not long
	    	   ListViewData();
	   	    TextView buttonview = (TextView)findViewById(R.id.home_btn_feature2);
		    buttonview.setText("Quick Mute");
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

}
