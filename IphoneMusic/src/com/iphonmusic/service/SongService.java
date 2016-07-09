package com.iphonmusic.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.iphonmusic.R;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;

public class SongService extends Service {

	private static boolean currentVersionSupportLockScreenControls = false;
	int NOTIFICATION_ID = 1112;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent.getAction().equals(ConstanceService.START_ACTION_FOREGROUND)) {
			showNotification();
		}else if(intent.getAction().equals(ConstanceService.NOTIFY_DELETE)){
			stopForeground(true);
			stopSelf();
		}
		return START_STICKY;
	}

	private void showNotification() {
		RemoteViews simpleContentView = new RemoteViews(getApplicationContext()
				.getPackageName(), R.layout.custom_notification);
		Notification notification = new NotificationCompat.Builder(
				getApplicationContext()).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Xuan Truong").build();
		setListeners(simpleContentView);
		notification.contentView = simpleContentView;
		if (Config.getInstance().getIsPlay()) {
			notification.contentView.setViewVisibility(R.id.btnPause,
					View.VISIBLE);
			notification.contentView.setViewVisibility(R.id.btnPlay, View.GONE);
		} else {
			notification.contentView
					.setViewVisibility(R.id.btnPause, View.GONE);
			notification.contentView.setViewVisibility(R.id.btnPlay,
					View.VISIBLE);
		}
		notification.contentView.setTextViewText(R.id.textSongName, BaseManager
				.getIntance().getCurrentSong().getSong_name());
		notification.contentView.setTextViewText(R.id.textAlbumName,
				BaseManager.getIntance().getCurrentSong().getSong_singer());
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		startForeground(NOTIFICATION_ID, notification);

	}

	public void setListeners(RemoteViews view) {
		Intent delete = new Intent(ConstanceService.NOTIFY_DELETE);
		Intent pause = new Intent(ConstanceService.NOTIFY_PAUSE);
		Intent next = new Intent(ConstanceService.NOTIFY_NEXT);
		Intent play = new Intent(ConstanceService.NOTIFY_PLAY);

		PendingIntent pDelete = PendingIntent.getBroadcast(
				getApplicationContext(), 0, delete,
				PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.btnDelete, pDelete);

		PendingIntent pPause = PendingIntent.getBroadcast(
				getApplicationContext(), 0, pause,
				PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.btnPause, pPause);

		PendingIntent pNext = PendingIntent.getBroadcast(
				getApplicationContext(), 0, next,
				PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.btnNext, pNext);

		PendingIntent pPlay = PendingIntent.getBroadcast(
				getApplicationContext(), 0, play,
				PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.btnPlay, pPlay);

	}

}
