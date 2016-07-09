package com.iphonmusic.child.musiconline;

import com.iphonmusic.entity.EntityZingMp3;

import android.widget.EditText;

public interface DelegateMusicOnline {
	
	public EditText getEditTextSearch();
	
	public void updateView(String result);
	public void updateProgressBar(boolean input);
	public void updateMusicOnline(EntityZingMp3 mp3);

}
