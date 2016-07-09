package com.iphonmusic.child.detail;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Rconfig;

import android.view.View;
import android.view.View.OnClickListener;

public class ControllerDetailPlay {

	private DelegateDetailPlay delegate;

	private OnClickListener onPlayListener, onNextListener, onPreviousListener;

	public OnClickListener getOnPlayListener() {
		return onPlayListener;
	}

	public OnClickListener getOnNextListener() {
		return onNextListener;
	}

	public OnClickListener getOnPreviousListener() {
		return onPreviousListener;
	}

	public void setDelegate(DelegateDetailPlay delegate) {
		this.delegate = delegate;
	}

	public ControllerDetailPlay() {
	}

	public void initListener() {
		onPlayListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Config.getInstance().getIsPlay()) {
					delegate.getImageViewPlay().setImageResource(Rconfig.getInstance().drawable(
							"ic_pause"));
					Config.getInstance().setPlay(false);
					BaseManager.getIntance().pauseMusic();
				} else {
					Config.getInstance().setPlay(true);
					delegate.getImageViewPlay().setImageResource(Rconfig.getInstance().drawable(
							"ic_play"));
					if (BaseManager.getIntance().getControllerBottom().getIsFirstPlay()) {
						BaseManager.getIntance().playMusic();
						BaseManager.getIntance().getControllerBottom().setIsFirstPlay(false);
					} else {
						BaseManager.getIntance().continueMusic();
					}
				}
			}
		};
		onNextListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseManager.getIntance().nextSong();
				delegate.updateView();
			}
		};
		onPreviousListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseManager.getIntance().previousSong();
				delegate.updateView();
			}
		};
	}

}
