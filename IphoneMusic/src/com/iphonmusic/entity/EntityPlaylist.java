package com.iphonmusic.entity;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iphonmusic.base.manager.BaseManager;

public class EntityPlaylist {

	private String playlistName;

	public static final String TABLE_PLAYLIST = "playlist";

	public static final String PLAYLIST_ID = "playlist_id";
	public static final String PLAYLIST_NAME = "playlist_name";

	public EntityPlaylist() {
	}

	public EntityPlaylist(String name) {
		this.playlistName = name;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public static void addItemPlaylist(EntityPlaylist playlist) {
		try {
			SQLiteDatabase database = BaseManager.getIntance()
					.getDatabaseHandler().getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(PLAYLIST_NAME, playlist.getPlaylistName());
			if (!checkExitPlaylist(playlist)) {
				database.insert(TABLE_PLAYLIST, null, values);
			}
			database.close();
		} catch (Exception e) {
		}
	}

	private static boolean checkExitPlaylist(EntityPlaylist playlist) {
		if (getAllPlayList().size() > 0) {
			for (EntityPlaylist entityPlaylist : getAllPlayList()) {
				if (entityPlaylist.getPlaylistName().equals(
						playlist.getPlaylistName())) {
					return true;
				}
			}
		}
		return false;
	}

	public static ArrayList<EntityPlaylist> getAllPlayList() {
		ArrayList<EntityPlaylist> playlists = new ArrayList<EntityPlaylist>();
		try {
			String query = "SELECT * FROM " + TABLE_PLAYLIST;
			SQLiteDatabase database = BaseManager.getIntance()
					.getDatabaseHandler().getWritableDatabase();
			Cursor cursor = database.rawQuery(query, null);
			if (cursor.moveToFirst()) {
				do {
					EntityPlaylist entity = new EntityPlaylist();
					entity.setPlaylistName(cursor.getString(1));
					playlists.add(entity);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return playlists;
	}

	public static String getIdPlaylist(EntityPlaylist playlist) {
		try {
			String query = "SELECT * FROM " + TABLE_PLAYLIST;
			SQLiteDatabase database = BaseManager.getIntance()
					.getDatabaseHandler().getWritableDatabase();
			Cursor cursor = database.rawQuery(query, null);
			if (cursor.moveToFirst()) {
				do {
					if (cursor.getString(0) != null
							&& cursor.getString(1).equals(
									playlist.getPlaylistName())) {
						return cursor.getString(0);
					}
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

}
