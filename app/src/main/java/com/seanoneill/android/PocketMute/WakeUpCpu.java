package com.seanoneill.android.PocketMute;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public abstract class WakeUpCpu extends IntentService {
       abstract void normalMode(Intent intent); 

	   public static final String LOCK_NAME_STATIC="com.seanoneill.android.PocketMute";
	   private static PowerManager.WakeLock lockStatic = null;
	     
	   public static void lock(Context context){
	     getLock(context).acquire();
	     }
	   
	     synchronized private static PowerManager.WakeLock getLock(Context context) {
	    	 if(lockStatic==null){
	    		 PowerManager dontGo = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
	    		 lockStatic= dontGo.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LOCK_NAME_STATIC);
	    		 lockStatic.setReferenceCounted(true);
	    		 }
	                  return(lockStatic);
	             }
	     
	  public WakeUpCpu(String name) {
		   super(name);
		}
 
	    @Override
	 	final protected void onHandleIntent(Intent intent) {

	        try{
	    	  normalMode(intent);
	    	  }
	        finally{
	         getLock(this).release();
	    	 }
	      }
       }
