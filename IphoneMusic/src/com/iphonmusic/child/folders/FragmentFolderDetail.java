package com.iphonmusic.child.folders;

import java.io.File;
import java.util.ArrayList;

import android.R.array;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.iphonmusic.adapter.AdapterWishList;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityFolder;
import com.iphonmusic.entity.EntitySong;

public class FragmentFolderDetail extends BaseFragment {

	private Context mContext;
	private LinearLayout mLayoutShuffle;
	private ImageView mImgShuffle;
	private ListView mListView;
	private EntityFolder mEntityFolder;
	private ArrayList<EntitySong> mSongs;
	private AdapterWishList mAdapter;

	public static FragmentFolderDetail newInstance() {
		FragmentFolderDetail detail = new FragmentFolderDetail();
		return detail;
	}

	public void setEntityFolder(EntityFolder mEntityFolder) {
		this.mEntityFolder = mEntityFolder;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_folder_detail"),
				container, false);
		mContext = getActivity();
		mLayoutShuffle = (LinearLayout) rootView.findViewById(Rconfig
				.getInstance().id("ll_shuffle"));
		mImgShuffle = (ImageView) rootView.findViewById(Rconfig.getInstance()
				.id("img_shuffle"));
		mListView = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		if (mEntityFolder != null) {
			mSongs = getListSongFromFolder(mEntityFolder);
			if (mAdapter == null) {
				mAdapter = new AdapterWishList(mSongs, getActivity());
			}
			mListView.setAdapter(mAdapter);
		}
		handleEvent();
		return rootView;
	}

	private void handleEvent() {
		if (Config.getInstance().getShuffle()) {
			mImgShuffle.setColorFilter(Color.BLACK);
		} else {
			mImgShuffle.setColorFilter(Color.GRAY);
		}
		mLayoutShuffle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Config.getInstance().getShuffle()) {
					mImgShuffle.setColorFilter(Color.GRAY);
					Config.getInstance().setShuffle(false);
				} else {
					mImgShuffle.setColorFilter(Color.BLACK);
					Config.getInstance().setShuffle(true);
				}

			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Instance.LISTSONG_FOR_PLAY = mSongs;
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
		if (mEntityFolder != null) {
			mSongs = getListSongFromFolder(mEntityFolder);
			if (mAdapter == null) {
				mAdapter = new AdapterWishList(mSongs, getActivity());
			}
			mListView.setAdapter(mAdapter);
		}
	}

	private ArrayList<EntitySong> getListSongFromFolder(EntityFolder folder) {
		ArrayList<EntitySong> arrayList = new ArrayList<EntitySong>();
		File fileInput = folder.getFolder_file();
		File listFile[] = fileInput.listFiles();
		for (File file : listFile) {
			if (file.getName().endsWith(".mp3")) {
				String fullName = file.getName().substring(0,
						(file.getName().length() - 4));
				if (!fullName.substring(0, 1).contains(".")) {
					EntitySong song = new EntitySong();
					song.setSong_name(Rconfig.getInstance().getSongName(
							fullName));
					song.setSong_singer(Rconfig.getInstance().getSingerName(
							fullName));
					song.setSong_url(file.getPath());
					song.setSong_file(file);
					arrayList.add(song);
				}
			}
		}
		return arrayList;
	}

}
