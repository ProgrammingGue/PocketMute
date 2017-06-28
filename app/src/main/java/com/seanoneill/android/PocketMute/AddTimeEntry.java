package com.seanoneill.android.PocketMute;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ParseException;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;

  public class AddTimeEntry extends ListActivity{
	 
	  private Long databaseRowId;
	 private static final int TIME_PICKER = 0;
	 private static final int END_TIME_PICKER = 1;
	 public static final String TIME_FORMAT = "kk:mm";
	 public static final String TIME_FORMAT1 = "h:mm a";
	 Calendar addTimeEntryCal;
	 Calendar endTimeEntryCal;
     private DatabaseStore getDatabaseInfo;
     public Editable goup;
     String goo ="";
     public Editable value;
     private Calendar AutoSilentCalendar;	
     String[] arr = null;
    String getalarmmode = "";
     String alarmonoff = "";
     String ismodeclicked = "Off";
    
     // sets the time next to each button on addtimeentry
     String startTimeText = "";
     String endTimeText = "";
     String repeatdaysText = "";
     String labeltext = ""; 
     String  covertdaystostring = "";
	 String getdaysoftheweek = "";
	 String daysofweek = getdaysoftheweek;
 	String getMuteTitle = "";
 	String gooo = getMuteTitle;
 	
     //button text and real time button time, repeat days, label
	 String[] values = new String[] { "Start Time: " + startTimeText, "End Time: " +endTimeText, "Repeat: " +repeatdaysText, "Label: " +labeltext};
    // int[] go =  {go ,go };
		boolean[]   states = {false, false, false, false, false, false, false};
		  
		  
  String so =  "";
		
		
	
		@Override
	protected void onCreate( Bundle savedIntentState){
		super.onCreate(savedIntentState);
		
		databaseRowId = savedIntentState != null ? savedIntentState.getLong(DatabaseStore.kEY_ROWID) 
					: null;
		
		 ListViewData();
		// TextView text = (TextView) findViewById(R.id.text6);
		 //text.setTextColor(Color.parseColor("#FF0000"));
		 
		getDatabaseInfo = new DatabaseStore(this);
		setContentView(R.layout.editinfo); 
		
		addTimeEntryCal = Calendar.getInstance();
		endTimeEntryCal = Calendar.getInstance();
		
		
		View b = findViewById(R.id.button30);
	    b.setVisibility(View.GONE);
	    
    }
   
	// For repeart button and alert box with checkboes for days of the week
		  
	    @Override
	    protected void onPause() {
	        super.onPause();
	        getDatabaseInfo.close(); 
	    }
	    
	    @Override
	    protected void onResume() {
	        super.onResume();
	        getDatabaseInfo.open(); 
	    	setRowIdFromIntent();
			populateFields();	
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
   
    @Override
    protected void onSaveInstanceState(Bundle savedIntentState) {
     if(databaseRowId != null){
    	super.onSaveInstanceState(savedIntentState);
        savedIntentState.putLong(DatabaseStore.kEY_ROWID, databaseRowId);
    }
     else{
    	 
     }}
	private void buttonListenersetText1() {
		final CharSequence[] items = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
					AlertDialog.Builder builder = new AlertDialog.Builder(AddTimeEntry.this);
					  builder.setTitle("Repeat");
					builder.setIcon(R.drawable.icon);
					

			        builder.setMultiChoiceItems(items, states, new DialogInterface.OnMultiChoiceClickListener(){
			            public void onClick(DialogInterface dialogInterface, int item, boolean go) {
			               
			             }
			        });
			       
			       
					builder.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									  
											int t = 0;						   
									 String[] go =  {" " ," " ,"  ", " ", " "," ", " "} ;
									 
									 //monday	
							           if(states[0] == true){
							        	  go[t] = "Mon";
							    	      t = t+1;
							           } 	
							    
									   //tue
									   if(states[1] == true){
										   go[t] = "Tue";
										   t =  t+1;
									   } 
									   
									   //wed
									   if(states[2] == true){
										   go[t] = "Wed";
										   t =  t+1;
									   }
									   
									   //thur
									   if(states[3] == true){
										   go[t] = "Thur";
										   t =  t+1;
									   }
									   
									   //fri
									   if(states[4] == true){
										   go[t] = "Fri";
										   t =  t+1;
									   }
									   
									   //sat
									   if(states[5] == true){
										  go[t] = "Sat";
										   t =  t+1;
									   }
									   
									   //sun
									   if(states[6] == true){
										   go[t] = "Sun";
										   t =  t+1;
									   }else{
										   go[t] = "";
									   }
									   
									    
									   //covert true and false from state into days of the week and stores then in database as string to display to user on front page
									covertdaystostring = "";
									   for (int i = 0;i<go.length; i++) {
										   covertdaystostring = covertdaystostring+go[i];
									        // Do not append comma at the end of last element
									        if(i<go.length - 1){
									        	covertdaystostring = covertdaystostring+" ";
									        }}
									   
									   
									updateDateButtonText();
															}
							});
					builder.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				}
	
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		}
	
	  @Override
	  protected Dialog onCreateDialog(int id){
		  switch(id){
		    case TIME_PICKER:
			   return setTime();
		case END_TIME_PICKER:
		    	return endTime();
		    	
		  }
		  
		  
		           return super.onCreateDialog(id);
		        }
	
	   private TimePickerDialog setTime(){
		   
		   boolean isTime12HourOr24 = false;
	    	
	    	 if (DateFormat.is24HourFormat(this)){
	    		 isTime12HourOr24 = true;
	    	 }
	    	 
		    TimePickerDialog timePicker = new  TimePickerDialog(this,
			   new TimePickerDialog.OnTimeSetListener() {
					
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						addTimeEntryCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
						addTimeEntryCal.set(Calendar.MINUTE, minute);
					//	changeButtonTIme();
						
						 updateDateButtonText(); 
						
						}
				    },
				        addTimeEntryCal.get(Calendar.HOUR_OF_DAY),
				        addTimeEntryCal.get(Calendar.MINUTE), isTime12HourOr24);
		    
		    //listener for timepicker buttons, so if set is pressed listview will update with correct start time that user inputed
		    timePicker.setOnDismissListener(starttimebuttons);
		    return timePicker;
				               
				           
				       }
	   
	   private void setRowIdFromIntent() {
			if (databaseRowId == null) {
				Bundle extras = getIntent().getExtras();            
				databaseRowId = extras != null ? extras.getLong(DatabaseStore.kEY_ROWID) 
										: null;
				
			}}
	   
	   private TimePickerDialog endTime(){
		   
		   boolean isTime12HourOr24 = false;
	    	
	    	 if (DateFormat.is24HourFormat(this)){
	    		 isTime12HourOr24 = true;
	    	 }
	    	 
		    TimePickerDialog endtimePicker = new  TimePickerDialog(this,
			   new TimePickerDialog.OnTimeSetListener() {
		    	
		    	
		    
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						endTimeEntryCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
						endTimeEntryCal.set(Calendar.MINUTE, minute);
					//	fuckchangeButtonTIme();
						}
				    },
				        endTimeEntryCal.get(Calendar.HOUR_OF_DAY),
				        endTimeEntryCal.get(Calendar.MINUTE), isTime12HourOr24);
		 
		    endtimePicker.setOnDismissListener(starttimebuttons);

				               return endtimePicker;
				               
				       }
	   
	   
	   //listner for start time and end time timepicker
	   private DialogInterface.OnDismissListener starttimebuttons =
			    new DialogInterface.OnDismissListener() {
			        public void onDismiss(DialogInterface dialog) {
				
				       	updateDateButtonText();
			        }
			    };
			    

			    //updaing list view user inputed values
		private void updateDateButtonText() {
				
			//  repeatdaysText = getdaysoftheweek;
			 repeatdaysText = covertdaystostring;
			//  endTimeText = endTimeText
			 
			 SimpleDateFormat endTimeFormat = new SimpleDateFormat(TIME_FORMAT1);
			  SimpleDateFormat dateTimeFormat = new SimpleDateFormat(TIME_FORMAT1);
			  
			 if (DateFormat.is24HourFormat(this)){
			  endTimeFormat = new SimpleDateFormat(TIME_FORMAT);
			  dateTimeFormat = new SimpleDateFormat(TIME_FORMAT);
			 }else{
				
			 }
			   String endDateTime = endTimeFormat.format(endTimeEntryCal.getTime());
			   endTimeText =    endDateTime;
		
			   labeltext = getMuteTitle;		
			 
			   String startDateTime = dateTimeFormat.format(addTimeEntryCal.getTime());
			   startTimeText =    startDateTime;

			   values = new String[] { "Start Time: " + startTimeText, "End Time: " +endTimeText, "Repeat: " +repeatdaysText, "Label: " +gooo, "Vibration: " +ismodeclicked};
	    ListViewData();
	  
		}
		void onTimeChanged (TimePicker view, int hourOfDay, int minute) {
			 updateDateButtonText();
		}
		
		  public void openDialog()
		    {
		        LinearLayout linearLayout = new LinearLayout(this);
		        linearLayout.setLayoutParams(new LayoutParams(
		                   LayoutParams.FILL_PARENT,
		                   LayoutParams.FILL_PARENT));
		        linearLayout.setPadding(0, 0, 0, 0);
		      
		       final EditText saveas = new EditText(this);
		       saveas.setLayoutParams(new LayoutParams(
		               LayoutParams.FILL_PARENT,
		               LayoutParams.FILL_PARENT));
		       saveas.setImeOptions(EditorInfo.IME_ACTION_DONE);
		       saveas.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		       saveas.setHint("Label it");
		       AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		       dialog.setTitle("Label");
		       
		    //   dialog.setMessage("");
		       
		       //gets title from database and sets it in text field
		       
		       SimpleDateFormat dateTimeFormat = new SimpleDateFormat(TIME_FORMAT);
			   String startDateTime = dateTimeFormat.format(addTimeEntryCal.getTime()); 
			   
		     
		       saveas.setText(getMuteTitle); 
		       
		       
		       linearLayout.addView(saveas);

		       dialog.setView(linearLayout);
		       
		  
		       dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
		       {
		    	   
		           public void onClick(DialogInterface dialoginterface, int buttons) 
		           {
		        	
		        	   
		        	   value = saveas.getText();
                       gooo = value.toString();
                       updateDateButtonText();
           		    
		        	
		        	   
		        	   //use this if you want to have text enterd before saving , link it to the save button
		        		if(saveas.getText().toString().length()>0){
		        		
		        		}
		        			//logic
		        			else{
		        			
		        				
		        			}
		           }
		       });

		       
		       dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
		       {
		           public void onClick(DialogInterface arg0, int buttons) 
		           {
		           }
		       });
		       dialog.show();
		    }
	   private void saveInfo() throws java.text.ParseException{
		   long covertRow = 0;
	   
		   SimpleDateFormat dateTimeFormat = new SimpleDateFormat(TIME_FORMAT);
		   String startDateTime = dateTimeFormat.format(addTimeEntryCal.getTime());
		   String endDateTime = dateTimeFormat.format(endTimeEntryCal.getTime());
		   Cursor reminder = getDatabaseInfo.getmax();
		   
		   if(reminder.moveToFirst()) {
			   
			 covertRow =   reminder.getLong(0);
		   }
		   else{
			   
		   }
	
		   String MainPageCursor = getDatabaseInfo.kEY_ROWID;	   
		 String  AlarmidString = covertRow + "";
		 
	     String s = "12:18";
	     SimpleDateFormat f1 = new SimpleDateFormat("hh:mm");
	     Date d = f1.parse(startDateTime);
	     SimpleDateFormat f2 = new SimpleDateFormat("h:mm a");
	    String go22 =  f2.format(d).toLowerCase();

	    //turn 24 hour clock to 12 hour clock - start time
	    SimpleDateFormat f11 = new SimpleDateFormat("hh:mm");
	     Date d2 = f11.parse(endDateTime);
	     SimpleDateFormat f22 = new SimpleDateFormat("h:mm a");
	    String go222 =  f22.format(d2).toLowerCase();
		 
		 
		 
		   if (databaseRowId == null){
			   
			   String fuckingWork = "" ;
			   for (int i = 0;i<states.length; i++) {
			       fuckingWork = fuckingWork+states[i];
			        // Do not append comma at the end of last element
			        if(i<states.length-1){
			            fuckingWork = fuckingWork+",";
			        }}
			   
			   if(gooo == ""){
					  gooo = startDateTime;//-----------------------------------------------------------------------------------------------------------change default title lable
				  }else{
				  }
			   long id =  getDatabaseInfo.createEntry(startDateTime, endDateTime, states, gooo, AlarmidString, "On", go22, go222, "1", ismodeclicked); 
				 Toast.makeText(getApplicationContext(), "Profile has been created" , Toast.LENGTH_SHORT).show();	  		
			  if(id > 0){
				  databaseRowId = id;
			  }}
			  else{
		
			boolean go = getDatabaseInfo.updateReminder(databaseRowId, startDateTime, endDateTime, states, gooo, AlarmidString, getalarmmode, go22, go222, ismodeclicked); // -- goooo is title
			 Toast.makeText(getApplicationContext(), "Profile has been updated" , Toast.LENGTH_SHORT).show();	  		
			  }
		   
		   if(getalarmmode.equals("Off")){
				
		   }else{
			    long addonetome = covertRow;
		
			   new SetAndroidAlarm(this).setAlarm(addonetome, addTimeEntryCal, states, endTimeEntryCal, ismodeclicked);     
		   }

			  int go =  states.length;
			  String go1 = go + "";
			   String covertRow1 = covertRow + "";
			   String ggo = databaseRowId + "";
		   }
		
	   
	   public void convertStringToArray(String str){
		  	    
		}

	    private void populateFields()  {
	    	    	
	    	// Only populate the text boxes and change the calendar date
	    	// if the row is not null from the database. 
	        if (databaseRowId != null) {
	            Cursor reminder = getDatabaseInfo.fetchSilent(databaseRowId);
	            startManagingCursor(reminder);
	            
	            // Get the date from the database and format it for our use. 
	            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(TIME_FORMAT);
	           Date startTime = null;
	           Date endTime = null;
	         
			   
				try {
					String[] arr  = null;
					///Update Start time & end pickers to what is saved in database
					String StartTimeUpdate = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.START_KEY_TIME)); 
					String endTimeUpdate = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.END_KEY_TIME)); 
					String getStates = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.KEY_STATE)); 
					 getMuteTitle = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.MUTE_TITLE)); 
					 covertdaystostring = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.COVERT_DAYS)); 
					 getalarmmode = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.ALARM_MODE)); 
					 ismodeclicked = reminder.getString(reminder.getColumnIndexOrThrow(DatabaseStore.ALARM_VIBRATE)); 
							
					 // getting start time from database and putting it in timepicker
					String go3 = StartTimeUpdate.substring(0,2);
					String go4 = StartTimeUpdate.substring(3,5);
					
					int value = Integer.parseInt(go3); 
					int valu1e = Integer.parseInt(go4); 

					
						addTimeEntryCal.set(Calendar.HOUR_OF_DAY, value);
						addTimeEntryCal.set(Calendar.MINUTE, valu1e);
			
						////////////////////////////////////////////////////////////////////
						// getting end time from database and add it to timepicker
						
						String go5 = endTimeUpdate.substring(0,2);
						String go6 = endTimeUpdate.substring(3,5);
						
						int value11 = Integer.parseInt(go5); 
						int valu1e11 = Integer.parseInt(go6); 

							
						endTimeEntryCal.set(Calendar.HOUR_OF_DAY, value11);
						endTimeEntryCal.set(Calendar.MINUTE, valu1e11);
						
						////////////////////////////////////////////////////////////////////////////
						
						endTime = dateTimeFormat.parse(endTimeUpdate);
						
						    String[] parts = getStates.split(" , ");
						   
						    View b = findViewById(R.id.button30);
						    b.setVisibility(View.VISIBLE);
						    
						    if(getalarmmode.equals("On")){
						    	((TextView) b).setText("Turn Off");
						    }else{
						    	((TextView) b).setText("Turn On");
						    }
					
						    // checks checkboxs from the data in the database
						    String states1[] = getStates.split(",");
					        for(int i = 0; i < states1.length; i++){
					      states1[i].trim();
					      states[i] = Boolean.valueOf(states1[i]);
					     
					        }
					      
						//gets the title from the database and display it in text box and on listview
						  gooo = getMuteTitle;
						  
						  
				} catch (ParseException e) {
					
				
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        } else {
	        	      
	        }
	        updateDateButtonText();
	    }
	    
	    ///  click buttons in list view
	    private void ListViewData(){

			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1, values);
					setListAdapter(adapter);
				}
		 
	    protected void onListItemClick(ListView l, View v, int position, long id){
	    	super.onListItemClick(l, v, position, id);
	    	if(position == 0){
	    		 showDialog(TIME_PICKER);
	    }
	    	if(position == 1){
	    		 showDialog(END_TIME_PICKER);
	    	}
	    	if(position == 2){
	    		buttonListenersetText1();
	    	}
	    	if(position == 3){
	    		openDialog();
	    	}if(position == 4){
	    		mode();
	   	
	    }}
	    public void onClickFeature (View v) throws java.text.ParseException
	    {
	        int id = v.getId ();
	        switch (id) {
	          case R.id.button10 :
	              saveInfo();
	              setResult(RESULT_OK);
		
					finish();
	               break;
	          case R.id.button20 :
	               startActivity (new Intent(getApplicationContext(), MainActivity.class));
	               break;
	          case R.id.button30 :
	     
	        	  AutoSilentCalendar = Calendar.getInstance();
                    	          	
	        	String onoroff = "";
	        String go20 = "";
	        	  if(getalarmmode.equals("On")){
	        		  getalarmmode = "Off";
	        		  onoroff = "0";
	        		 new SetAndroidAlarm(this).overrightDelete(databaseRowId, AutoSilentCalendar);  
	        		 Toast.makeText(getApplicationContext(), "Profile has been turned off" , Toast.LENGTH_SHORT).show();	  		
	        	  }else{
	        		  getalarmmode = "On";
	        		  onoroff = "1";
	        		  new SetAndroidAlarm(this).setAlarm(databaseRowId, addTimeEntryCal, states, endTimeEntryCal, ismodeclicked); 
	        		  Toast.makeText(getApplicationContext(), "Profile has been turned on" , Toast.LENGTH_SHORT).show();	  		
	        	  }
	        	
	        	 
	        	 getDatabaseInfo.updateAlarmMode(databaseRowId, getalarmmode, onoroff);
	        	 
	        	 startActivity (new Intent(getApplicationContext(), MainActivity.class));
	               break;
	   
	          default: 
	        	   break;
	        }
	    }
	  
	    public void onClickSearch (View v)
	    {
	        
	   }
	    //allows the user to enter text for its mute phone alarm
	      
	    public void onClick(View view) {
	        Intent i = new Intent( AddTimeEntry.this, HelpPage.class);
	        startActivity(i);
	    }
	    
	    
	    public void mode(){
	    
	    	
	    	if(ismodeclicked.equals("Off")){
	   
	    ismodeclicked = "On";
	    updateDateButtonText();
	    	}

	    	else if(ismodeclicked.equals("On")){
	    		
	    		ismodeclicked = "Off";
	    		 updateDateButtonText();
	    	}
	       
	      }
	    }
  
  
	
	
	 
