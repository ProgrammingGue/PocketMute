package com.seanoneill.android.PocketMute;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.format.DateUtils;
import android.widget.Toast;

public class SetAndroidAlarm {
	 private DatabaseStore databaseHelper;
    private Context setAndroidAlarmContext;
	private AlarmManager setAlarm;
	private AlarmManager setAlarm1;
	   private DatabaseStore getDatabaseInfo;
	   
	   public static final String TIME_FORMAT = "kk:mm";
	   SimpleDateFormat dateTimeFormat = new SimpleDateFormat(TIME_FORMAT);
	Calendar cal = Calendar.getInstance();
	public SetAndroidAlarm(Context context){
		   setAndroidAlarmContext = context;
		   setAlarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		   setAlarm1 = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		   }
	
	//get day of the week
	 Calendar calendar = Calendar.getInstance();
     int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
	
	//start silent mode
	public void setAlarm(Long timeId, Calendar when, boolean[] states, Calendar endTimeEntryCal, String vibrate){
		
		 Intent startAlarmIntent = new Intent(setAndroidAlarmContext, DontSleepAndroid.class); 
	     if(vibrate.equals("On")){
		 startAlarmIntent.putExtra("delete",(double) 5); 
		// int go = 4;
		//double go1 = Double.parseDouble(go);
	     }
	 	
		Integer covertToInt = timeId.intValue();

		
		long  daysBetweenAlrm = 86400000 * 7;
		
	
		int day1 = 0; //monday
	    int day2 = 1; //tue
	    int day3 = 2; // wed
	    int day4 = 3; // thur
	    int day5 = 4; //fri
	    int day6 = 5; // sat
	    int day7 = 6; // sun
	      
	      int ifMon = 0;
	      int ifTue = 0;
	      int ifWed = 0;
	      int ifThur = 0;
	      int ifFri = 0;
	      int ifSat = 0;
	      int ifSun = 0;
	      int something = 0;
	      //runs monday code
	    		  if(states[0] == true){
	    			  ifMon = 1;
	    		  }
	    		  //runs tue code
	    		  if(states[1] == true){
	    		  ifTue = 2;
	    	  }
	    		  //runs wed code
	    		   if(states[2] == true){
		    		  ifWed = 3;
	    		  }
	    		  
	    		  //runs thur code
	    		  if(states[3] == true){
	    			  ifThur = 4;
	    		  }
	    		  
	    		  //runs fri code
	    		  if(states[4] == true){
	    			  ifFri = 5;
	    		  }
	    		  
	    		  //runs sat code
	    		  if(states[5] == true){
	    			  ifSat = 6;
	    		  }
	    		  
	    		  //runs sun code
	    		   if(states[6] == true){
	    			  ifSun = 7;
	    		  }
	    		  if(states[0] == false & states[1]  == false & states[2]  == false & states[3]  == false & states[4]  == false & states[5]  == false & states[6]  == false){
	    			something = 12;
	    		  }
	    		  
	    		  
	    		  //Monday
	 if(ifMon == 1){
		
	         if( currentDay == 1){
	        	  day1 =    86400000 * 1; 
	         }
             if( currentDay == 2){
             	  day1 =   0; 
	         }
            if( currentDay == 3){
            	day1 =   86400000 * 6; 
	         }
            if( currentDay == 4){
            	day1 =   86400000 * 5 ; 
	         }   
            //thur
            if( currentDay == 5){
            	day1 =   86400000 * 4; 
	         }
	         
            
            if( currentDay == 6){
            	day1 =   86400000 * 3; 
	         }
	         
            
            if( currentDay == 7){
            	day1 =   86400000 * 2; 
	         }
	         
            //set alarm idenfication
         int  alarmMondayStart =  covertToInt + 1;
         int alarmMondayEnd =  covertToInt + 2;
         final String TIME_FORMAT = "kk";
         SimpleDateFormat dateTimeFormat = new SimpleDateFormat(TIME_FORMAT);
		   String startDateTime = dateTimeFormat.format(endTimeEntryCal.getTime());
		   
		   SimpleDateFormat dateTimeFormat1 = new SimpleDateFormat(TIME_FORMAT);
		   String endDateTime = dateTimeFormat1.format(when.getTime());
		   
            long startMonday =  when.getTimeInMillis() + day1;
            long endMonday;
            
            String go9 = startDateTime.substring(0,2);

            String go99 = endDateTime.substring(0,2);
       //     String goo = endTimeEntryCal + "";
           // String go10 = endDateTime.substring(0,2);
            int go111 = Integer.parseInt(go99); 
            
            int go = Integer.parseInt(go9); 
                  if(go >= 00 && go111 <= 00){
           //////////////////////////////////////////////////////////////////////////////////////////////////////// 
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
        
                if( currentDay == 1){
  	        	  day2 =    86400000 *2; 
  	         }
  	         //mon
               if( currentDay == 2){
               	  day2 =   86400000 * 1; 
  	         }
               //tue
              if( currentDay == 3){
              	day2 =   0; 
  	         }
              //wed
              if( currentDay == 4){
              	day2 =   86400000 * 6 ; 
  	         }   
              //thur
              if( currentDay == 5){
              	day2 =   86400000 * 5; 
  	         }
  	         
              //fri
              if( currentDay == 6){
              	day2 =   86400000 * 4; 
  	         }
  	         
              //sat
              if( currentDay == 7){
              	day2 =   86400000 * 3; 
  	         }
               endMonday = endTimeEntryCal.getTimeInMillis() + day2;
                  }else{
                	  endMonday = endTimeEntryCal.getTimeInMillis() + day1;
                  }
              ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
           //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	     // was 0 ....flag_update_current .... works for now anyway
	     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmMondayStart , startAlarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
	     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startMonday, daysBetweenAlrm , startIntent);
	    
	     //turn off
	     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
	     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmMondayEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endMonday, daysBetweenAlrm , endIntent); 
	    }

	 // /////////////////////////////////////////////////////////////////////////////////////////
	 
	 
	 // Tueday code
	 if(ifTue == 2){	         
				
		 //sun
	         if( currentDay == 1){
	        	  day2 =    86400000 *2; 
	         }
	         //mon
             if( currentDay == 2){
             	  day2 =   86400000 * 1; 
	         }
             //tue
            if( currentDay == 3){
            	day2 =   0; 
	         }
            //wed
            if( currentDay == 4){
            	day2 =   86400000 * 6 ; 
	         }   
            //thur
            if( currentDay == 5){
            	day2 =   86400000 * 5; 
	         }
	         
            //fri
            if( currentDay == 6){
            	day2 =   86400000 * 4; 
	         }
	         
            //sat
            if( currentDay == 7){
            	day2 =   86400000 * 3; 
	         }
	         
            long startTue =  when.getTimeInMillis() + day2;
            long endTue;
            
            //set alarm idenfication
            int  alarmTuedayStart =  covertToInt + 3;
            int alarmTuedayEnd =  covertToInt + 4;
            
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            final String TIME_FORMAT = "kk";
            SimpleDateFormat dateTimeFormat1 = new SimpleDateFormat(TIME_FORMAT);
   		   String startDateTime1 = dateTimeFormat1.format(endTimeEntryCal.getTime());
   		   
   		   SimpleDateFormat dateTimeFormat11 = new SimpleDateFormat(TIME_FORMAT);
   		   String endDateTime1 = dateTimeFormat11.format(when.getTime());
   		   
               
               String go91 = startDateTime1.substring(0,2);

               String go991 = endDateTime1.substring(0,2);
          //     String goo = endTimeEntryCal + "";
              // String go10 = endDateTime.substring(0,2);
               int go1112 = Integer.parseInt(go991); 
               
               int go2 = Integer.parseInt(go91); 
                     if(go2 >= 00 && go1112 <= 00){
              //////////////////////////////////////////////////////////////////////////////////////////////////////// 
               ////////////////////////////////////////////////////////////////////////////////////////////////////////
           
                    	 //sun
        		         if( currentDay == 1){
        		        	  day3 =    86400000 *3; 
        		         }
        		         //mon
        	             if( currentDay == 2){
        	             	  day3 =   86400000 *2; 
        		         }
        	             //tue
        	            if( currentDay == 3){
        	            	day3 =   86400000 * 1; 
        		         }
        	            //wed
        	            if( currentDay == 4){
        	            	day3 =   0 ; 
        		         }   
        	            //thur
        	            if( currentDay == 5){
        	            	day3 =   86400000 * 6; 
        		         }
        		         
        	            //fri
        	            if( currentDay == 6){
        	            	day3 =   86400000 * 5; 
        		         }
        		         
        	            //sat
        	            if( currentDay == 7){
        	            	day3 =   86400000 * 4; 
        		         }
        	            
        	            endTue = endTimeEntryCal.getTimeInMillis() + day3;
                     }else{
                    	 endTue = endTimeEntryCal.getTimeInMillis() + day2;
                     }
                     
                     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
	
	     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmTuedayStart , startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startTue, daysBetweenAlrm , startIntent);
	    
	     
	     //turn off
	     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
	     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmTuedayEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endTue, daysBetweenAlrm , endIntent); 
	    }
	 
	 
	 //////////////////////////////////////////////////////////////////////////////////////
	 
	///wed 
	 if(ifWed == 3){	         
					
			 //sun
		         if( currentDay == 1){
		        	  day3 =    86400000 *3; 
		         }
		         //mon
	             if( currentDay == 2){
	             	  day3 =   86400000 *2; 
		         }
	             //tue
	            if( currentDay == 3){
	            	day3 =   86400000 * 1; 
		         }
	            //wed
	            if( currentDay == 4){
	            	day3 =   0 ; 
		         }   
	            //thur
	            if( currentDay == 5){
	            	day3 =   86400000 * 6; 
		         }
		         
	            //fri
	            if( currentDay == 6){
	            	day3 =   86400000 * 5; 
		         }
		         
	            //sat
	            if( currentDay == 7){
	            	day3 =   86400000 * 4; 
		         }

	    long startWed =  when.getTimeInMillis() + day3;
	    long endWed = endTimeEntryCal.getTimeInMillis() + day3;
	    
	    //set alarm idenfication
        int  alarmWedStart =  covertToInt + 5;
        int alarmWedEnd =  covertToInt + 6;
	    
        
        
        final String TIME_FORMAT = "kk";
        SimpleDateFormat dateTimeFormat15 = new SimpleDateFormat(TIME_FORMAT);
		   String startDateTime15 = dateTimeFormat15.format(endTimeEntryCal.getTime());
		   
		   SimpleDateFormat dateTimeFormat115 = new SimpleDateFormat(TIME_FORMAT);
		   String endDateTime15 = dateTimeFormat115.format(when.getTime());
		   
           
           String go912 = startDateTime15.substring(0,2);

           String go9913 = endDateTime15.substring(0,2);
      //     String goo = endTimeEntryCal + "";
          // String go10 = endDateTime.substring(0,2);
           int go11121 = Integer.parseInt(go9913); 
           
           int go23 = Integer.parseInt(go912); 
                 if(go23 >= 00 && go11121 <= 00){
          //////////////////////////////////////////////////////////////////////////////////////////////////////// 
           ////////////////////////////////////////////////////////////////////////////////////////////////////////
       
             		
    				 //sun
    			         if( currentDay == 1){
    			        	  day4 =    86400000 *4; 
    			         }
    			         //mon
    		             if( currentDay == 2){
    		             	  day4 =   86400000 *3; 
    			         }
    		             //tue
    		            if( currentDay == 3){
    		            	day4 =   86400000 * 2; 
    			         }
    		            //wed
    		            if( currentDay == 4){
    		            	day4 =    86400000 * 1 ; 
    			         }   
    		            //thur
    		            if( currentDay == 5){
    		            	day4 =   0; 
    			         }
    			         
    		            //fri
    		            if( currentDay == 6){
    		            	day4 =   86400000 * 6; 
    			         }
    			         
    		            //sat
    		            if( currentDay == 7){
    		            	day4 =   86400000 * 5; 
    			         }
    	            
    	            endWed = endTimeEntryCal.getTimeInMillis() + day4;
                 }else{
                	 endWed = endTimeEntryCal.getTimeInMillis() + day3;
                 }
                 
                 
		     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmWedStart , startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startWed, daysBetweenAlrm , startIntent);
		    
		     //turn off
		     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
		     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmWedEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endWed, daysBetweenAlrm , endIntent); 
		    }
	 
	 /////////////////////////////////////////////////////////////////////////////////////////
	 
	 
	///Thur
		 if(ifThur == 4){	         
						
				 //sun
			         if( currentDay == 1){
			        	  day4 =    86400000 *4; 
			         }
			         //mon
		             if( currentDay == 2){
		             	  day4 =   86400000 *3; 
			         }
		             //tue
		            if( currentDay == 3){
		            	day4 =   86400000 * 2; 
			         }
		            //wed
		            if( currentDay == 4){
		            	day4 =    86400000 * 1 ; 
			         }   
		            //thur
		            if( currentDay == 5){
		            	day4 =   0; 
			         }
			         
		            //fri
		            if( currentDay == 6){
		            	day4 =   86400000 * 6; 
			         }
			         
		            //sat
		            if( currentDay == 7){
		            	day4 =   86400000 * 5; 
			         }

		    long startThur =  when.getTimeInMillis() + day4;
		    long endThur;
		    
		    //set alarm idenfication
	        int  alarmThurStart =  covertToInt + 7;
	        int alarmThurEnd =  covertToInt + 8;
		    
	        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
	        final String TIME_FORMAT = "kk";
	        SimpleDateFormat dateTimeFormat2009 = new SimpleDateFormat(TIME_FORMAT);
			   String startDateTime2012 = dateTimeFormat2009.format(endTimeEntryCal.getTime());
			   
			   SimpleDateFormat dateTimeFormat2111 = new SimpleDateFormat(TIME_FORMAT);
			   String endDateTime15 = dateTimeFormat2111.format(when.getTime());
			   
	           
	           String fo34 = startDateTime2012.substring(0,2);

	           String bo90 = endDateTime15.substring(0,2);
	      //     String goo = endTimeEntryCal + "";
	          // String go10 = endDateTime.substring(0,2);
	           int eo343 = Integer.parseInt(bo90); 
	           
	           int eo12 = Integer.parseInt(fo34); 
	                 if(eo12 >= 00 && eo343 <= 00){
	          //////////////////////////////////////////////////////////////////////////////////////////////////////// 
	           ////////////////////////////////////////////////////////////////////////////////////////////////////////
	       
	             		
	                	 //sun
				         if( currentDay == 1){
				        	  day5 =    86400000 *5; 
				         }
				         //mon
			             if( currentDay == 2){
			             	  day5 =   86400000 * 4; 
				         }
			             //tue
			            if( currentDay == 3){
			            	day5 =   86400000 * 3; 
				         }
			            //wed
			            if( currentDay == 4){
			            	day5 =    86400000 * 2 ; 
				         }   
			            //thur
			            if( currentDay == 5){
			            	day5 =    86400000  * 1; 
				         }
				         
			            //fri
			            if( currentDay == 6){
			            	day5 =   0; 
				         }
				         
			            //sat
			            if( currentDay == 7){
			            	day5 =   86400000 * 6; 
				         }
	    	            
			            endThur = endTimeEntryCal.getTimeInMillis() + day5;
	                 }else{
	                	 endThur = endTimeEntryCal.getTimeInMillis() + day4;
	                 }
	                 
	           
			     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmThurStart , startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startThur, daysBetweenAlrm , startIntent);
			    
			     
			     //turn off
			     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
			     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmThurEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endThur, daysBetweenAlrm , endIntent); 
			    }
		 
		 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
		///Fri
		 if(ifFri == 5){	         
						
				 //sun
			         if( currentDay == 1){
			        	  day5 =    86400000 *5; 
			         }
			         //mon
		             if( currentDay == 2){
		             	  day5 =   86400000 * 4; 
			         }
		             //tue
		            if( currentDay == 3){
		            	day5 =   86400000 * 3; 
			         }
		            //wed
		            if( currentDay == 4){
		            	day5 =    86400000 * 2 ; 
			         }   
		            //thur
		            if( currentDay == 5){
		            	day5 =    86400000  * 1; 
			         }
			         
		            //fri
		            if( currentDay == 6){
		            	day5 =   0; 
			         }
			         
		            //sat
		            if( currentDay == 7){
		            	day5 =   86400000 * 6; 
			         }

		    long startFri =  when.getTimeInMillis() + day5;
		    long endFri = endTimeEntryCal.getTimeInMillis() + day5;
		    
		  //set alarm idenfication
	        int  alarmThurStart =  covertToInt + 9;
	        int alarmThurEnd =  covertToInt + 10;
		    
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
	        final String TIME_FORMAT = "kk";
	        SimpleDateFormat dateTimeFormat1992 = new SimpleDateFormat(TIME_FORMAT);
			   String startDateTime3023 = dateTimeFormat1992.format(endTimeEntryCal.getTime());
			   
			   SimpleDateFormat dateTimeFormat1980 = new SimpleDateFormat(TIME_FORMAT);
			   String endDateTime123 = dateTimeFormat1980.format(when.getTime());
			   
	           
	           String fo34 = startDateTime3023.substring(0,2);

	           String bo90 = endDateTime123.substring(0,2);
	      //     String goo = endTimeEntryCal + "";
	          // String go10 = endDateTime.substring(0,2);
	           int eo505 = Integer.parseInt(bo90); 
	           
	           int eo1212 = Integer.parseInt(fo34); 
	                 if(eo1212 >= 00 && eo505 <= 00){
	          //////////////////////////////////////////////////////////////////////////////////////////////////////// 
	           ////////////////////////////////////////////////////////////////////////////////////////////////////////
	       
	             		
	                	 //sun
				         if( currentDay == 1){
				        	  day6 =    86400000 * 6; 
				         }
				         //mon
			             if( currentDay == 2){
			             	  day6 =   86400000 * 5; 
				         }
			             //tue
			            if( currentDay == 3){
			            	day6 =   86400000 * 4; 
				         }
			            //wed
			            if( currentDay == 4){
			            	day6 =    86400000 * 3 ; 
				         }   
			            //thur
			            if( currentDay == 5){
			            	day6 =    86400000  * 2;
				         }
				         
			            //fri
			            if( currentDay == 6){
			            	day6 =   86400000  * 1; 
				         }
				         
			            //sat
			            if( currentDay == 7){
			            	day6 =   0; 
				         }
	    	            
			            endFri = endTimeEntryCal.getTimeInMillis() + day6;
	                 }else{
	                	 endFri = endTimeEntryCal.getTimeInMillis() + day5;
	                 }
	                 
	                 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
			     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmThurStart , startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startFri, daysBetweenAlrm , startIntent);
			     
			     //turn off
			     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
			     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmThurEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endFri, daysBetweenAlrm , endIntent); 
			    
			    }
		 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 
		///sat
		 if(ifSat == 6){	         
						
				 //sun
			         if( currentDay == 1){
			        	  day6 =    86400000 * 6; 
			         }
			         //mon
		             if( currentDay == 2){
		             	  day6 =   86400000 * 5; 
			         }
		             //tue
		            if( currentDay == 3){
		            	day6 =   86400000 * 4; 
			         }
		            //wed
		            if( currentDay == 4){
		            	day6 =    86400000 * 3 ; 
			         }   
		            //thur
		            if( currentDay == 5){
		            	day6 =    86400000  * 2;
			         }
			         
		            //fri
		            if( currentDay == 6){
		            	day6 =   86400000  * 1; 
			         }
			         
		            //sat
		            if( currentDay == 7){
		            	day6 =   0; 
			         }

		    long startSat =  when.getTimeInMillis() + day6;
		    long endSat = endTimeEntryCal.getTimeInMillis() + day6;
		    
		    //set alarm idenfication
	        int  alarmFriStart =  covertToInt + 11;
	        int alarmFriEnd =  covertToInt + 12;
	        
	        
	        final String TIME_FORMAT = "kk";
	        SimpleDateFormat dateTimeFormat1515 = new SimpleDateFormat(TIME_FORMAT);
			   String startDateTime4080 = dateTimeFormat1515.format(endTimeEntryCal.getTime());
			   
			   SimpleDateFormat dateTimeFormat1955 = new SimpleDateFormat(TIME_FORMAT);
			   String endDateTime2060 = dateTimeFormat1955.format(when.getTime());
			   
	           
	           String fo34 = startDateTime4080.substring(0,2);

	           String golow = endDateTime2060.substring(0,2);
	      //     String goo = endTimeEntryCal + "";
	          // String go10 = endDateTime.substring(0,2);
	           int imhere = Integer.parseInt(golow); 
	           
	           int eo1212 = Integer.parseInt(fo34); 
	                 if(eo1212 >= 00 && imhere <= 00){
	          //////////////////////////////////////////////////////////////////////////////////////////////////////// 
	           ////////////////////////////////////////////////////////////////////////////////////////////////////////
	       
	             		
	                	 //sun
				         if( currentDay == 1){
				        	  day7 =    0; 
				         }
				         //mon
			             if( currentDay == 2){
			             	  day7 =   86400000 * 6; 
				         }
			             //tue
			            if( currentDay == 3){
			            	day7 =   86400000 * 5; 
				         }
			            //wed
			            if( currentDay == 4){
			            	day7 =    86400000 * 4 ; 
				         }   
			            //thur
			            if( currentDay == 5){
			            	day7 =    86400000  * 3;
				         }
				         
			            //fri
			            if( currentDay == 6){
			            	day7 =   86400000  * 2; 
				         }
				         
			            //sat
			            if( currentDay == 7){
			            	day7 =    86400000  * 1; 
				         }
	    	            
			            endSat = endTimeEntryCal.getTimeInMillis() + day7;
	                 }else{
	                	 endSat = endTimeEntryCal.getTimeInMillis() + day6;
	                 }
		    
			     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmFriStart , startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startSat, daysBetweenAlrm , startIntent);
			    
			     //turn off
			     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
			     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmFriEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endSat, daysBetweenAlrm , endIntent); 
			     
			    }
		 
		 /////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///sun
		 if(ifSun == 7){	         
						
				 //sun
			         if( currentDay == 1){
			        	  day7 =    0; 
			         }
			         //mon
		             if( currentDay == 2){
		             	  day7 =   86400000 * 6; 
			         }
		             //tue
		            if( currentDay == 3){
		            	day7 =   86400000 * 5; 
			         }
		            //wed
		            if( currentDay == 4){
		            	day7 =    86400000 * 4 ; 
			         }   
		            //thur
		            if( currentDay == 5){
		            	day7 =    86400000  * 3;
			         }
			         
		            //fri
		            if( currentDay == 6){
		            	day7 =   86400000  * 2; 
			         }
			         
		            //sat
		            if( currentDay == 7){
		            	day7 =    86400000  * 1; 
			         }

		    long startSun =  when.getTimeInMillis() + day7;
		    long endSun = endTimeEntryCal.getTimeInMillis() + day7;
		    
		    //set alarm idenfication
	        int  alarmSatStart =  covertToInt + 13;
	        int alarmSatEnd =  covertToInt + 14;
	        
	        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
	        final String TIME_FORMAT = "kk";
	        SimpleDateFormat dateTimeFormat5050 = new SimpleDateFormat(TIME_FORMAT);
			   String startDateTime4022 = dateTimeFormat5050.format(endTimeEntryCal.getTime());
			   
			   SimpleDateFormat dateTimeFormat1846 = new SimpleDateFormat(TIME_FORMAT);
			   String endDateTime3077 = dateTimeFormat1846.format(when.getTime());
			   
	           
	           String sean92 = startDateTime4022.substring(0,2);

	           String goho30 = endDateTime3077.substring(0,2);
	      //     String goo = endTimeEntryCal + "";
	          // String go10 = endDateTime.substring(0,2);
	           int imnothere = Integer.parseInt(goho30); 
	           
	           int heyhowareyou = Integer.parseInt(sean92); 
	                 if(heyhowareyou >= 00 && imnothere <= 00){
	          //////////////////////////////////////////////////////////////////////////////////////////////////////// 
	           ////////////////////////////////////////////////////////////////////////////////////////////////////////
	       
	             		
	                	  if( currentDay == 1){
	        	        	  day1 =    86400000 * 1; 
	        	         }
	                     if( currentDay == 2){
	                     	  day1 =   0; 
	        	         }
	                    if( currentDay == 3){
	                    	day1 =   86400000 * 6; 
	        	         }
	                    if( currentDay == 4){
	                    	day1 =   86400000 * 5 ; 
	        	         }   
	                    //thur
	                    if( currentDay == 5){
	                    	day1 =   86400000 * 4; 
	        	         }
	        	         
	                    
	                    if( currentDay == 6){
	                    	day1 =   86400000 * 3; 
	        	         }
	        	         
	                    
	                    if( currentDay == 7){
	                    	day1 =   86400000 * 2; 
	        	         }
	    	            
			            endSun = endTimeEntryCal.getTimeInMillis() + day1;
	                 }else{
	                	 endSun = endTimeEntryCal.getTimeInMillis() + day7;
	                 }
	                 
	        
			     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmSatStart , startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, startSun, daysBetweenAlrm , startIntent);
			    
			     //turn off
			     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
			     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmSatEnd , endAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			     setAlarm.setRepeating(AlarmManager.RTC_WAKEUP, endSun, daysBetweenAlrm , endIntent); 
			    }
	
		 	
	 if(something == 12){
		
		    
		    //set alarm idenfication
	        int  alarmNow =  covertToInt + 15;
	        int alarmNowEnd =  covertToInt + 16;
		    
	        
	        SimpleDateFormat dateTimeFormat1515 = new SimpleDateFormat(TIME_FORMAT);
			   String currenttime = dateTimeFormat1515.format(System.currentTimeMillis());
			   
			   SimpleDateFormat dateTimeFormat1955 = new SimpleDateFormat(TIME_FORMAT);
			   String endDateTime2060 = dateTimeFormat1955.format(endTimeEntryCal.getTime());
			   
	           
	           String nowtime = currenttime.substring(0,2);

	           String golow = endDateTime2060.substring(0,2);
	      //     String goo = endTimeEntryCal + "";
	          // String go10 = endDateTime.substring(0,2);
	           int imhere = Integer.parseInt(golow); 
	           
	           int eo1212 = Integer.parseInt(nowtime); 
	                
	           SimpleDateFormat dateTimeFormatdate = new SimpleDateFormat(TIME_FORMAT);//*******************************************************************think this can all be taken out
			   String startDateTimenow = dateTimeFormatdate.format(endTimeEntryCal.getTime());
			   
			   SimpleDateFormat dateTimeFormatdate1 = new SimpleDateFormat(TIME_FORMAT);
			   String endDateTimenow = dateTimeFormatdate1.format(when.getTime());
			   
	           
	           String fo34 = startDateTimenow.substring(0,2);

	           String nowitends = endDateTimenow.substring(0,2);
	      //     String goo = endTimeEntryCal + "";
	          // String go10 = endDateTime.substring(0,2);
	           int ohya = Integer.parseInt(nowitends); 
	           
	       	long endtimenow = 0;
	           
	           int haaaohh = Integer.parseInt(fo34); 
	                
	           String ampmstart = DateUtils.getAMPMString(when.get(Calendar.AM_PM));
	           String ampmend = DateUtils.getAMPMString(endTimeEntryCal.get(Calendar.AM_PM));
	           Toast.makeText( setAndroidAlarmContext,"start: "+ ampmstart, Toast.LENGTH_SHORT).show();	
	           Toast.makeText( setAndroidAlarmContext,"end: "+ ampmend, Toast.LENGTH_SHORT).show();	
				// Toast.makeText(getApplicationContext(), ampm , Toast.LENGTH_SHORT).show();	  		
	           if(ampmstart.equals("pm") && ampmend.equals("am")){ //**********************************************************sometimes does not work
	                	 
	                	 endtimenow =   86400000;
	                	 Toast.makeText( setAndroidAlarmContext, "itworked", Toast.LENGTH_SHORT).show();	
	                 }
	           
	           
	           
	           if(eo1212 > imhere ){
	                	 
	                 
		    PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmNow, startAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
		     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis() * 86400000, startIntent);
		     
		     
		   //  int gei = Integer.valueOf(timeId);

		    
		     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
		     endAlarmIntent.putExtra("DeleteMeBoy", (double)timeId+1); 
		     PendingIntent emdIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmNowEnd, endAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
		     setAlarm.set(AlarmManager.RTC_WAKEUP,  endTimeEntryCal.getTimeInMillis() * 86400000 + endtimenow, emdIntent);
		     
	                 }else{
	                	    PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmNow, startAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
	           		     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis() , startIntent);
	           		     
	           		     
	           		   //  int gei = Integer.valueOf(timeId);

	           		    
	           		     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
	           		     endAlarmIntent.putExtra("DeleteMeBoy", (long)timeId+1); 
	           		     PendingIntent emdIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmNowEnd, endAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
	           		     setAlarm.set(AlarmManager.RTC_WAKEUP,  endTimeEntryCal.getTimeInMillis() + endtimenow, emdIntent); 
	                	 
	                 }
	 
	}}
	 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 

	/* end silent mode***********************************dont think this needs to be there
	     public void endAlarm(Long timeId, Calendar when, int endTimeCheck){
			 Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
		     PendingIntent endIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, 2011 , endAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
		     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis(), endIntent); 
		     
		     
	}*/
	
	public void overrightDelete(Long timeId, Calendar when){
 
		//start alarm - needed to delete start alarms
		Intent deleteStartAlarm = new Intent(setAndroidAlarmContext, DontSleepAndroid.class);
	
		
 // end alarm -- needed to delete end alarms
         Intent overright = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
		 
	//	overright.putExtra(DatabaseStore.kEY_ROWID, (long)timeId); 
		 
		//Cursor reminder = getDatabaseInfo.fetchSilent(timeId);
		Integer alarmidInt = timeId.intValue();
	//	  String alarmid = reminder.getString(reminder.getColumnIndex(DatabaseStore.ALARM_ID)); 
//covert string to int
	//	  int alarmidInt = Integer.parseInt(alarmid);
		   
         
		//monday start and end deletion
		 PendingIntent MonDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 1 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000 , MonDelete);
	     
	
	     // Tue start and end deletion
	     PendingIntent TueDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 3 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, TueDelete);
	   
	     
	     // Wed start and end deletion
	     PendingIntent WedDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 5 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, WedDelete);
	     
	 
	     // Thur start and end deletion
	     PendingIntent ThurDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 7 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, ThurDelete);
	     
	    
	     
	     // Fri start and end deletion
	     PendingIntent FriDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 9 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, FriDelete);
	     
	   
	     
	     // Sat start and end deletion
	     PendingIntent SatDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 11 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, SatDelete);
	     
	  
	     
	     // Sun start and end deletion
	     PendingIntent SunDelete = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 13 , overright, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, SunDelete);
	     
	
	     
	     PendingIntent NowDelete= PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 15 , overright,  PendingIntent.FLAG_ONE_SHOT);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, NowDelete);
	     
	     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	     PendingIntent SunDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 14 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, SunDelete1);
	     
	     
	     PendingIntent NowDelete1= PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 16 , deleteStartAlarm,  PendingIntent.FLAG_ONE_SHOT);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, NowDelete1);
	     
	     
	     PendingIntent SatDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 12 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, SatDelete1);
	     
	     PendingIntent FriDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 10 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, FriDelete1);
	     
	     PendingIntent ThurDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 8 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, ThurDelete1);
	     
	     PendingIntent WedDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 6 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, WedDelete1);
	     
   	     
	     PendingIntent TueDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 4 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, TueDelete1);
	
	     PendingIntent MonDelete1 = PendingIntent.getBroadcast(setAndroidAlarmContext, alarmidInt + 2 , deleteStartAlarm, 0);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis()+ 1000, MonDelete1);
	     //cancel alarm monday for start and end time alarms
	     setAlarm.cancel(MonDelete);
	    
	     
	     //cancel alarm Tue for start and end time alarms
	     setAlarm.cancel(TueDelete);
	    
	     //cancel alarm Wed for start and end time alarms
	     setAlarm.cancel(WedDelete);
	  
	     
	     //cancel alarm Thur for start and end time alarms
	     setAlarm.cancel(ThurDelete);
	    
	     
	     //cancel alarm Fri for start and end time alarms
	     setAlarm.cancel(FriDelete);
	   
	     
	     //cancel alarm Sat for start and end time alarms
	     setAlarm.cancel(SatDelete);
	     
	     
	     //cancel alarm Sun for start and end time alarms
	     setAlarm.cancel(SunDelete);
	     
	     
	     setAlarm.cancel(NowDelete);
	    
	     setAlarm.cancel(NowDelete1);
	     
	     setAlarm.cancel(SunDelete1);
	     setAlarm.cancel(SatDelete1);
	     setAlarm.cancel(FriDelete1);
	     setAlarm.cancel(ThurDelete1);
	     setAlarm.cancel(WedDelete1);
	     setAlarm.cancel(TueDelete1);
	     setAlarm.cancel(MonDelete1);
	    }
	
	public void QuickMute(Long timeId, long when, String vibrateQuickMute){
		
		 Intent startAlarmIntent = new Intent(setAndroidAlarmContext, DontSleepAndroid.class); 
	     if(vibrateQuickMute == "On"){
		 startAlarmIntent.putExtra("delete",(double) 5); 
	     }else{
	    	 startAlarmIntent.putExtra("delete", 1.1); //*****************************************************change
	     }
		long CurrentTime = System.currentTimeMillis();
		///long CurrentTime1 = System.currentTimeMillis()+ when;
	String deletealarm = "sean";
		
		
	     PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, 2232323 , startAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, CurrentTime, startIntent);
	     
	     
	     Intent startAlarmIntent1 = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
	     startAlarmIntent1.putExtra("ss",(double) 2); 
	     
	     PendingIntent startIntent1 = PendingIntent.getBroadcast(setAndroidAlarmContext, 343343 , startAlarmIntent1, PendingIntent.FLAG_ONE_SHOT);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when, startIntent1);
	     
	   
	     
	     
    }
	
	public void overrightDeleteQuickMute(long when){
		Intent overright = new Intent(setAndroidAlarmContext, DontSleepAndroid.class); 
		 
		 
		 /// might be problem other alarms use all dontsleepandroid.class but this one needs endtimeandroid why???
	//	  PendingIntent startIntent = PendingIntent.getBroadcast(setAndroidAlarmContext, 2232323 , overright, PendingIntent.FLAG_ONE_SHOT);
		//     setAlarm.set(AlarmManager.RTC_WAKEUP, when, startIntent);
	 //    overright.putExtra("delete", 3); 
	 //    setAlarm.cancel(startIntent);
	     
	     Intent endAlarmIntent = new Intent(setAndroidAlarmContext, EndTimeAndroid.class);
	     PendingIntent quickMuteEnd = PendingIntent.getBroadcast(setAndroidAlarmContext, 343343 , endAlarmIntent, PendingIntent.FLAG_ONE_SHOT);
	     setAlarm.set(AlarmManager.RTC_WAKEUP, when, quickMuteEnd);
	     
	
	     setAlarm.cancel(quickMuteEnd);
}
}
