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
import com.iphonmusic.entity.EntityFolder;

public class AdapterFolder extends BaseAdapter {

	private Context mContext;
	private ArrayList<EntityFolder> mFolder;

	public AdapterFolder(Context context, ArrayList<EntityFolder> arrayList) {
		this.mContext = context;
		this.mFolder = arrayList;
	}

	@Override
	public int getCount() {
		return mFolder.size();
	}

	@Override
	public Object getItem(int position) {
		return mFolder.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(
					Rconfig.getInstance().layout("layout_item_folder"), null);
			holder = new ViewHolder();
			holder.img_folder = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_icon"));
			holder.img_extend = (ImageView) convertView.findViewById(Rconfig
					.getInstance().id("img_expand"));
			holder.txt_folder_name = (TextView) convertView
					.findViewById(Rconfig.getInstance().id("txt_name_folder"));
			holder.txt_folder_path = (TextView) convertView
					.findViewById(Rconfig.getInstance().id("txt_path_folder"));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		EntityFolder folder = (EntityFolder) getItem(position);

		holder.txt_folder_name.setText(folder.getFolder_name());
		holder.txt_folder_path.setText(folder.getFolder_file().getPath());
		Glide.with(mContext).load("").centerCrop()
				.placeholder(R.drawable.ic_folder).into(holder.img_folder);
		Glide.with(mContext).load("").centerCrop()
				.placeholder(R.drawable.ic_extend).into(holder.img_extend);
		return convertView;
	}

	private static class ViewHolder {
		private ImageView img_folder;
		private TextView txt_folder_name;
		private TextView txt_folder_path;
		private ImageView img_extend;
	}

}
