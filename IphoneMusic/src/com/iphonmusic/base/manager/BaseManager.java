package com.iphonmusic.base.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.ContactsContract.DeletedContacts;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.child.detail.DelegateDetailPlay;
import com.iphonmusic.child.detailonline.ControllerMusicOnlineDetail;
import com.iphonmusic.child.detailonline.DelegateMusicOnlineDetail;
import com.iphonmusic.child.musiconline.ControllerMusicOnline;
import com.iphonmusic.child.musiconline.DelegateMusicOnline;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.DatabaseHandler;
import com.iphonmusic.entity.EntitySong;
import com.iphonmusic.entity.EntityZingMp3;
import com.iphonmusic.menubottom.ControllerBottom;
import com.iphonmusic.menutop.controller.MenuTopController;
import com.iphonmusic.service.ConstanceService;
import com.iphonmusic.service.SongService;
import com.iphonmusic.slidemenu.controller.PhoneSlideMenuController;

public class BaseManager {
	private PhoneSlideMenuController mSlideMenuController;
	private Activity mCurrentActivity;
	private static BaseManager instance;
	private Context mCurrentContext;
	protected MenuTopController mMenuTopController;
	private FragmentManager mManager;
	private ControllerBottom mControllerBottom;
	private EntitySong mCurrentSong;
	private MediaPlayer mPlayer = new MediaPlayer();
	private MediaPlayer mPlayerOnline = new MediaPlayer();

	private DatabaseHandler databaseHandler;
	private EntityZingMp3 mCurrentOnline;
	private ArrayList<EntityZingMp3> mListEntityOnline;
	private DelegateMusicOnlineDetail mDelegateOnlineDetail;
	private DelegateMusicOnline mDelegateOnline;
	private DelegateDetailPlay mDelegateDetail;

	// private ControllerMusicOnline mControllerMusicOnline;

	public static BaseManager getIntance() {
		if (null == instance) {
			instance = new BaseManager();
		}
		return instance;
	}

	public void setDelegateDetail(DelegateDetailPlay mDelegateDetail) {
		this.mDelegateDetail = mDelegateDetail;
	}

	public void setDelegateOnline(DelegateMusicOnline mDelegateOnline) {
		this.mDelegateOnline = mDelegateOnline;
	}

	public void setDelegateOnlineDetail(
			DelegateMusicOnlineDetail mDelegateOnlineDetail) {
		this.mDelegateOnlineDetail = mDelegateOnlineDetail;
	}

	public void setCurrentOnline(EntityZingMp3 mCurrentOnline) {
		this.mCurrentOnline = mCurrentOnline;
		mDelegateOnline.updateMusicOnline(mCurrentOnline);

	}

	public EntityZingMp3 getCurrentOnline() {
		return mCurrentOnline;
	}

	public void setListEntityOnline(ArrayList<EntityZingMp3> mListEntityOnline) {
		this.mListEntityOnline = mListEntityOnline;
	}

	public ArrayList<EntityZingMp3> getListEntityOnline() {
		return mListEntityOnline;
	}

	public MediaPlayer getPlayer() {
		return mPlayer;
	}

