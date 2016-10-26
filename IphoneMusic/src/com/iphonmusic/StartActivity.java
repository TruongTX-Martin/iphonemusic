package com.iphonmusic;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityFolder;
import com.iphonmusic.entity.EntitySong;
import com.iphonmusic.entity.EntityVideo;

public class StartActivity extends Activity {

	final String MEDIA_PATH = Environment.getExternalStorageDirectory()
			.getPath() + "/";
	ArrayList<File> arrayFolder = new ArrayList<File>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BaseManager.getIntance().setCurrentActivity(this);
		BaseManager.getIntance().setCurrentContext(getApplicationContext());
		// setContentView(R.layout.core_start_activity);
		setContentView(Rconfig.getInstance().layout("core_start_activity"));
		Log.d("Start time==========+>", new Date().toString());
		new getSongAsynTask().execute("");
	}

	private class getSongAsynTask extends
			AsyncTask<String, Void, ArrayList<EntitySong>> {

		@Override
		protected ArrayList<EntitySong> doInBackground(String... params) {
			File dir = new File(MEDIA_PATH);
			getListSongs(new File(MEDIA_PATH));
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<EntitySong> result) {
			new getFolderAsynTask().execute("");
		}

	}

	private class getFolderAsynTask extends
			AsyncTask<String, Void, ArrayList<EntitySong>> {

		@Override
		protected ArrayList<EntitySong> doInBackground(String... params) {
			File dir = new File(MEDIA_PATH);
			getListFolder(new File(MEDIA_PATH));
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<EntitySong> result) {
			if (arrayFolder.size() > 0) {
				for (int i = 0; i < arrayFolder.size(); i++) {
					File file = arrayFolder.get(i);
					EntityFolder entityFolder = new EntityFolder();
					entityFolder.setFolder_file(file.getParentFile());
					Instance.LISTFOLDER.add(entityFolder);
				}
			}
			toMainActivity();
			Log.d("End time==========+>", new Date().toString());
		}

	}

	private void toMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void getListFolder(File dir) {
		String Pattern = ".mp3";
		File listFile[] = dir.listFiles();
		if (listFile != null && listFile.length > 0) {
			for (File file : listFile) {
				if (file != null && !file.isHidden()) {
					if (file.isDirectory()) {
						if (folderHasMp3(file)) {
						}
					} else {
						if (file.getName().endsWith(Pattern)) {
							arrayFolder.add(file);
						}
					}
				}
			}
		}
	}

	public void getListSongs(File dir) {
		String pattemMP3 = ".mp3";
		String pattemMP4 = ".mp4";
		File listFile[] = dir.listFiles();
		if (listFile != null && listFile.length > 0) {
			for (File file : listFile) {
				if (file != null && !file.isHidden()) {
					if (file.isDirectory()) {
						getListSongs(file);
					} else {
						if (file.getName().endsWith(pattemMP3)) {
							String fullName = file.getName().substring(0,
									(file.getName().length() - 4));
							if (!fullName.substring(0, 1).contains(".")) {
								EntitySong song = new EntitySong();
								song.setSong_name(Rconfig.getInstance()
										.getSongName(fullName));
								song.setSong_singer(Rconfig.getInstance()
										.getSingerName(fullName));
								song.setSong_url(file.getPath());
								song.setSong_file(file);
								Instance.LISTSONG.add(song);
							}
						}
//						if (file.getName().endsWith(pattemMP4)
//								&& !file.isHidden()) {
//							String fullName = file.getName().substring(0,
//									(file.getName().length() - 4));
//							if (!fullName.substring(0, 1).contains(".")) {
//								EntityVideo video = new EntityVideo();
//								video.setVideo_name(fullName);
//								video.setVideo_url(file.getAbsolutePath());
//								Bitmap bitmap = ThumbnailUtils
//										.createVideoThumbnail(
//												file.getAbsolutePath(),
//												MediaStore.Video.Thumbnails.MICRO_KIND);
//								video.setVideo_bitmap_thumb(bitmap);
//								Instance.LIST_VIDEO.add(video);
//							}
//						}
					}
				}
			}
		}
	}
	private boolean folderHasMp3(File dir) {
		File listFile[] = dir.listFiles();
		for (File file : listFile) {
			if (file != null && !file.isHidden()) {
				if (file.isDirectory()) {
					folderHasMp3(file);
				} else {
					if (file.getName().endsWith(".mp3")) {
						arrayFolder.add(file);
						return true;
					}
				}
			}
		}
		return false;
	}

	

}
