package com.iphonmusic.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.child.folders.FragmentFolder;
import com.iphonmusic.child.playlist.FragmentPlaylist;
import com.iphonmusic.child.songs.FragmentSongs;
import com.iphonmusic.child.video.FragmentVideo;
import com.iphonmusic.child.wishlist.FragmentWishlist;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Constant;

public class TabAdapterFragment extends FragmentStatePagerAdapter {
	protected ArrayList<BaseFragment> mListFragment;
	protected ArrayList<String> mListTitle;

	public TabAdapterFragment(FragmentManager fm) {
		super(fm);
		mListFragment = new ArrayList<BaseFragment>();
		mListTitle = new ArrayList<String>();
		addFragment();
		addTitle();
	}

	@Override
	public Fragment getItem(int position) {
		return mListFragment.get(position);
	}

	@Override
	public int getCount() {
		return mListFragment.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mListTitle.get(position);
	}

	private void addFragment() {
		FragmentSongs frm_song = FragmentSongs.newInstance();
		FragmentFolder frm_folder = FragmentFolder.newInstance();
		FragmentPlaylist frm_playlist = FragmentPlaylist.newInstance();
		FragmentWishlist frm_wishlist = FragmentWishlist.newInstance();
		FragmentVideo frm_video = FragmentVideo.newInstance();
		mListFragment.add(frm_song);
		mListFragment.add(frm_folder);
		mListFragment.add(frm_playlist);
		mListFragment.add(frm_wishlist);
		mListFragment.add(frm_video);
	}

	private void addTitle() {
		mListTitle.add(Constant.ITEM_SONGS);
		mListTitle.add(Constant.ITEM_FOLDERS);
		mListTitle.add(Constant.ITEM_PLAYLIST);
		mListTitle.add(Constant.ITEM_WISHLIST);
		mListTitle.add(Constant.ITEM_VIDEOS);
	}

}
