package com.iphonmusic.child.songs;

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
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.iphonmusic.adapter.AdapterSongsChoise;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class FragmentSongsChoise extends BaseFragment {

	private Context mContext;
	private ListView mListView;

	private RelativeLayout layout_done;
	private ImageView img_done;
	private AdapterSongsChoise mAdapter;
	private String mIdPlaylist;

	public static FragmentSongsChoise newInstance() {
		FragmentSongsChoise choise = new FragmentSongsChoise();
		return choise;
	}
	
	public void setIdPlaylist(String mIdPlaylist) {
		this.mIdPlaylist = mIdPlaylist;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Instance.LISTSONG_CHOISE.clear();
		Instance.LISTSONGALL_CHOISE.clear();
		Instance.LISTSONGALL_CHOISE = Instance.LISTSONG;
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_songs_choise"),
				container, false);
		mContext = getActivity();
		mListView = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		img_done = (ImageView) rootView.findViewById(Rconfig.getInstance().id(
				"img_done"));
		img_done.setColorFilter(Color.parseColor("#000000"));
		layout_done = (RelativeLayout) rootView.findViewById(Rconfig
				.getInstance().id("layout_done"));

		if (mAdapter == null) {
			mAdapter = new AdapterSongsChoise(mContext);
		}
		mListView.setAdapter(mAdapter);
		handleEvent();
		return rootView;
	}

	private void handleEvent() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ImageView img = (ImageView) view.findViewById(Rconfig
						.getInstance().id("img_check"));
				EntitySong song = (EntitySong) parent.getItemAtPosition(position);
				if(BaseManager.getIntance().checkSongInListChoise(song)){
					BaseManager.getIntance().removeSongInListChoise(song);
					img.setImageDrawable(mContext.getResources().getDrawable(
							Rconfig.getInstance().drawable("ic_unchecked")));
					song.setCheck(false);
					Instance.LISTSONGALL_CHOISE.get(position).setCheck(false);
				}else{
					Instance.LISTSONG_CHOISE.add(song);
					img.setImageDrawable(mContext.getResources().getDrawable(
							Rconfig.getInstance().drawable("ic_checked")));
					song.setCheck(true);
					Instance.LISTSONGALL_CHOISE.get(position).setCheck(true);
				}
				
				img.setColorFilter(Color.parseColor("#000000"));
			}
		});
		layout_done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(Instance.LISTSONG_CHOISE.size() > 0){
					EntitySong.addListItemToPlayList(mIdPlaylist, Instance.LISTSONG_CHOISE);
					BaseManager.getIntance().backToPreviousFragment();
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdapter == null) {
			mAdapter = new AdapterSongsChoise(mContext);
		}
		mListView.setAdapter(mAdapter);
	}

}
