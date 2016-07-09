package com.iphonmusic.adapter;

import java.util.ArrayList;

import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityPlaylist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPlayList extends BaseAdapter {

	private Context mContext;
	private ArrayList<EntityPlaylist> mPlayList;

	public AdapterPlayList(Context context,
			ArrayList<EntityPlaylist> entityPlaylists) {
		this.mContext = context;
		this.mPlayList = entityPlaylists;
	}

	@Override
	public int getCount() {
		return mPlayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mPlayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(Rconfig.getInstance().layout("layout_item_playlist"),
					null);
			holder = new ViewHolder();
			holder.img_playlist = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_icon"));
			holder.txt_playlist_name = (TextView) convertView
					.findViewById(Rconfig.getInstance().id("txt_name_playlist"));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		EntityPlaylist playlist = mPlayList.get(position);
		
		holder.txt_playlist_name.setText(playlist.getPlaylistName());
		holder.img_playlist.setImageResource(Rconfig.getInstance().drawable("ic_playlist"));
		holder.img_playlist.setColorFilter(Color.parseColor("#000000"));
		return convertView;
	}

	private static class ViewHolder {
		ImageView img_playlist;
		TextView txt_playlist_name;
	}

}
