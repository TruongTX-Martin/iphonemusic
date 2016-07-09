package com.iphonmusic.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iphonmusic.adapter.TabAdapterFragment;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Constant;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.style.PagerSlidingTabStrip;

public class FragmentHome extends BaseFragment {

	public static FragmentHome newInstance() {
		FragmentHome fragment = new FragmentHome();
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setScreenName(Constant.SCREEN_HOME);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_home"),
				container, false);
		initView(rootView);
		return rootView;
	}
	public void initView(View mRootView) {
		final TabAdapterFragment adapter = new TabAdapterFragment(
				getChildFragmentManager());
		final ViewPager mPager = (ViewPager) mRootView.findViewById(Rconfig
				.getInstance().id("pager"));
		mPager.setAdapter(adapter);

		PagerSlidingTabStrip title_tab = (PagerSlidingTabStrip) mRootView
				.findViewById(Rconfig.getInstance().id("pager_title_strip"));
		title_tab.setTextColor(Config.getInstance().getSection_text_color());
		title_tab.setBackgroundColor(Color.parseColor(Config.getInstance()
				.getSection_color()));
		title_tab.setDividerColor(Config.getInstance().getSection_text_color());
		title_tab.setIndicatorColor(Config.getInstance().getKey_color());
		title_tab.setIndicatorHeight(5);
		title_tab.setAllCaps(false);
		title_tab.setViewPager(mPager);
	}
	@Override
	public void onResume() {
		super.onResume();
		BaseManager.getIntance().getControllerBottom().visibleRootView(true);
	}
	

}
