package com.seanoneill.android.PocketMute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseStore{

 private static final String DATABASE_NAME = "silent_time_database";
 private static final String DATABASE_TABLE = "silent";
 private static final String DATABASE_TABLE_QUICK = "quick_mute";
 private static final int DATABASE_VERSION = 1;
 public static final String START_KEY_TIME = "silent_end_time";
 public static final String END_KEY_TIME = "silent_start_time";
 public static final String START_TIME_12_HOUR = "start_coverted_24_to_12_hour";
 public static final String END_TIME_12_HOUR = "end_coverted_24_to_12_hour";
 
 public static final String kEY_ROWID = "_id"; 
 public static final String COVERT_DAYS = "silent_covert_day";
 public static final String KEY_STATE = "silent_day_of_week";
 public static final String MUTE_TITLE = "title";
 public static final String ALARM_MODE = "alarm_mode";
 public static final String ALARM_ID = "alarm_id";
 public static final String ALARM_BOOLEAN = "alarm_on_or_off";
 public static final String ALARM_VIBRATE = "alarm_vibrate";
 public static final String ALARM_ON_NOW = "is_alarm_on_now";

 
 //quick mute
 public static final String QUICK_ROWID = "_id"; 
 public static final String END_TIME_QUICK = "quick_mute_end_time";
 public static final String QUICK_TIME_24 = "quick_mute_24";
 public static final String QUICK_TIME_12 = "quick_mute_12";
 public static final String ALARM_ID_QUICK = "quick_mute_id";
 
 

 private DatabaseHelper dataAnroid;
 private SQLiteDatabase databaseConnect;
 private final Context androidContext;
 int t = 0;
private String tileUpdate;

  private static final String DATABASE_CREATE =
		  
		  "create table " + DATABASE_TABLE_QUICK + " ("
		        + QUICK_ROWID + " integer primary key autoincrement, "
                   + END_TIME_QUICK  + " text not null, "
                    + QUICK_TIME_24  + " text not null, "
                    + QUICK_TIME_12  + " text not null, "
                   + ALARM_ID_QUICK + " text not null );";
		  
                  private static final String DATABASE_CREATE1=
                              "create table " + DATABASE_TABLE + " ("
    	                      + kEY_ROWID + " integer primary key autoincrement, "
    	                      + END_KEY_TIME  + " text not null,"
    	                      + START_TIME_12_HOUR  + " text not null,"
    	                      + END_TIME_12_HOUR  + " text not null,"
    	                      + COVERT_DAYS + " text not null,"
    	                      + KEY_STATE  + " text not null,"
    	                      + MUTE_TITLE + " text not null, "
    	                      + ALARM_MODE + " text not null, "
    	                       +ALARM_ID+ " text not null, "
    	                       +ALARM_BOOLEAN+ " text not null, "
    	                       +ALARM_VIBRATE+ " text not null, "
    	                       + ALARM_ON_NOW + " text not null, "
    	                      + START_KEY_TIME  + " text not null  );";

public DatabaseStore(Context databaseContext) {
	  this.androidContext = databaseContext;
 }
 
private static class DatabaseHelper extends SQLiteOpenHelper {
	   DatabaseHelper(Context context){
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
	   }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldDatabase, int newDatabase) {
	          // needed for upgrading database from old one ---- not used right now
	    }
	}

	public DatabaseStore open() throws android.database.SQLException{
		dataAnroid = new DatabaseHelper(androidContext);
		databaseConnect = dataAnroid.getReadableDatabase();
		return this;
        }
	  
    public void close() {
	      dataAnroid.close();
	     }

   public long createEntry(String startTime, String endTime, boolean[] states, String gooo2, String alarmid, String alarmMode, String startTime12Hour, String endTime12Hour, String alarmboolean, String vibrate) {
	    
//Covert the true and false of the days of the week into a string, only way they can be stored in the database
	   String changeStateToString = "";
	   for (int i = 0;i<states.length; i++) {
		   changeStateToString = changeStateToString+states[i];
	        // Do not append comma at the end of last element
	        if(i<states.length - 1){
	        	changeStateToString = changeStateToString+",";
	        }}
	   
		// make another database tabke and save days of the week to the database and use i = i +1 to save days of week to database and show on list then
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
		   String fuckingWork1 = "";
		   for (int i = 0;i<go.length; i++) {
		       fuckingWork1 = fuckingWork1+go[i];
		        // Do not append comma at the end of last element
		        if(i<go.length - 1){
		            fuckingWork1 = fuckingWork1+" ";
		        }}
		 
	//  String gooo =  gooo2.toString();
	   ContentValues initialValues = new ContentValues();
	     initialValues.put(START_KEY_TIME , startTime);
	     initialValues.put(END_KEY_TIME , endTime);
	     initialValues.put(START_TIME_12_HOUR, startTime12Hour);
	     initialValues.put(END_TIME_12_HOUR, endTime12Hour );
	     initialValues.put(MUTE_TITLE, gooo2);
	     initialValues.put(KEY_STATE, changeStateToString ); //--------------------------------------problem is key state wont save but covert days will why dont know... variable is holding all the true false it jsut wont save to key sate might be (,) that coverts it try a sapce or something
	     initialValues.put(COVERT_DAYS , fuckingWork1);
	     initialValues.put(ALARM_MODE , alarmMode);
	     initialValues.put(ALARM_ID, alarmid);
	     initialValues.put(ALARM_BOOLEAN, alarmboolean);
	     initialValues.put(ALARM_VIBRATE, vibrate);
	     initialValues.put(ALARM_ON_NOW, "Off");
	         return databaseConnect.insert(DATABASE_TABLE, null, initialValues);
        }
   
    public boolean deleteEntry(long rowId){
	    return databaseConnect.delete(DATABASE_TABLE, kEY_ROWID + "=" +rowId, null) > 0;
        }
    
    public Cursor getDatabaseEntries() {
	    return databaseConnect.query(DATABASE_TABLE, new String[] {kEY_ROWID, START_KEY_TIME, END_KEY_TIME, KEY_STATE, COVERT_DAYS,MUTE_TITLE, ALARM_ID, ALARM_MODE, START_TIME_12_HOUR, END_TIME_12_HOUR, ALARM_VIBRATE }, null, null, null, null, null);// TOOK OUT ONE NULL FROM EACH
	    }
    
   public Cursor getmax(){
    			String maxValue = "1";
    	Cursor c = databaseConnect.rawQuery("SELECT seq FROM sqlite_sequence WHERE sqlite_sequence.name = 'silent'", null);
    	if (c != null && c.moveToFirst())
    	    maxValue = String.valueOf(c.getInt(c.getColumnIndex("seq")));
		return c;
    }
    
   // for 24 hour clock
    public Cursor fetchAllReminders() {

        return databaseConnect.query(DATABASE_TABLE, new String[] {kEY_ROWID, 
                START_KEY_TIME, END_KEY_TIME, KEY_STATE, COVERT_DAYS, MUTE_TITLE, ALARM_ID, ALARM_MODE}, null, null, null, null, null);
    }
    
    //for 12 hour clock
    public Cursor fetchAllMutes12Hour() {

        return databaseConnect.query(DATABASE_TABLE, new String[] {kEY_ROWID, 
        		START_TIME_12_HOUR, END_TIME_12_HOUR, KEY_STATE, COVERT_DAYS, MUTE_TITLE, ALARM_ID, ALARM_MODE,ALARM_ON_NOW }, null, null, null, null, null);
    }
    
    public Cursor fetchSilent(long rowId) throws SQLException {
	     Cursor mCursor = databaseConnect.query(true, DATABASE_TABLE, new String[] {kEY_ROWID, 
			                              START_KEY_TIME, END_KEY_TIME, KEY_STATE, COVERT_DAYS, MUTE_TITLE, ALARM_ID, ALARM_MODE, START_TIME_12_HOUR, END_TIME_12_HOUR, ALARM_VIBRATE }, 
			                              kEY_ROWID + "=" + rowId,  null,null,null,null, null);/// if problem take out nulls
	  
	     if (mCursor != null){
		     mCursor.moveToFirst();
	         }
	             return mCursor;
	         }
        
    
    //******************************* neeed to put covert_state_to_days into update below some how ****************************//
    
    public boolean updateReminder(long rowId, String startTime, String endTime , boolean[] states, String mutetitle ,String goup, String alarmMode, String startTime12Hour, String endTime12Hour, String vibrate) {
      
    	  String changeStateToString = "";
   	   for (int i = 0;i<states.length; i++) {
   		   changeStateToString = changeStateToString+states[i];
   	        // Do not append comma at the end of last element
   	        if(i<states.length - 1){
   	        	changeStateToString = changeStateToString+",";
   	        }}

   	 String[] go1 =  {" " ," " ,"  ", " ", " "," ", " "} ;
	   //monday	
   	int g = 0;
if(states[0] == true){
	 go1[g] = "Mon";
	  g = g+1;

} 

	
	   //tue
	   if(states[1] == true){
		   go1[g] = "Tue";
		   g =  g+1;
	   } 
	   
	   //wed
	   if(states[2] == true){
		   go1[g] = "Wed";
		   g =  g+1;
	   }
	   
	   //thur
	   if(states[3] == true){
		   go1[g] = "Thur";
		   g =  g+1;
	   }
	   
	   //fri
	   if(states[4] == true){
		   go1[g] = "Fri";
		   g =  g+1;
	   }
	   
	   //sat
	   if(states[5] == true){
		  go1[g] = "Sat";
		   g =  g+1;
	   }
	   
	   //sun
	   if(states[6] == true){
		   go1[g] = "Sun";
		   g =  g+1;
	   }else{
		   go1[g] = "";
	   }
	   
	    
	   //covert true and false from state into days of the week and stores then in database as string to display to user on front page
	   String fuckingWork11 = "";
	   for (int i = 0;i<go1.length; i++) {
	       fuckingWork11 = fuckingWork11+go1[i];
	        // Do not append comma at the end of last element
	        if(i<go1.length - 1){
	            fuckingWork11 = fuckingWork11+" ";
	        }}
	   
    	ContentValues args = new ContentValues();
        args.put(START_KEY_TIME, startTime);
        args.put(END_KEY_TIME, endTime);
        args.put(START_TIME_12_HOUR, startTime12Hour);
	     args.put(END_TIME_12_HOUR, endTime12Hour );
        args.put(KEY_STATE, changeStateToString);
        args.put(MUTE_TITLE, mutetitle);
       args.put(ALARM_MODE, alarmMode);
      args.put(COVERT_DAYS , fuckingWork11);
      args.put(ALARM_VIBRATE , vibrate);
        return databaseConnect.update(DATABASE_TABLE, args, kEY_ROWID + "=" + rowId, null) > 0;
    }  
    

    public boolean updateAlarmMode(long rowId, String alarmMode, String onorOffBoolean) {
    	ContentValues args = new ContentValues();
    	args.put(ALARM_MODE, alarmMode);
    	args.put(ALARM_BOOLEAN, onorOffBoolean);
    	
    	 return databaseConnect.update(DATABASE_TABLE, args, kEY_ROWID + "=" + rowId, null) > 0;
}
    
    //save quick mute in database table
    public void createQuickMuteEntry(String endTime, String alarmid1, String Time24HourClock, String Time12HourClock) {
    	
    	
    	  ContentValues initialValues = new ContentValues();
 	     initialValues.put(END_TIME_QUICK, endTime);
 	    initialValues.put(ALARM_ID_QUICK, alarmid1);
 	   initialValues.put(QUICK_TIME_24, Time24HourClock);
 	   initialValues.put(QUICK_TIME_12, Time12HourClock);
 	     
 	     databaseConnect.insert(DATABASE_TABLE_QUICK, null, initialValues);
         }
    
    
   
    public Cursor fetchAllQuciMute() {

        return databaseConnect.query(DATABASE_TABLE_QUICK, new String[] { QUICK_ROWID,
        		END_TIME_QUICK, ALARM_ID_QUICK, QUICK_TIME_24, QUICK_TIME_12}, null, null, null, null, null);
    }
  
    public Cursor getQuickMute(long rowId) throws SQLException {
	     Cursor mCursor = databaseConnect.query(true, DATABASE_TABLE_QUICK, new String[] {QUICK_ROWID, 
	    		 END_TIME_QUICK, ALARM_ID_QUICK, QUICK_TIME_24, QUICK_TIME_12 }, 
	    		 ALARM_ID_QUICK + "=" + rowId,  null,null,null,null, null);/// if problem take out nulls
	  
	     if (mCursor != null){
		     mCursor.moveToFirst();
	         }
	             return mCursor;
	         }
    
    public boolean deleteQuickMute(long rowId){
	    return databaseConnect.delete(DATABASE_TABLE_QUICK, ALARM_ID_QUICK + "=" +rowId, null) > 0;
        }
    
    public boolean QuickTime(){
    	
	
    		String myQuery = "SELECT * FROM DATABASE_TABLE_QUICK WHERE ALARM_ID_QUICK LIKE 'On' ";
    	    Cursor mCursor = databaseConnect.rawQuery(myQuery, null);
    	    if(mCursor.getCount()>0)  //if at least one record contains 'on'
    	        return true;
    	    else
    	        return false; //no record was found with the word 'on'
    	}
    
    public boolean goggo() throws SQLException {
	   
    	String rowId = "1";
    	boolean mCursor = databaseConnect.query(true, DATABASE_TABLE, new String[] {QUICK_ROWID, 
    			ALARM_MODE, }, 
    			ALARM_BOOLEAN + "=" +rowId,  null,null,null,null, null).getCount() > 0;/// if problem take out nulls
	  
	  
	             return mCursor;
    }
    
    //if there is no repating in alarm when it ends if will be turned off
    public boolean updateTurnOff(long rowId) {
    	ContentValues args = new ContentValues();
    	args.put(ALARM_MODE, "Off");
    	
    	 return databaseConnect.update(DATABASE_TABLE, args, kEY_ROWID + "=" + rowId, null) > 0;
}
    
    
    public boolean turnBooleantoFalse(long rowId) {
    	ContentValues args = new ContentValues();
    	args.put(ALARM_MODE, "Off");
    	args.put(ALARM_BOOLEAN, 0);
    	
    	 return databaseConnect.update(DATABASE_TABLE, args, kEY_ROWID + "=" + rowId, null) > 0;
}
    
    public boolean turnOnOrOffAlarm(long rowId, String onOrOff) {
    	ContentValues args = new ContentValues();
    	args.put(ALARM_MODE, onOrOff);
    	
    	 return databaseConnect.update(DATABASE_TABLE, args, kEY_ROWID + "=" + rowId, null) > 0;
}
    
}

