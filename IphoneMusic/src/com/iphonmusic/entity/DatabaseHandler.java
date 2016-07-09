package com.iphonmusic.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Name
	private static final String DATABASE_NAME = "iphonemusic";
	// Database Version
	private static final int DATABASE_VERSION = 1;
	String CREATE_SONG_TABLE = "CREATE TABLE " + EntitySong.TABLE_ITEMSONGS
			+ "(" + EntitySong.KEY_ID + " INTEGER PRIMARY KEY,"
			+ EntitySong.KEY_SONG_NAME + " TEXT," + EntitySong.KEY_SONG_URL
			+ " TEXT," + EntitySong.KEY_SONG_SINGER + " TEXT,"
			+ EntitySong.KEY_SONG_FILE + " TEXT," + EntitySong.KEY_TAG
			+ " TEXT," + EntitySong.KEY_PLAYLIST_ID + " TEXT" + ")";

	String CREATE_PLAYLIST_TABLE = "CREATE TABLE "
			+ EntityPlaylist.TABLE_PLAYLIST + "(" + EntityPlaylist.PLAYLIST_ID
			+ " INTEGER PRIMARY KEY," + EntityPlaylist.PLAYLIST_NAME + " TEXT"
			+ ")";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_SONG_TABLE);
			db.execSQL(CREATE_PLAYLIST_TABLE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + EntitySong.TABLE_ITEMSONGS);
		db.execSQL("DROP TABLE IF EXISTS " + EntityPlaylist.TABLE_PLAYLIST);
		// Create tables again
		onCreate(db);
	}

}
