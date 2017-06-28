package com.seanoneill.android.PocketMute;

import android.content.Intent;
import android.media.AudioManager;

 public class SetSilentNormalMode extends  WakeUpCpu {
     
	 private AudioManager mmAudioManager;
	
	 public SetSilentNormalMode(){
		super("SilentService");
	 }
	
	 @Override
	void normalMode(Intent intent){
		 if(intent != null){
				// neeed to get extas from other file
			//int g = 	extras.
			 
			 double selectedId = intent.getDoubleExtra("delete", 1.1);
			 
			 if(selectedId == 5.0){// **********this is for setting vibate if users picks it
					mmAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
		              mmAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			 }	
			 if(selectedId == 3){
					mmAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
		              mmAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			 }
			 
			 if(selectedId == 9){
				 
			 }if(selectedId == 1.1){//***** default if user sets normal alarm it will set slient
				mmAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
	              mmAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	            //put in if statament that if they picked repeat then it would reset
			 }else{
				 
			 }
			}else{
			
		  }
	  }
 }
