package com.seanoneill.android.PocketMute;

import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.widget.TextView;

	 public class EndShortSilentMode extends  WakeUpCpu {
		 private DatabaseStore getDatabaseInfo;
		 
		 private AudioManager mmAudioManager;

		 public EndShortSilentMode(){
			super("SilentServices");
			 
		 
		 }
		
		 @Override
		void normalMode(Intent intent){
			
			 
			 double deleteme = intent.getDoubleExtra("delete", 1.1);
			 double selectedId = intent.getDoubleExtra("id", 1.1);//------------------- mgiht need to start a 0
				double selectedId1 = intent.getDoubleExtra("idid", 0.0);
			
		
				// neeed to get extas from other file
			//int g = 	extras.

			 // if this class is set off by alarm if its normal mute it will not be == 2 so it will just turn alarm
			 // to normal because slient time is over, if its == 2 because it was quick mute it will delete
			 // entry to database and take the pohne off mute.
			
				if(deleteme == 10.0 ){
					
				}
				
				
				if(selectedId == 2.0){

			getDatabaseInfo = new DatabaseStore(this);
	 getDatabaseInfo.open(); 
	 
			 Calendar calendar = Calendar.getInstance();
			 getDatabaseInfo.deleteQuickMute(202034343);

			  NotificationManager notificationManager = (NotificationManager) 
						getSystemService(NOTIFICATION_SERVICE);
			    
			    boolean go11 = getDatabaseInfo.goggo();
				 // Toast.makeText(getApplicationContext(),  go11 + "", Toast.LENGTH_SHORT).show();		
				  
				  Cursor MainPageCursor1 = getDatabaseInfo.fetchAllQuciMute();
				
				  
				  if(go11 == false || MainPageCursor1.getCount()<=0 ){
					
					  notificationManager.cancel(0);
			   
				  }else{
					  
				  }
				  				  
	// getDatabaseInfo.deleteEntry(selectedId1);
	   getDatabaseInfo.close();
		mmAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
	   mmAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	
}if(selectedId1 > 0.0){	
		
		getDatabaseInfo = new DatabaseStore(this);
		 getDatabaseInfo.open();
		 
		 long covertselectedId1 = (long) selectedId1;
		 getDatabaseInfo.turnBooleantoFalse(covertselectedId1 - 1);
			    boolean go11 = getDatabaseInfo.goggo();
				 
				  
				 // Cursor MainPageCursor1 = getDatabaseInfo.fetchAllQuciMute();
				  NotificationManager notificationManager = (NotificationManager) 
							getSystemService(NOTIFICATION_SERVICE);
				  
				  if(go11 == false){ 
					  notificationManager.cancel(0);
				  }

		 getDatabaseInfo.close();
	
		mmAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
		   mmAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
}
			 		 
           if(deleteme != 10.0 && selectedId1 == 0.0 &&  selectedId != 2.0){
					mmAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
					   mmAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);			
	      }
            }
		     }
	 

