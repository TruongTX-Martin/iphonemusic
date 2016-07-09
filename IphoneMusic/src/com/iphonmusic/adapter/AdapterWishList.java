package com.iphonmusic.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class AdapterWishList extends BaseAdapter{

	private ArrayList<EntitySong> mWishList = new ArrayList<EntitySong>();
	private Context mContext;

	private Drawable icon_appble;
	private Drawable icon_extend;
	public AdapterWishList(ArrayList<EntitySong> list,Context context) {
		this.mWishList = list;
		this.mContext = context;
	}
	@Override
	public int getCount() {
		return mWishList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mWishList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(
					Rconfig.getInstance().layout("layout_item_song"), null);
			holder = new ViewHolder();
			holder.img_icon = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_icon"));
			holder.img_extend = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_expand"));
			holder.txt_song_name = (TextView) convertView.findViewById(Rconfig
					.getInstance().id("txt_name_song"));
			holder.txt_song_singer = (TextView) convertView
					.findViewById(Rconfig.getInstance().id("txt_singer"));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		EntitySong song = mWishList.get(position);
		if (icon_appble == null) {
			icon_appble = mContext.getResources().getDrawable(
					Rconfig.getInstance().drawable("ic_appble"));
		}
		holder.img_icon.setImageDrawable(icon_appble);

		if (icon_extend == null) {
			icon_extend = mContext.getResources().getDrawable(
					Rconfig.getInstance().drawable("ic_extend"));
		}
		holder.img_extend.setImageDrawable(icon_extend);

		holder.txt_song_name.setText(song.getSong_name());
		holder.txt_song_singer.setText(song.getSong_singer());
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView img_icon;
		ImageView img_extend;
		TextView txt_song_name;
		TextView txt_song_singer;
	}
	
	

}
