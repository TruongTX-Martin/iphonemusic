package com.iphonmusic.slidemenu.block;

import java.util.ArrayList;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.child.feedback.FragmentFeedBack;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.slidemenu.adapter.SlideMenuAdapter;
import com.iphonmusic.slidemenu.delegate.SlideMenuDelegate;
import com.iphonmusic.slidemenu.entity.ItemNavigation;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PhoneSlideMenuBlock implements SlideMenuDelegate {
	protected ListView list_navigation;
	protected View mView;
	protected Context mContext;
	protected SlideMenuAdapter mAdapter;
	private TextView txt_feedback;
	private ImageView img_expand;

	public PhoneSlideMenuBlock(View mView, Context mContext) {
		super();
		this.mView = mView;
		this.mContext = mContext;
	}

	public void setListener(OnItemClickListener listener) {
		list_navigation.setOnItemClickListener(listener);
	}

	@Override
	public void onSelectedItem(int position) {
		// TODO Auto-generated method stub
		list_navigation.setItemChecked(position, true);
	}

	public void initView() {
		list_navigation = (ListView) mView.findViewById(Rconfig.getInstance()
				.id("lv_navigation"));
		txt_feedback = (TextView) mView.findViewById(Rconfig.getInstance().id(
				"txt_feedback"));
		img_expand = (ImageView) mView.findViewById(Rconfig.getInstance().id(
				"img_expand"));
		img_expand.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentFeedBack feedBack = FragmentFeedBack.newInstance();
				BaseManager.getIntance().replaceFragment(feedBack);
			}
		});
		txt_feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentFeedBack feedBack = FragmentFeedBack.newInstance();
				BaseManager.getIntance().replaceFragment(feedBack);
			}
		});
	}

	@Override
	public void onRefresh() {

	}

	public void setClicker(OnItemClickListener clicker) {
		list_navigation.setOnItemClickListener(clicker);
	}

	@Override
	public void setAdapter(ArrayList<ItemNavigation> items) {
		// TODO Auto-generated method stub
		mAdapter = new SlideMenuAdapter(items, mContext);
		list_navigation.setAdapter(mAdapter);
	}

}
