package com.seanoneill.android.PocketMute;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EndTimeAndroid extends BroadcastReceiver{
	
		@Override	
		public void onReceive(Context context, Intent intent) {
			int endtimecheck = 1;
			WakeUpCpu.lock(context);
			
			double selectedId1 = intent.getDoubleExtra("ss", 1.0);
			 double selectedId10 = intent.getDoubleExtra("deleteme", 1.0);
			 double byebyeMe = intent.getExtras().getDouble("DeleteMeBoy", 0.0);
			 
			Intent i = new Intent(context, EndShortSilentMode.class);
			
			if(selectedId1 == 2.0){
		    i.putExtra("id", selectedId1);
			}
			if(selectedId10 == 10.0){
				i.putExtra("delete", selectedId10);
				}
			if(byebyeMe > 0.0){
				 i.putExtra("idid", (double)byebyeMe);
			}else{
				i.putExtra("idid", 0.0);
			}
			context.startService(i);
		    }
		

}
