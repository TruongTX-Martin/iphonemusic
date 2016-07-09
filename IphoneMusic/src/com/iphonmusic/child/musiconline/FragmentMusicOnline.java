package com.iphonmusic.child.musiconline;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;

public class FragmentMusicOnline extends BaseFragment {
	
	private Context mContext;
	private static String mSiteName;
	
	
	
	public static FragmentMusicOnline newInstance(String siteName) {
		FragmentMusicOnline mp3Zing = new FragmentMusicOnline();
		mSiteName = siteName;
		return mp3Zing;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity().getApplicationContext();
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_mp3zing"),
				container, false);
		BlockMusicOnline block = new BlockMusicOnline(rootView,mContext);
		block.setSiteName(mSiteName);
		block.initView();
		BaseManager.getIntance().setDelegateOnline(block);
		ControllerMusicOnline mController = new ControllerMusicOnline();
		mController.setDelegate(block);
		mController.setSiteName(mSiteName);
		mController.request(Instance.SEARCH);
	
		
		block.setOnKeyEdittext(mController.getOnKeyListenerEditText());
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		BaseManager.getIntance().getControllerBottom().visibleRootView(false);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
