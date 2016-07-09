package com.iphonmusic.child.detailonline;

import com.iphonmusic.entity.EntityZingMp3;

import android.widget.ImageView;

public interface DelegateMusicOnlineDetail {

	public void updateView(EntityZingMp3 mp3,boolean isPlay);

	public ImageView getImageViewPlay();
	
	public void updateTime();
}
