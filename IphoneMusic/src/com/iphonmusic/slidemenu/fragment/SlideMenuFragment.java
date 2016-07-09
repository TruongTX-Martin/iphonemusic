package com.iphonmusic.slidemenu.fragment;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.slidemenu.delegate.CloseSlideMenuDelegate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SlideMenuFragment extends BaseFragment implements CloseSlideMenuDelegate{
	protected View mFragmentContainerView;
	protected DrawerLayout mDrawerLayout;
	protected View rootView;
	protected ActionBarDrawerToggle mDrawerToggle;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(
				Rconfig.getInstance().getId(getActivity(),
						"core_slidemenu_layout", "layout"), container, false);
		rootView.setBackgroundColor(Color.parseColor("#ffffff"));
		return rootView;
	}
	public void setup(int fragmentId, DrawerLayout drawerLayout) {
		// ActionBar actionBar = getActionBar();
		// actionBar.setDisplayHomeAsUpEnabled(true);
		// actionBar.setHomeButtonEnabled(true);
		int mIdIconDrawer = Rconfig.getInstance().drawable("ic_menu");

		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = (DrawerLayout) getActivity().findViewById(
				Rconfig.getInstance().id("drawer_layout"));
		mDrawerLayout.setDrawerShadow(
				Rconfig.getInstance().drawable("drawer_shadow"),
				GravityCompat.START);

		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
				mIdIconDrawer, Rconfig.getInstance().string(
						"navigation_drawer_open"), Rconfig.getInstance()
						.string("navigation_drawer_close")) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActivity().supportInvalidateOptionsMenu();
			}
		};
		
		// mDrawerLayout.openDrawer(mFragmentContainerView);
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		initView();
	}
	public void initView(){
		PhoneSlideMenuFragment fragment = new PhoneSlideMenuFragment();
		fragment.setCloseDelegate(this);
		replaceFragment(fragment);
	}
	public void replaceFragment(BaseFragment fragment){
		FragmentManager manager = getChildFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		int container = Rconfig.getInstance().id("contain_slidemenu");
		transaction.replace(container, fragment);
		transaction.commit();
	}
	public void openMenu() {
		mDrawerLayout.openDrawer(mFragmentContainerView);
	}
	
	public boolean checkNavigation(){
		if (mDrawerLayout.isDrawerOpen(mFragmentContainerView)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void closeSlideMenu() {
		mDrawerLayout.closeDrawer(mFragmentContainerView);
	}
}
