package com.iphonmusic.slidemenu.fragment;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.slidemenu.block.PhoneSlideMenuBlock;
import com.iphonmusic.slidemenu.controller.PhoneSlideMenuController;
import com.iphonmusic.slidemenu.delegate.CloseSlideMenuDelegate;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PhoneSlideMenuFragment extends BaseFragment {
	protected CloseSlideMenuDelegate mCLoseDelegate;
	protected PhoneSlideMenuController mController;
	protected PhoneSlideMenuBlock mBlock;
	public void setCloseDelegate(CloseSlideMenuDelegate mCLoseDelegate) {
		this.mCLoseDelegate = mCLoseDelegate;
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(
				Rconfig.getInstance().layout("core_phone_slide_menu"), null,
				false);
		view.setBackgroundColor(Color.parseColor("#ffffff"));
		Context context = getActivity();
		mBlock = new PhoneSlideMenuBlock(view, context);
		mBlock.initView();
		
		mController = new PhoneSlideMenuController(mBlock, context);
		mController.setCloseDelegate(mCLoseDelegate);
		mController.create();
		
		mBlock.setListener(mController.getListener());
		BaseManager.getIntance().setSlideMenuController(mController);
		return view;
	}
}
