package com.iphonmusic.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Constant;

public class EntitySong {

	private boolean isCheck;
	private String song_name;
	private String song_url;
	private File song_file;
	private String sone_singer;
	private String song_tag;
	// Song table name
	public static final String TABLE_ITEMSONGS = "iphonesong";

	// Song Table Columns names
	public static final String KEY_ID = "song_id";
	public static final String KEY_TAG = "song_tag";
	public static final String KEY_SONG_NAME = "song_name";
	public static final String KEY_SONG_URL = "song_url";
	public static final String KEY_SONG_FILE = "song_file";
	public static final String KEY_SONG_SINGER = "song_singer";
	public static final String KEY_PLAYLIST_ID = "playlist_id";

	public EntitySong() {
	}

	public void setCheck(boolean check) {
		this.isCheck = check;
	}

	public boolean getCheck() {
		return isCheck;
	}

	public void setSong_tag(String song_tag) {
		this.song_tag = song_tag;
	}

	public String getSong_tag() {
		return song_tag;
	}

	public void setSong_singer(String sone_singer) {
		this.sone_singer = sone_singer;
	}

	public String getSong_singer() {
		return sone_singer;
	}

	public void setSong_file(File song_file) {
		this.song_file = song_file;
	}

	public File getSong_file() {
		return song_file;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getSong_url() {
		return song_url;
	}

	public void setSong_url(String sone_url) {
		this.song_url = sone_url;
	}

	public static void addItemWishList(EntitySong song) {
		try {
			SQLiteDatabase database = BaseManager.getIntance()
					.getDatabaseHandler().getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_SONG_NAME, song.getSong_name());
			values.put(KEY_SONG_URL, song.getSong_url());
			values.put(KEY_SONG_SINGER, song.getSong_singer());
			values.put(KEY_SONG_FILE, song.getSong_file().toString());
			values.put(KEY_TAG, Constant.TAG_WISHLISH);

			database.insert(TABLE_ITEMSONGS, null, values);
			database.close();
			Log.e("ENtitySOng-====================>", "Add success");
		} catch (Exception e) {
			Log.e("ENtitySOng- addItemWishList", e.getMessage());
		}
	}

	public static ArrayList<EntitySong> getAllItemWishList() {
		ArrayList<EntitySong> list = new ArrayList<EntitySong>();
		String query = "SELECT * FROM " + TABLE_ITEMSONGS;
		SQLiteDatabase database = BaseManager.getIntance().getDatabaseHandler()
				.getWritableDatabase();
		Cursor cursor = database.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				EntitySong entity = new EntitySong();
				entity.setSong_name(cursor.getString(1));
				entity.setSong_url(cursor.getString(2));
				entity.setSong_singer(cursor.getString(3));
				entity.setSong_file(new File(cursor.getString(4)));
				entity.setSong_tag(cursor.getString(5));
				if (entity.getSong_tag().equals(Constant.TAG_WISHLISH)) {
					list.add(entity);
				}
			} while (cursor.moveToNext());
		}
		return list;
	}

	public static void deleteItemWishList(EntitySong song) {
		SQLiteDatabase db = BaseManager.getIntance().getDatabaseHandler()
				.getWritableDatabase();
		db.delete(TABLE_ITEMSONGS, KEY_SONG_NAME + " = ?",
				new String[] { String.valueOf(song.getSong_name()) });
		db.close();
		Log.e("Delete Item Sucess========== >", "Success");
	}

	public static boolean checkExitsSong(EntitySong entitySong) {
		List<EntitySong> list = getAllItemWishList();
		if (list.size() > 0) {
			for (EntitySong song : list) {
				if (song.getSong_url().equals(entitySong.getSong_url())) {
					return true;
				}
			}
		}
		return false;
	}

	// query for playlist
	public static void addItemToPlayList(String playlistId, EntitySong song) {
		try {
			SQLiteDatabase database = BaseManager.getIntance()
					.getDatabaseHandler().getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_SONG_NAME, song.getSong_name());
			values.put(KEY_SONG_URL, song.getSong_url());
			values.put(KEY_SONG_SINGER, song.getSong_singer());
			values.put(KEY_SONG_FILE, song.getSong_file().toString());
			values.put(KEY_TAG, Constant.TAG_PLAYLIST);
			values.put(KEY_PLAYLIST_ID, playlistId);

			database.insert(TABLE_ITEMSONGS, null, values);
			database.close();
			Log.e("ENtitySOng-====================>", "Add success");
		} catch (Exception e) {
			Log.e("ENtitySOng- addItemWishList", e.getMessage());
		}
	}

	public static void addListItemToPlayList(String playlistId,
			ArrayList<EntitySong> mSong) {
		try {
			if (mSong.size() > 0) {
				SQLiteDatabase database = BaseManager.getIntance()
						.getDatabaseHandler().getWritableDatabase();
				for (EntitySong song : mSong) {
					if(!checkSongExitInPlayList(playlistId, song)){
					ContentValues values = new ContentValues();
					values.put(KEY_SONG_NAME, song.getSong_name());
					values.put(KEY_SONG_URL, song.getSong_url());
					values.put(KEY_SONG_SINGER, song.getSong_singer());
					values.put(KEY_SONG_FILE, song.getSong_file().toString());
					values.put(KEY_TAG, Constant.TAG_PLAYLIST);
					values.put(KEY_PLAYLIST_ID, playlistId);
					database.insert(TABLE_ITEMSONGS, null, values);
					}
				}
				database.close();
			}
			Log.e("ENtitySOng-====================>", "Add success");
		} catch (Exception e) {
			Log.e("ENtitySOng- addItemWishList", e.getMessage());
		}
	}

	public static ArrayList<EntitySong> getItemSongFromPlaylistId(
			String playlistID) {
		ArrayList<EntitySong> mSong = new ArrayList<EntitySong>();
		String query = "SELECT * FROM " + TABLE_ITEMSONGS;
		SQLiteDatabase database = BaseManager.getIntance().getDatabaseHandler()
				.getWritableDatabase();
		Cursor cursor = database.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				EntitySong entity = new EntitySong();
				entity.setSong_name(cursor.getString(1));
				entity.setSong_url(cursor.getString(2));
				entity.setSong_singer(cursor.getString(3));
				entity.setSong_file(new File(cursor.getString(4)));
				entity.setSong_tag(cursor.getString(5));
				if (entity.getSong_tag().equals(Constant.TAG_PLAYLIST)
						&& cursor.getString(6) != null
						&& cursor.getString(6).equals(playlistID)) {
					mSong.add(entity);
				}
			} while (cursor.moveToNext());
		}
		return mSong;
	}

	public static boolean checkSongExitInPlayList(String idPlaylist,
			EntitySong song) {
		ArrayList<EntitySong> mSong = getItemSongFromPlaylistId(idPlaylist);
		if (mSong.size() > 0) {
			for (EntitySong entitySong : mSong) {
				if (entitySong.getSong_name().equals(song.getSong_name())) {
					return true;
				}
			}
		}
		return false;
	}

}
