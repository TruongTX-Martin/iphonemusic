package com.iphonmusic.menubottom;

import com.iphonmusic.entity.EntitySong;

import android.widget.ImageView;

public interface DelegateBottom {

	public ImageView getImagePlay();
	public ImageView getImageNext();
	public void visibleRootView(boolean b);
	public void updateView(EntitySong entitySong);
}
