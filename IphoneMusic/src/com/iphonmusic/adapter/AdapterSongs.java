package com.iphonmusic.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntitySong;

public class AdapterSongs extends BaseAdapter implements Filterable{

	private Context mContext;
	private ArrayList<EntitySong> listSong = new ArrayList<EntitySong>();
	private ArrayList<EntitySong> listSongFilter = new ArrayList<EntitySong>();
	private Drawable icon_appble;
	private Drawable icon_extend;
	private ValueFilter valueFilter;

	public AdapterSongs(Context context, ArrayList<EntitySong> arrayList) {
		this.mContext = context;
		this.listSong = arrayList;
		this.listSongFilter = arrayList;
	}

	@Override
	public int getCount() {
		return listSong.size();
	}

	@Override
	public Object getItem(int position) {
		return listSong.get(position);
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
		EntitySong song = listSong.get(position);
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

	private class ValueFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();

			if (constraint != null && constraint.length() > 0) {
				ArrayList<EntitySong> filterList = new ArrayList<EntitySong>();
				for (int i = 0; i < listSongFilter.size(); i++) {
					if ((listSongFilter.get(i).getSong_name().toUpperCase())
							.contains(constraint.toString().toUpperCase())) {

						EntitySong song = new EntitySong();
						song.setSong_name(listSongFilter.get(i).getSong_name());
						song.setSong_singer(listSongFilter.get(i).getSong_singer());
						song.setSong_url(listSongFilter.get(i).getSong_url());
						song.setSong_file(listSongFilter.get(i).getSong_file());
						filterList.add(song);
					}
				}
				results.count = filterList.size();
				results.values = filterList;
			} else {
				results.count = listSongFilter.size();
				results.values = listSongFilter;
			}
			return results;

		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			listSong = (ArrayList<EntitySong>) results.values;
			notifyDataSetChanged();
		}

	}
	
	private static class ViewHolder {
		ImageView img_icon;
		ImageView img_extend;
		TextView txt_song_name;
		TextView txt_song_singer;
	}

	@Override
	public Filter getFilter() {
		if (valueFilter == null) {
			valueFilter = new ValueFilter();
		}
		return valueFilter;
	}

}
