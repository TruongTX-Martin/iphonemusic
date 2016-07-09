package com.iphonmusic.child.detailonline;

import android.view.View;
import android.view.View.OnClickListener;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.entity.EntityZingMp3;

public class ControllerMusicOnlineDetail {

	private OnClickListener onPlayListener, onNextListener, onPreviousListener;
	private DelegateMusicOnlineDetail mDelegate;

	private boolean isNewPlay;

	public void setIsNewPlay(boolean input) {
		this.isNewPlay = input;
	}

	public void setmDelegate(DelegateMusicOnlineDetail mDelegate) {
		this.mDelegate = mDelegate;
	}

	public OnClickListener getOnPlayListener() {
		return onPlayListener;
	}

	public OnClickListener getOnNextListener() {
		return onNextListener;
	}

	public OnClickListener getOnPreviousListener() {
		return onPreviousListener;
	}

	public ControllerMusicOnlineDetail() {
		// mPlayer = new MediaPlayer();
	}

	public void initListener() {
		if (isNewPlay) {
			BaseManager.getIntance().playMusicOnline();
		} else {
			if (Config.getInstance().getPlayOnline()) {
				BaseManager.getIntance().continueMusicOnline();
				mDelegate.updateView(BaseManager.getIntance()
						.getCurrentOnline(), true);
			} else {
				BaseManager.getIntance().playMusicOnline();
			}
		}
		onPlayListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Config.getInstance().getPlayOnline()) {
					BaseManager.getIntance().pauseMusicOnline();
				} else {
					BaseManager.getIntance().continueMusicOnline();
				}
			}
		};
		onNextListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseManager.getIntance().nextSongOnline();

			}
		};
		onPreviousListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseManager.getIntance().previousSongOnline();
			}
		};
	}

}
