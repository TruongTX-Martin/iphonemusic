package com.iphonmusic.child.playlist;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.iphonmusic.adapter.AdapterPlayList;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityPlaylist;

public class FragmentPlaylist extends BaseFragment {

	private ListView listview;

	private ArrayList<EntityPlaylist> mPlayList = new ArrayList<EntityPlaylist>();
	private AdapterPlayList mAdapter;
	private Context mContext;
	private ImageView img_add;

	public static FragmentPlaylist newInstance() {
		FragmentPlaylist fragment = new FragmentPlaylist();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_playlist"),
				container, false);
		mContext = getActivity();
		listview = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		img_add = (ImageView) rootView.findViewById(Rconfig.getInstance().id(
				"img_addnew"));
		EntityPlaylist entityPlaylist = new EntityPlaylist();
		entityPlaylist.setPlaylistName("Default PlayList");
		EntityPlaylist.addItemPlaylist(entityPlaylist);
		mPlayList = EntityPlaylist.getAllPlayList();
		if (mAdapter == null) {
			mAdapter = new AdapterPlayList(mContext, mPlayList);
			listview.setAdapter(mAdapter);
		}
		img_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addPlaylist();
			}
		});

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EntityPlaylist playlist = (EntityPlaylist) parent.getItemAtPosition(position);
				String mId = EntityPlaylist.getIdPlaylist(playlist);
				FragmentPlaylistDetail detail = FragmentPlaylistDetail
						.newInstance();
				detail.setIdPlaylist(mId);
				BaseManager.getIntance().replaceFragment(detail);
			}
		});
		return rootView;
	}

	private void addPlaylist() {
		try {
			final Dialog dialog = new Dialog(mContext);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(Rconfig.getInstance().layout(
					"custom_dialog_addplaylist"));
			final EditText editText = (EditText) dialog.findViewById(Rconfig
					.getInstance().id("edit_name"));
			Button btn_cancel = (Button) dialog.findViewById(Rconfig
					.getInstance().id("btn_cancel"));
			Button btn_ok = (Button) dialog.findViewById(Rconfig.getInstance()
					.id("btn_ok"));
			btn_cancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			btn_ok.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (editText.getText().toString().length() > 0) {
						String name = editText.getText().toString();
						EntityPlaylist
								.addItemPlaylist(new EntityPlaylist(name));
						refreshListview();
					}
					dialog.dismiss();
				}
			});
			dialog.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void refreshListview() {
		mPlayList = EntityPlaylist.getAllPlayList();
		mAdapter = new AdapterPlayList(mContext, mPlayList);
		listview.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdapter == null) {
			mAdapter = new AdapterPlayList(mContext, mPlayList);
		}
		listview.setAdapter(mAdapter);
	}
	
	

}
