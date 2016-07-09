package com.iphonmusic.menubottom;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class BlockBottom implements DelegateBottom {

	private View mRootView;

	private ImageView img_play;
	private ImageView img_next;
	private TextView txt_name_song;
	private TextView txt_name_singer;

	public BlockBottom(View view) {
		this.mRootView = view;
	}

	public void setOnPlayOnClickListener(OnClickListener listener) {
		img_play.setOnClickListener(listener);
	}

	public void setOnNextOnClickListener(OnClickListener listener) {
		img_next.setOnClickListener(listener);
	}

	public void setOnLayoutClickListener(OnClickListener listener) {
		mRootView.setOnClickListener(listener);
	}

	void initView() {
		img_play = (ImageView) mRootView.findViewById(Rconfig.getInstance().id(
				"img_icon_play"));
		img_next = (ImageView) mRootView.findViewById(Rconfig.getInstance().id(
				"img_icon_next"));
		txt_name_song = (TextView) mRootView.findViewById(Rconfig.getInstance()
				.id("txt_song_name"));
		txt_name_singer = (TextView) mRootView.findViewById(Rconfig
				.getInstance().id("txt_singer"));
//		if (Instance.LISTSONG.size() == 1) {
//			BaseManager.getIntance().setCurrentSong(
//					Instance.LISTSONG.get(0));
//			BaseManager.getIntance().playMusic();
//			BaseManager.getIntance().pauseMusic();
//		}
//		if (BaseManager.getIntance().getCurrentSong() != null) {
//			updateView(BaseManager.getIntance().getCurrentSong());
//		}
		txt_name_song.setSelected(true);
		if(Instance.LISTSONG.size() > 0){
			updateView(Instance.LISTSONG.get(0));
			BaseManager.getIntance().setCurrentSong(Instance.LISTSONG.get(0));
		}
	}

	@Override
	public ImageView getImagePlay() {
		return img_play;
	}

	@Override
	public ImageView getImageNext() {
		return img_next;
	}

	@Override
	public void visibleRootView(boolean b) {
		if (b) {
			mRootView.setVisibility(View.VISIBLE);
		} else {
			mRootView.setVisibility(View.GONE);
		}
	}

	@Override
	public void updateView(EntitySong entitySong) {
		txt_name_song.setText(entitySong.getSong_name());
		txt_name_singer.setText(entitySong.getSong_singer());
	}
}
