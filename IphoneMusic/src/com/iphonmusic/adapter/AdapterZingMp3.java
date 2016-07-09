package com.iphonmusic.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iphonmusic.R;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityZingMp3;

public class AdapterZingMp3 extends BaseAdapter {

	private Context mContext;
	private ArrayList<EntityZingMp3> mZingMp3;

	public AdapterZingMp3(Context context, ArrayList<EntityZingMp3> arrayList) {
		this.mContext = context;
		this.mZingMp3 = arrayList;
	}

	@Override
	public int getCount() {
		return mZingMp3.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mZingMp3.get(position);
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
					Rconfig.getInstance().layout("layout_item_zingmp3"), null);
			holder = new ViewHolder();
			holder.img_icon = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_icon"));
			holder.txt_song_name = (TextView) convertView.findViewById(Rconfig
					.getInstance().id("txt_name_song"));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		EntityZingMp3 zingMp3 = (EntityZingMp3) getItem(position);
		if (zingMp3.getzTitle() != null) {
			if (zingMp3.getzArtist() != null) {
				holder.txt_song_name.setText(zingMp3.getzTitle() + " - "
						+ zingMp3.getzArtist());
			} else {
				holder.txt_song_name.setText(zingMp3.getzTitle());
			}
		}
		if (zingMp3.getzAvatar() != null) {
			Glide.with(mContext).load(zingMp3.getzAvatar()).centerCrop()
					.placeholder(R.drawable.ic_music_item)
					.into(holder.img_icon);
		}
		return convertView;
	}

	private static class ViewHolder {
		ImageView img_icon;
		TextView txt_song_name;
	}

}
