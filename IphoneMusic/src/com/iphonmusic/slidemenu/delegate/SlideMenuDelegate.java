package com.iphonmusic.slidemenu.delegate;

import java.util.ArrayList;

import com.iphonmusic.slidemenu.entity.ItemNavigation;

public interface SlideMenuDelegate {
	public void onSelectedItem(int position);

	public void onRefresh();

	public void setAdapter(ArrayList<ItemNavigation> items);

	
}
