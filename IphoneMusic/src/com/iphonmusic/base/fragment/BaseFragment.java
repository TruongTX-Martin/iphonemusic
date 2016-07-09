package com.iphonmusic.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment{
	
	protected View rootView;
	protected String screenName = "";

	public static BaseFragment newInstance() {
		BaseFragment fragment = new BaseFragment();
		return fragment;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String name) {
		this.screenName = name;
//		CacheSimiTrackingFragment cache = new CacheSimiTrackingFragment();
//		cache.setFragment(this);
//		EventFragment event = new EventFragment();
//		event.dispatchEvent(name, cache);
	}

	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
