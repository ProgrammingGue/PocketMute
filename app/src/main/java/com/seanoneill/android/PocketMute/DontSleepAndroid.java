package com.seanoneill.android.PocketMute;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DontSleepAndroid extends BroadcastReceiver {

	
	@Override	
	public void onReceive(Context context, Intent intent) {
		long rowid = intent.getExtras().getLong(DatabaseStore.kEY_ROWID);
		WakeUpCpu.lock(context);
		
		double selectedId10 = intent.getDoubleExtra("delete", 1.1);
	 	 Toast.makeText(  context, selectedId10+"", Toast.LENGTH_SHORT).show();	
		Intent i = new Intent(context, SetSilentNormalMode.class);
		i.putExtra("delete", selectedId10);
		context.startService(i);
	    }
	}
	

