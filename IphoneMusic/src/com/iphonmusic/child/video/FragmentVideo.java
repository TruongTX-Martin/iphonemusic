package com.iphonmusic.child.video;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.iphonmusic.adapter.AdapterVideo;
import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityVideo;

public class FragmentVideo extends BaseFragment {
	final String MEDIA_PATH = Environment.getExternalStorageDirectory()
			.getPath() + "/";
	private AdapterVideo mAdapter;
	private Context mContext;
	private GridView mGridView;
	private TextView txt_message;

	public static FragmentVideo newInstance() {
		FragmentVideo fragment = new FragmentVideo();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_videos"),
				container, false);
		mContext = getActivity();
		mGridView = (GridView) rootView.findViewById(Rconfig.getInstance().id(
				"gridview"));
		txt_message = (TextView) rootView.findViewById(Rconfig.getInstance().id("txt_message"));
		if (Instance.LIST_VIDEO.size() == 0) {
			getListSongs(new File(MEDIA_PATH));
		}
		if (mAdapter == null) {
			mAdapter = new AdapterVideo(mContext, Instance.LIST_VIDEO);
		}
		mGridView.setAdapter(mAdapter);
		updateView();
		handleEvent();
		return rootView;
	}
	
	private void handleEvent(){
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EntityVideo video = (EntityVideo) parent.getItemAtPosition(position);
				FragmentVideoDetail detail = FragmentVideoDetail.newInstance();
				detail.setVideo(video);
				BaseManager.getIntance().replaceFragment(detail);
			}
		});
	}

	void updateView(){
		if(Instance.LIST_VIDEO.size() > 0){
			mGridView.setVisibility(View.VISIBLE);
			txt_message.setVisibility(View.GONE);
		}else{
			mGridView.setVisibility(View.GONE);
			txt_message.setVisibility(View.VISIBLE);
		}
	}
	@SuppressLint("NewApi")
	public void getListSongs(File dir) {
		String Pattern = ".mp4";
		File listFile[] = dir.listFiles();
		if (listFile != null && listFile.length > 0) {
			for (File file : listFile) {
				if (file != null && !file.isHidden()) {
					if (file.isDirectory()) {
						getListSongs(file);
					} else {
						if (file.getName().endsWith(Pattern)
								&& !file.isHidden()) {
							String fullName = file.getName().substring(0,
									(file.getName().length() - 4));
							if (!fullName.substring(0, 1).contains(".")) {
								EntityVideo video = new EntityVideo();
								video.setVideo_name(fullName);
								video.setVideo_url(file.getAbsolutePath());
								Bitmap bitmap = ThumbnailUtils
										.createVideoThumbnail(
												file.getAbsolutePath(),
												MediaStore.Video.Thumbnails.MICRO_KIND);
								video.setVideo_bitmap_thumb(bitmap);
								Instance.LIST_VIDEO.add(video);
							}
						}
					}
				}
			}
		}
		if (mAdapter == null) {
			mAdapter = new AdapterVideo(mContext, Instance.LIST_VIDEO);
		}
		mGridView.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdapter == null) {
			mAdapter = new AdapterVideo(mContext, Instance.LIST_VIDEO);
		}
		mGridView.setAdapter(mAdapter);
		updateView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
