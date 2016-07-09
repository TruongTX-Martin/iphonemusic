package com.iphonmusic.child.video;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityVideo;

public class FragmentVideoDetail extends BaseFragment {

	private EntityVideo video;
	private VideoView mVideoView;
	private Context mContext;

	DisplayMetrics displayMetrics;
	SurfaceView surfaceView;
	MediaController mediaController;

	public static FragmentVideoDetail newInstance() {
		FragmentVideoDetail detail = new FragmentVideoDetail();
		return detail;
	}

	public void setVideo(EntityVideo video) {
		this.video = video;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_videos_detail"),
				container, false);
		mVideoView = (VideoView) rootView.findViewById(Rconfig.getInstance()
				.id("videoview"));
		if (video != null) {
			mediaController = new MediaController(BaseManager.getIntance().getCurrentActivity());
			displayMetrics = new DisplayMetrics();
			BaseManager.getIntance().getCurrentActivity().getWindowManager()
					.getDefaultDisplay().getMetrics(displayMetrics);
			int height = displayMetrics.heightPixels;
			int width = displayMetrics.widthPixels;
			Uri uri = Uri.parse(video.getVideo_url());
			mVideoView.setMinimumWidth(width);
			mVideoView.setMinimumHeight(height);
			mVideoView.setMediaController(mediaController);
			mVideoView.setVideoPath(video.getVideo_url());
			mVideoView.start();
			BaseManager.getIntance().pauseMusic();
		}
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BaseManager.getIntance().getControllerBottom().visibleRootView(false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		BaseManager.getIntance().getControllerBottom().visibleRootView(true);
		if (mVideoView != null && mVideoView.isPlaying()) {
			mVideoView.stopPlayback();
		}
	}

}
