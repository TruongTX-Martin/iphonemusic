package com.iphonmusic.menubottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Rconfig;

public class FragmentBottom extends BaseFragment {

	public static FragmentBottom newInstance() {
		FragmentBottom fragment = new FragmentBottom();
		return fragment;
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater
				.inflate(Rconfig.getInstance().layout("core_menu_bottom"),
						container, false);
		BlockBottom blockBottom = new BlockBottom(rootView);
		blockBottom.initView();
		ControllerBottom controller = new ControllerBottom();
		controller.setDelegate(blockBottom);
		controller.init();
		
		BaseManager.getIntance().setControllerBottom(controller);
		blockBottom.setOnPlayOnClickListener(controller.getOnPlayListener());
		blockBottom.setOnNextOnClickListener(controller.getOnNextListener());
		blockBottom.setOnLayoutClickListener(controller.getOnLayoutListener());
		return rootView;
	}
}
