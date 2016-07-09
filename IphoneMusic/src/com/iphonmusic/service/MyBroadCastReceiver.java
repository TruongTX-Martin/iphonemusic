package com.iphonmusic.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iphonmusic.base.manager.BaseManager;

public class MyBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ConstanceService.NOTIFY_PLAY)) {
			BaseManager.getIntance().continueMusic();
			Log.e("Service=========>", "Play");
		} else if (intent.getAction().equals(ConstanceService.NOTIFY_PAUSE)) {
			BaseManager.getIntance().pauseMusic();
			Log.e("Service=========>", "Pause");
		} else if (intent.getAction().equals(ConstanceService.NOTIFY_NEXT)) {
			BaseManager.getIntance().nextSong();
			Log.e("Service=========>", "Next");
		} else if (intent.getAction().equals(ConstanceService.NOTIFY_DELETE)) {
			Intent serviceIntent = new Intent(BaseManager.getIntance()
					.getCurrentActivity(), SongService.class);
			serviceIntent.setAction(ConstanceService.NOTIFY_DELETE);
			BaseManager.getIntance().getCurrentActivity()
					.startService(serviceIntent);
		}
	}

}
