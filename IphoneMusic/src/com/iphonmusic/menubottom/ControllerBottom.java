package com.iphonmusic.menubottom;

import android.view.View;
import android.view.View.OnClickListener;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.child.detail.FragmentDetailPlay;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class ControllerBottom {

	private DelegateBottom mDelegate;
	private OnClickListener onPlayListener, onNextListener, onLayoutListener;
	private boolean isFirstPlay = true;

	public void setDelegate(DelegateBottom mDelegate) {
		this.mDelegate = mDelegate;
	}

	public ControllerBottom() {
	}

	public void setIsFirstPlay(boolean b) {
		isFirstPlay = false;
	}

	public boolean getIsFirstPlay() {
		return isFirstPlay;
	}

	public OnClickListener getOnPlayListener() {
		return onPlayListener;
	}

	public OnClickListener getOnNextListener() {
		return onNextListener;
	}

	public OnClickListener getOnLayoutListener() {
		return onLayoutListener;
	}

	public void init() {
		onPlayListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Config.getInstance().getIsPlay()) {
					updateImagePlay(false);
					Config.getInstance().setPlay(false);
					BaseManager.getIntance().pauseMusic();
				} else {
					Config.getInstance().setPlay(true);
					updateImagePlay(true);
					if (isFirstPlay) {
						BaseManager.getIntance().playMusic();
						isFirstPlay = false;
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
			}
		};

		onLayoutListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentDetailPlay detailPlay = FragmentDetailPlay
						.newInstance();
				BaseManager.getIntance().replaceFragment(detailPlay);
			}
		};
		// if (Instance.LISTSONG.size() > 0) {
		// EntitySong entitySong = Instance.LISTSONG.get(0);
		// updateView(entitySong);
		// }
	}

	public void updateImagePlay(boolean status) {
		if (status) {
			mDelegate.getImagePlay().setImageResource(
					Rconfig.getInstance().drawable("ic_play"));
		} else {
			mDelegate.getImagePlay().setImageResource(
					Rconfig.getInstance().drawable("ic_pause"));
		}
	}

	public void updateView(EntitySong entitySong) {
		mDelegate.updateView(entitySong);
	}

	public void visibleRootView(boolean b) {
		mDelegate.visibleRootView(b);
	}

}
