package com.iphonmusic.config;

import android.content.Context;

import com.iphonmusic.base.manager.BaseManager;

public class Rconfig {
	public Context mContext;
	private String mThemeColor = "#3498DB";
	private static Rconfig instance;

	private Rconfig() {
		mContext = BaseManager.getIntance().getCurrentContext();
	}

	public static Rconfig getInstance() {
		if (instance == null) {
			instance = new Rconfig();
		}
		return instance;
	}

	public String getPackageName() {
		return mContext.getPackageName();
	}

	public int layout(String name) {
		return mContext.getResources().getIdentifier(name, "layout",
				getPackageName());
	}

	public int getId(Context context, String name, String res) {
		return context.getResources()
				.getIdentifier(name, res, getPackageName());
	}

	public int id(String name) {
		return mContext.getResources().getIdentifier(name, "id",
				getPackageName());
	}

	public int drawable(String name) {
		return mContext.getResources().getIdentifier(name, "drawable",
				getPackageName());
	}

	public int string(String name) {
		return mContext.getResources().getIdentifier(name, "string",
				getPackageName());
	}

	public int getId(String name, String res) {
		return mContext.getResources().getIdentifier(name, res,
				getPackageName());
	}
	public int[] getArrayStyleable(String res) {
		String fullName = getPackageName() + ".R$styleable";
		Class<?> style_class;
		try {
			style_class = Class.forName(fullName);
			int[] array = (int[]) style_class.getField(res).get(null);
			return array;
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | NoSuchFieldException e) {
		}

		return null;

	}
	public int getAttr(String name) {
		return mContext.getResources().getIdentifier(name, "attr",
				getPackageName());
	}
	
	public String getSingerName(String fullName) {
		try {
			if(fullName.contains("-") && fullName.contains("_")){
				return fullName.substring(fullName.indexOf("-")+ 1, fullName.indexOf("_")-1).trim();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return  fullName;
	}
	public String getSongName(String fullName) {
		try {
			if(fullName.contains("-")){
				return fullName.substring(0, fullName.indexOf("-")-1).trim();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return  fullName;
	}
}
