package com.iphonmusic.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityVideo;

public class AdapterVideo extends BaseAdapter {
	private Context mContext;
	private ArrayList<EntityVideo> mVideo;

	public AdapterVideo(Context context, ArrayList<EntityVideo> arrayList) {
		this.mContext = context;
		this.mVideo = arrayList;
	}

	@Override
	public int getCount() {
		return mVideo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mVideo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(
					Rconfig.getInstance().layout("layout_item_video"), null);
			holder = new ViewHolder();
			holder.img_icon = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("image_icon"));
			holder.img_full = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_full"));
			holder.txt_video_name = (TextView) convertView.findViewById(Rconfig
					.getInstance().id("txt_name"));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		EntityVideo video = mVideo.get(position);
		holder.txt_video_name.setText(video.getVideo_name());
		if(video.getVideo_bitmap_thumb() != null){
			holder.img_full.setImageBitmap(video.getVideo_bitmap_thumb());
		}
		return convertView;
	}

	private static class ViewHolder {
		ImageView img_icon;
		ImageView img_full;
		TextView txt_video_name;
	}

}
