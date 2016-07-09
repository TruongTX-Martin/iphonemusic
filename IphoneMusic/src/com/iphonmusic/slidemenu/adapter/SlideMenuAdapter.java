package com.iphonmusic.slidemenu.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iphonmusic.config.Constant;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.slidemenu.entity.ItemNavigation;

public class SlideMenuAdapter extends BaseAdapter {
	protected ArrayList<ItemNavigation> mItems;
	protected Context mContext;

	public void setItems(ArrayList<ItemNavigation> items) {
		mItems = items;
	}

	public SlideMenuAdapter(ArrayList<ItemNavigation> mItems, Context mContext) {
		super();
		this.mItems = mItems;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		convertView = inflater.inflate(
				Rconfig.getInstance().layout("core_phone_slide_menu_item"),
				null, false);
		convertView.setBackgroundColor(Color.parseColor("#ffffff"));
		ImageView img_icon = (ImageView) convertView.findViewById(Rconfig
				.getInstance().id("img_icon"));
		TextView txt_name = (TextView) convertView.findViewById(Rconfig
				.getInstance().id("tv_name"));

		ItemNavigation item = (ItemNavigation) getItem(position);
		String name = item.getName();
		txt_name.setText(name);
		txt_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		txt_name.setTextColor(Color.parseColor("#000000"));
		img_icon.setImageDrawable(item.getDrawble());
		if (name.equals(Constant.ITEM_HOME)) {
			img_icon.setColorFilter(Color.parseColor("#000000"));
		}
		return convertView;
	}
}
