package com.iphonmusic.child.songs;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.iphonmusic.adapter.AdapterSongs;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class FragmentSongs extends BaseFragment {

	final String MEDIA_PATH = Environment.getExternalStorageDirectory()
			.getPath() + "/";

	private ListView mListView;
	private AdapterSongs mAdapter;
	private Context mContext;
	private EditText edt_search;
	private ImageView img_shuffle;
	private LinearLayout layout_shuffle;

	public static FragmentSongs newInstance() {
		FragmentSongs fragment = new FragmentSongs();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_songs"),
				container, false);
		mContext = getActivity();
		mListView = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		edt_search = (EditText) rootView.findViewById(Rconfig.getInstance().id(
				"edt_search"));
		img_shuffle = (ImageView) rootView.findViewById(Rconfig.getInstance()
				.id("img_shuffle"));
		layout_shuffle = (LinearLayout) rootView.findViewById(Rconfig
				.getInstance().id("ll_shuffle"));
		refesh();
		if (mAdapter == null) {
			mAdapter = new AdapterSongs(mContext, Instance.LISTSONG);
			mListView.setAdapter(mAdapter);
		} else {
			mListView.setAdapter(mAdapter);
		}
		handleEvent();
		if(Instance.LISTSONG.size() > 0){
			Instance.LISTSONG_FOR_PLAY = Instance.LISTSONG;
		}
		return rootView;
	}

	private void refesh() {
		if (Config.getInstance().getShuffle()) {
			img_shuffle.setColorFilter(Color.BLACK);
		} else {
			img_shuffle.setColorFilter(Color.GRAY);
		}
	}

	private void handleEvent() {

		edt_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				mAdapter.getFilter().filter(s.toString());
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Instance.LISTSONG_FOR_PLAY = Instance.LISTSONG;
				EntitySong song = (EntitySong) parent
						.getItemAtPosition(position);
				BaseManager.getIntance().setCurrentSong(song);
				BaseManager.getIntance().playMusic();
			}
		});
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
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdapter == null) {
			mAdapter = new AdapterSongs(mContext, Instance.LISTSONG);
			mListView.setAdapter(mAdapter);
		} else {
			mListView.setAdapter(mAdapter);
		}
		refesh();
	}
}
