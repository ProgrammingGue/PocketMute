package com.seanoneill.android.PocketMute;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.database.Cursor;
import android.util.Log;

public class PhoneRebootReset extends BroadcastReceiver {
	
	private static final String TAG = ComponentInfo.class.getCanonicalName();  
	 public static final String TIME_FORMAT1 = "kk:mm";
	 public static final String TIME_FORMAT2 = "kk:mm";
	@Override
	public void onReceive(Context context, Intent intent) {

		SetAndroidAlarm reminderMgr = new SetAndroidAlarm(context);
		
       	DatabaseStore dbHelper = new DatabaseStore(context);
		dbHelper.open();
			
		Cursor cursor = dbHelper.fetchAllReminders();

		if(cursor != null) {
			cursor.moveToFirst(); 
			
			long rowIdColumnIndex = cursor.getColumnIndex(DatabaseStore.kEY_ROWID);
			String StartTimeUpdate1 = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseStore.START_KEY_TIME)); 
			String endTimeUpdate1 = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseStore.END_KEY_TIME)); 
		int dayStates = cursor.getColumnIndex(DatabaseStore.KEY_STATE); 
		
			while(cursor.isAfterLast() == false) {

				Log.d(TAG, "Adding alarm from boot.");
				Log.d(TAG, "Row Id Column Index - " + rowIdColumnIndex);
			//	Log.d(TAG, "Date Time Column Index - " + endTime);
				Log.d(TAG, "Date Time Column Index - " + dayStates);
				
			//	Long rowId = cursor.getLong(rowIdColumnIndex); 
			//	String start = cursor.getString(startTime); 
		       //String end = cursor.getString(endTime); 
				String dayofweek = cursor.getString(dayStates); 

				Calendar cal = Calendar.getInstance();
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT1); 
				SimpleDateFormat format1 = new SimpleDateFormat(TIME_FORMAT2); 

				
				try {
					java.util.Date startFormat = format.parse(StartTimeUpdate1);
					java.util.Date endFormat = format1.parse(endTimeUpdate1);
					cal.setTime(startFormat);
					cal1.setTime(endFormat);
					//Calendar addTimeEntryCal = Calendar.getInstance();
					boolean[]   covertTo = {true, true, true, true, true, true, true};
					   String[] arr = dayofweek.split(",");
					   
					 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					   
					   SimpleDateFormat dateTimeFormat1515 = new SimpleDateFormat(TIME_FORMAT1);
					   String currenttime = dateTimeFormat1515.format(System.currentTimeMillis());
					   
					   SimpleDateFormat dateTimeFormat1955 = new SimpleDateFormat(TIME_FORMAT1);
					   String endDateTime2060 = dateTimeFormat1955.format(endFormat.getTime());
					   
			           
			           String nowtime = currenttime.substring(0,2);

			           String golow = endDateTime2060.substring(0,2);
			      //     String goo = endTimeEntryCal + "";
			          // String go10 = endDateTime.substring(0,2);
			           int imhere = Integer.parseInt(golow); 
			           
			           int eo1212 = Integer.parseInt(nowtime); 
			                
			 
					   
			           
			     

			         
			      //     String goo = endTimeEntryCal + "";
			          // String go10 = endDateTime.substring(0,2);
			           
			           
			       	long endtimenow = 0;
			           
			     String onOrOff = "Off";
			                 if(eo1212 < imhere){
			                	 
			                	 onOrOff = "On";
			                	 ///////////////////////////////////////////////////////////////////////////////////
			                 }
					   
					  // new SetAndroidAlarm(this).setAlarm(databaseRowId, addTimeEntryCal, states, endTimeEntryCal);  *********************problem with this**
				reminderMgr.setAlarm(rowIdColumnIndex, cal, covertTo, cal1, "On" ); 
				
				 
			 //    setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endSun, daysBetweenAlrm , endIntent); 
				} catch (java.text.ParseException e) {
					Log.e("OnBootReceiver", e.getMessage(), e);
				}
				
				cursor.moveToNext(); 
			}
			cursor.close() ;	
		}
			
		dbHelper.close(); 
	}
}