	public MediaPlayer getPlayerOnline() {
		return mPlayerOnline;
	}

	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}

	public void setCurrentSong(EntitySong mCurrentSong) {
		this.mCurrentSong = mCurrentSong;
		if (mControllerBottom != null) {
			mControllerBottom.updateView(mCurrentSong);
		}

	}

	public EntitySong getCurrentSong() {
		return mCurrentSong;
	}

	public Context getCurrentContext() {
		return mCurrentContext;
	}

	public Activity getCurrentActivity() {
		return mCurrentActivity;
	}

	public void setControllerBottom(ControllerBottom mControllerBottom) {
		this.mControllerBottom = mControllerBottom;
	}

	public ControllerBottom getControllerBottom() {
		return mControllerBottom;
	}

	public void updateBottom() {
		mControllerBottom.updateView(mCurrentSong);
	}

	public void setCurrentActivity(Activity mCurrentActivity) {
		this.mCurrentActivity = mCurrentActivity;
	}

	public void setCurrentContext(Context mCurrentContext) {
		this.mCurrentContext = mCurrentContext;
	}

	public void setChildFragment(FragmentManager childFragment) {
		mManager = childFragment;
	}

	public FragmentManager getChilFragmentManager() {
		return mManager;
	}

	public FragmentManager getManager() {
		return mManager;
	}

	public void setManager(FragmentManager mManager) {
		this.mManager = mManager;
	}

	public void setSlideMenuController(PhoneSlideMenuController controller) {
		mSlideMenuController = controller;
	}

	public PhoneSlideMenuController getSlideMenuController() {
		return mSlideMenuController;
	}

	public void setmMenuTopController(MenuTopController mMenuTopController) {
		this.mMenuTopController = mMenuTopController;
	}

	public void playMusicOnline() {
		if (mPlayerOnline != null) {
			try {
				mPlayerOnline.reset();
				mPlayerOnline.setDataSource(mCurrentOnline.getzUrlDownload());
				mPlayerOnline.prepare();
				mPlayerOnline.start();
				mDelegateOnlineDetail.updateView(mCurrentOnline, true);
				Config.getInstance().setPlayOnline(true);
				mDelegateOnlineDetail.updateTime();
				mDelegateOnline.updateMusicOnline(mCurrentOnline);
				pauseMusic();
			} catch (Exception e) {
				Log.e("Exception Play Music:", e.getMessage());
			}
		}
	}

	public void continueMusicOnline() {
		if (mPlayerOnline != null && !mPlayerOnline.isPlaying()) {
			mPlayerOnline.start();
			mDelegateOnlineDetail.updateView(mCurrentOnline, true);
			mDelegateOnline.updateMusicOnline(mCurrentOnline);
			Config.getInstance().setPlayOnline(true);
			pauseMusic();
		}
	}

	public void pauseMusicOnline() {
		if (mPlayerOnline != null && mPlayerOnline.isPlaying()) {
			mPlayerOnline.pause();
			mDelegateOnlineDetail.updateView(mCurrentOnline, false);
			mDelegateOnline.updateMusicOnline(mCurrentOnline);
			Config.getInstance().setPlayOnline(false);
		}
	}

	public void nextSongOnline() {
		if (mListEntityOnline.size() > 0 && mCurrentOnline != null) {
			int current = mListEntityOnline.indexOf(mCurrentOnline);
			if (current < mListEntityOnline.size() - 1) {
				mCurrentOnline = mListEntityOnline.get(current + 1);
			} else {
				mCurrentOnline = mListEntityOnline.get(0);
				;
			}
			playMusicOnline();

		}
	}

	public void previousSongOnline() {
		if (mListEntityOnline.size() > 0 && mCurrentOnline != null) {
			int current = mListEntityOnline.indexOf(mCurrentOnline);
			if (current > 0) {
				mCurrentOnline = mListEntityOnline.get(current - 1);
			}
			playMusicOnline();
		}
	}

	public void playMusic() {
		if (mPlayer != null) {
			try {
				mPlayer.reset();
				mPlayer.setDataSource(mCurrentSong.getSong_url());
				mPlayer.prepare();
				mPlayer.start();
				mControllerBottom.updateImagePlay(true);
				if (mDelegateDetail != null) {
					mDelegateDetail.updateView();
				}
				Config.getInstance().setPlay(true);
				mControllerBottom.setIsFirstPlay(false);
				pauseMusicOnline();
				showNotificationMusic();
			} catch (Exception e) {
				Log.e("Exception Play Music:", e.getMessage());
			}
		}
	}

	public void continueMusic() {
		if (mPlayer != null && !mPlayer.isPlaying()) {
			mPlayer.start();
			mControllerBottom.updateImagePlay(true);
			Config.getInstance().setPlay(true);
			pauseMusicOnline();
			showNotificationMusic();
		}
	}

	public void pauseMusic() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			mPlayer.pause();
			mControllerBottom.updateImagePlay(false);
			Config.getInstance().setPlay(false);
			showNotificationMusic();
		}
	}

	private void showNotificationMusic() {
		Intent serviceIntent = new Intent(mCurrentActivity, SongService.class);
		serviceIntent.setAction(ConstanceService.START_ACTION_FOREGROUND);
		BaseManager.getIntance().getCurrentActivity()
				.startService(serviceIntent);
	}

	public void nextSong() {
		if (Instance.LISTSONG_FOR_PLAY.size() > 0 && mCurrentSong != null) {
			int current = Instance.LISTSONG_FOR_PLAY.indexOf(mCurrentSong);
			if (current < Instance.LISTSONG_FOR_PLAY.size() - 1) {
				setCurrentSong(Instance.LISTSONG_FOR_PLAY.get(current + 1));
			} else {
				setCurrentSong(Instance.LISTSONG_FOR_PLAY.get(0));
			}
			if (Config.getInstance().getShuffle()) {
				Random rand = new Random();
				int currentSongIndex = rand.nextInt((Instance.LISTSONG_FOR_PLAY
						.size() - 1) - 0 + 1) + 0;
				setCurrentSong(Instance.LISTSONG_FOR_PLAY.get(currentSongIndex));
			}
			playMusic();

		}
	}

	public void previousSong() {
		if (Instance.LISTSONG_FOR_PLAY.size() > 0 && mCurrentSong != null) {
			int current = Instance.LISTSONG_FOR_PLAY.indexOf(mCurrentSong);
			if (current > 0) {
				setCurrentSong(Instance.LISTSONG_FOR_PLAY.get(current - 1));
			}
			playMusic();
		}
	}

	public Fragment getCurrentFragment() {
		List<Fragment> fragments = mManager.getFragments();
		for (Fragment fragment : fragments) {
			if (fragment != null && fragment.isVisible()) {
				return fragment;
			}
		}
		return null;
	}

	public void addFragment(BaseFragment fragment) {
		if (null != mManager) {
			String nameFragment = fragment.getClass().getName();
			FragmentTransaction ft = mManager.beginTransaction();
			ft.setCustomAnimations(
					Rconfig.getInstance().getId("in_from_right", "anim"),
					Rconfig.getInstance().getId("out_to_left", "anim"), Rconfig
							.getInstance().getId("in_from_left", "anim"),
					Rconfig.getInstance().getId("out_to_right", "anim"));
			ft.replace(Rconfig.getInstance().id("container"), fragment);
			ft.addToBackStack(nameFragment);
			ft.commit();
			mManager.executePendingTransactions();
		}
	}

	public void replaceFragment(BaseFragment fragment) {
		System.out.println(mManager);
		if (mManager != null) {
			String nameFragment = fragment.getClass().getName();
			boolean isHome = false;
			String screen_name = fragment.getScreenName();
			if (nameFragment.contains("com.iphonmusic.fragment.FragmentHome")
					|| nameFragment
							.contains("com.iphonmusic.child.video.FragmentVideoDetail")) {
				isHome = true;
			}

			mManager.popBackStack(nameFragment,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			FragmentTransaction fragmentTransaction = mManager
					.beginTransaction();
			if (!isHome) {
				fragmentTransaction.setCustomAnimations(Rconfig.getInstance()
						.getId("in_from_right", "anim"), Rconfig.getInstance()
						.getId("out_to_left", "anim"), Rconfig.getInstance()
						.getId("in_from_left", "anim"), Rconfig.getInstance()
						.getId("out_to_right", "anim"));
			} else {
				mControllerBottom.visibleRootView(true);
			}
			fragmentTransaction.replace(Rconfig.getInstance().id("container"),
					fragment);
			fragmentTransaction.addToBackStack(nameFragment);
			fragmentTransaction.commit();
		}

	}

	public void backToPreviousFragment() {
		mManager.popBackStack();
	}

	public boolean checkSongInListChoise(EntitySong song) {
		if (Instance.LISTSONG_CHOISE.size() > 0) {
			for (EntitySong entitySong : Instance.LISTSONG_CHOISE) {
				if (entitySong.getSong_name().equals(song.getSong_name())) {
					return true;
				}
			}
		}
		return false;
	}

	public void removeSongInListChoise(EntitySong song) {
		if (Instance.LISTSONG_CHOISE.size() > 0) {
			Instance.LISTSONG_CHOISE.remove(song);
		}
	}

	public void hideKeyBoard() {
		try {
			if (mCurrentActivity != null) {
				InputMethodManager imm = (InputMethodManager) mCurrentActivity
						.getSystemService(Service.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mCurrentActivity.getCurrentFocus()
						.getWindowToken(), 0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
