package com.iphonmusic.config;

import java.util.ArrayList;

import com.iphonmusic.entity.EntityFolder;
import com.iphonmusic.entity.EntitySong;
import com.iphonmusic.entity.EntityVideo;

public class Instance {
	
	public static ArrayList<EntitySong> LISTSONG = new ArrayList<EntitySong>();
	public static ArrayList<EntityFolder> LISTFOLDER = new ArrayList<EntityFolder>();
	public static ArrayList<EntitySong> LISTSONG_CHOISE = new ArrayList<EntitySong>();
	public static ArrayList<EntitySong> LISTSONGALL_CHOISE = new ArrayList<EntitySong>();
	public static ArrayList<EntitySong> LISTSONG_FOR_PLAY = new ArrayList<EntitySong>();
	public static ArrayList<EntityVideo> LIST_VIDEO = new ArrayList<EntityVideo>();
	
	public static String SEARCH = "nhac hot";

}
