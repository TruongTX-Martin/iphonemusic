package com.iphonmusic.base.block;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseBlock {
	
	protected View mView;
	protected Context mContext;
	protected ViewGroup viewGroup;
	
	public BaseBlock(View v, Context context){
		mView = v;
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewGroup = ((ViewGroup) mView);
	}
	public BaseBlock() {
	}

	public View getView() {
		return mView;
	}

	public void initView() {
		
	}
}
