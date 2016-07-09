package com.iphonmusic.child.folders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.iphonmusic.adapter.AdapterFolder;
import com.iphonmusic.adapter.AdapterSongs;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityFolder;

public class FragmentFolder extends BaseFragment {

	private ListView mListView;
	private AdapterFolder mAdapter;
	private Context mContext;

	public static FragmentFolder newInstance() {
		FragmentFolder fragment = new FragmentFolder();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_folders"),
				container, false);
		mContext = getActivity();
		mListView = (ListView) rootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		if (mAdapter == null) {
			mAdapter = new AdapterFolder(mContext, Instance.LISTFOLDER);
			mListView.setAdapter(mAdapter);
		} else {
			mListView.setAdapter(mAdapter);
		}
		handleEvent();
		return rootView;
	}
	private void handleEvent(){
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EntityFolder entityFolder = (EntityFolder) parent.getItemAtPosition(position);
				FragmentFolderDetail detail = FragmentFolderDetail.newInstance();
				detail.setEntityFolder(entityFolder);
				BaseManager.getIntance().replaceFragment(detail);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdapter == null) {
			mAdapter = new AdapterFolder(mContext, Instance.LISTFOLDER);
			mListView.setAdapter(mAdapter);
		} else {
			mListView.setAdapter(mAdapter);
		}
	}

}
