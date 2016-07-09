package com.iphonmusic;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.Toast;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.DatabaseHandler;
import com.iphonmusic.fragment.FragmentHome;
import com.iphonmusic.menubottom.FragmentBottom;
import com.iphonmusic.menutop.fragment.MenuTopFragment;
import com.iphonmusic.report.UnCaughtException;
import com.iphonmusic.slidemenu.fragment.SlideMenuFragment;

public class MainActivity extends FragmentActivity {

	private SlideMenuFragment mNavigationDrawerFragment;
	public static Activity context;
	public static MainActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		DatabaseHandler databaseHandler = new DatabaseHandler(this);
		BaseManager.getIntance().setDatabaseHandler(databaseHandler);
		BaseManager.getIntance().setCurrentActivity(this);
		BaseManager.getIntance().setCurrentContext(getApplicationContext());
		context = this;
		setContentView(R.layout.core_main_activity);
		try {
			// setContentView(Rconfig.getInstance().layout(
			// "core_main_activity"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Log.e("List Song =================>", Instance.LISTSONG.size() + "");
		mNavigationDrawerFragment = (SlideMenuFragment) getSupportFragmentManager()
				.findFragmentById(Rconfig.getInstance().id("navigation_drawer"));
		mNavigationDrawerFragment.setup(
				Rconfig.getInstance().id("navigation_drawer"),
				(DrawerLayout) findViewById(Rconfig.getInstance().id(
						"drawer_layout")));

		FragmentTransaction mFragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		MenuTopFragment fragmentMenuTop = MenuTopFragment
				.newInstance(mNavigationDrawerFragment);
		mFragmentTransaction.replace(Rconfig.getInstance().id("menu_top"),
				fragmentMenuTop);

		FragmentBottom bottom = FragmentBottom.newInstance();
		mFragmentTransaction.replace(Rconfig.getInstance().id("layout_bottom"),
				bottom);
		mFragmentTransaction.commit();
		BaseManager.getIntance().setManager(getSupportFragmentManager());
	}

	@Override
	public void onBackPressed() {
		List<Fragment> list = BaseManager.getIntance().getManager()
				.getFragments();
		int count = BaseManager.getIntance().getManager()
				.getBackStackEntryCount();
		if (count == 1) {
			AlertDialog.Builder alertboxDowload = new AlertDialog.Builder(this);
			alertboxDowload.setTitle("Close Application");
			alertboxDowload.setMessage("Are you sure you want to exit?");
			alertboxDowload.setCancelable(false);
			alertboxDowload.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							context = null;
							BaseManager.getIntance().getManager()
									.popBackStack();
							android.os.Process.killProcess(android.os.Process
									.myPid());
							finish();
						}
					});
			alertboxDowload.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
						}
					});
			alertboxDowload.show();
		} else {
			super.onBackPressed();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0) {
			Toast.makeText(this, "Thank you for your feedback!",
					Toast.LENGTH_LONG).show();
			FragmentHome fragmentHome = FragmentHome.newInstance();
			BaseManager.getIntance().replaceFragment(fragmentHome);
		}
	}
}
