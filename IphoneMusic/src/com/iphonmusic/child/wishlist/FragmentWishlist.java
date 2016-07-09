package com.iphonmusic.child.wishlist;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.iphonmusic.adapter.AdapterWishList;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class FragmentWishlist extends BaseFragment {

	private ListView listView;

	private AdapterWishList mAdapter;
	private ImageView img_shuffle;
	ArrayList<EntitySong> mWishList;
	private LinearLayout layout_shuffle;

	public static FragmentWishlist newInstance() {
		FragmentWishlist fragment = new FragmentWishlist();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_wishlist"),
				container, false);
		listView = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview_wishlist"));

		mWishList = EntitySong.getAllItemWishList();
		if (mAdapter == null) {
			mAdapter = new AdapterWishList(mWishList, getActivity());
		}
		listView.setAdapter(mAdapter);
		img_shuffle = (ImageView) rootView.findViewById(Rconfig.getInstance()
				.id("img_shuffle"));
		if (Config.getInstance().getShuffle()) {
			img_shuffle.setColorFilter(Color.BLACK);
		} else {
			img_shuffle.setColorFilter(Color.GRAY);
		}
		layout_shuffle = (LinearLayout) rootView.findViewById(Rconfig.getInstance().id(
				"ll_shuffle"));
		handleEvent();
		return rootView;
	}

	private void handleEvent() {
		layout_shuffle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Config.getInstance().getShuffle()) {
					img_shuffle.setColorFilter(Color.GRAY);
					Config.getInstance().setShuffle(false);
				} else {
					img_shuffle.setColorFilter(Color.BLACK);
					Config.getInstance().setShuffle(true);
				}

			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Instance.LISTSONG_FOR_PLAY = mWishList;
				EntitySong song = (EntitySong) parent
						.getItemAtPosition(position);
				BaseManager.getIntance().setCurrentSong(song);
				BaseManager.getIntance().playMusic();
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		ArrayList<EntitySong> list = EntitySong.getAllItemWishList();
		mAdapter = new AdapterWishList(list, getActivity());
		listView.setAdapter(mAdapter);
	}
}
