package com.iphonmusic.menutop.fragment;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.menutop.block.MenuTopBlock;
import com.iphonmusic.menutop.controller.MenuTopController;
import com.iphonmusic.slidemenu.fragment.SlideMenuFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuTopFragment extends BaseFragment{
	public View rootView;
	protected MenuTopBlock mBlock;
	protected MenuTopController mController;
	protected SlideMenuFragment mNavigationDrawerFragment;
	
	public static MenuTopFragment newInstance(SlideMenuFragment mNavigationDrawerFragment){
		MenuTopFragment fragment = new MenuTopFragment();
		fragment.mNavigationDrawerFragment = mNavigationDrawerFragment;
		return fragment;
	}
	
	public MenuTopFragment() {
		super();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(Rconfig.getInstance().layout("core_menu_top"), container,false);
//		rootView.setBackgroundColor(Config.getInstance().getColorMain());
		Context mContext = getActivity();
		mBlock = new MenuTopBlock(rootView, mContext);
		mBlock.initView();
		if (mController == null) {
			mController = new MenuTopController();
			mController.setSlideMenu(mNavigationDrawerFragment);
			mController.setMenuTopDelegate(mBlock);
			mController.onStart();
		} else {
			
		}
		mBlock.setOnTouchMenu(mController.getTouchMenu());
		BaseManager.getIntance().setmMenuTopController(mController);
		return rootView;
	}
}
