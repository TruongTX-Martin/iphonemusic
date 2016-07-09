package com.iphonmusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.config.Rconfig;

public class FragmentCategory extends BaseFragment {

	public static FragmentCategory newInstance() {
		FragmentCategory fragment = new FragmentCategory();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_category"),
				container, false);
		return rootView;
	}

}
