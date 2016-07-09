package com.iphonmusic.child.playlist;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iphonmusic.adapter.AdapterSongs;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.child.songs.FragmentSongsChoise;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class FragmentPlaylistDetail extends BaseFragment {

	private ListView listview;
	private Context mContext;
	private ImageView img_add;
	private TextView txt_message;
	private ArrayList<EntitySong> mEntitySongs = new ArrayList<EntitySong>();
	private String mIdPlaylist;
	private AdapterSongs mAdapter;
	private ImageView img_shuffle;
	private LinearLayout layout_shuffle;

	public static FragmentPlaylistDetail newInstance() {
		FragmentPlaylistDetail detail = new FragmentPlaylistDetail();
		return detail;
	}

	public void setIdPlaylist(String mIdPlaylist) {
		this.mIdPlaylist = mIdPlaylist;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater
				.inflate(
						Rconfig.getInstance().layout(
								"layout_fragment_playlist_detail"), container,
						false);
		mContext = getActivity();
		listview = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		img_add = (ImageView) rootView.findViewById(Rconfig.getInstance().id(
				"img_addnew_detail"));
		txt_message = (TextView) rootView.findViewById(Rconfig.getInstance()
				.id("txt_message"));
		img_shuffle = (ImageView) rootView.findViewById(Rconfig.getInstance()
				.id("img_shuffle"));
		layout_shuffle = (LinearLayout) rootView.findViewById(Rconfig.getInstance().id(
				"ll_shuffle"));
		if (Config.getInstance().getShuffle()) {
			img_shuffle.setColorFilter(Color.BLACK);
		} else {
			img_shuffle.setColorFilter(Color.GRAY);
		}
		showview();
		handleEvent();
		return rootView;
	}

	void showview() {
		if (mEntitySongs.size() > 0) {
			listview.setVisibility(View.VISIBLE);
			txt_message.setVisibility(View.GONE);
			mAdapter = new AdapterSongs(mContext, mEntitySongs);
			listview.setAdapter(mAdapter);
		} else {
			listview.setVisibility(View.GONE);
			txt_message.setVisibility(View.VISIBLE);
		}
	}

	private void handleEvent() {
		img_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentSongsChoise choise = FragmentSongsChoise.newInstance();
				choise.setIdPlaylist(mIdPlaylist);
				BaseManager.getIntance().replaceFragment(choise);
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
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Instance.LISTSONG_FOR_PLAY = mEntitySongs;
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
		if (mIdPlaylist != null) {
			mEntitySongs = EntitySong.getItemSongFromPlaylistId(mIdPlaylist);
			showview();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
