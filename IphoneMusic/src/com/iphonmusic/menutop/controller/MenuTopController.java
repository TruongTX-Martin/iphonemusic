package com.iphonmusic.menutop.controller;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.iphonmusic.base.controller.BaseController;
import com.iphonmusic.menutop.delegate.MenuTopDelegate;
import com.iphonmusic.slidemenu.fragment.SlideMenuFragment;

public class MenuTopController extends BaseController {
	protected OnTouchListener mTouchMenu;
	protected SlideMenuFragment mNavigationDrawerFragment;
	protected MenuTopDelegate mMenuTopDelegate;
	
	public void setSlideMenu(SlideMenuFragment mNavigationDrawerFragment) {
		this.mNavigationDrawerFragment = mNavigationDrawerFragment;
	}
	 
	public void setMenuTopDelegate(MenuTopDelegate mMenuTopDelegate) {
		this.mMenuTopDelegate = mMenuTopDelegate;
	}
	@Override
	public void onStart() {
		init();
	}
	
	@Override
	public void onResume() {
	}
	
	public void init(){
		mTouchMenu = new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					v.setBackgroundColor(0x80CACACA);
					break;
				}
				case MotionEvent.ACTION_UP: {
					
					if (mNavigationDrawerFragment.checkNavigation()) {
						mNavigationDrawerFragment.closeSlideMenu();
//						mSlideMenuDelegate.visiableCart();
					} else {
						mNavigationDrawerFragment.openMenu();
					}
					
				}

				case MotionEvent.ACTION_CANCEL: {
					v.setBackgroundColor(0);
					break;
				}
				default:
					break;
				}
				return true;
			}
		};
	}
	
	public OnTouchListener getTouchMenu() {
		return mTouchMenu;
	}

	public void setTouchMenu(OnTouchListener mTouchMenu) {
		this.mTouchMenu = mTouchMenu;
	}
	
	public void invisiableCartLayout(){
		mMenuTopDelegate.controllCartLayout();
	}
	
}
