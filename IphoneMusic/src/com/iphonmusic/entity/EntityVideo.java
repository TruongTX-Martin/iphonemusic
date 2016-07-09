package com.iphonmusic.entity;

import android.graphics.Bitmap;

public class EntityVideo {

	private String video_name;
	private String video_url;
	private Bitmap video_bitmap_thumb;

	public EntityVideo() {
	}

	public EntityVideo(String name, String url) {
		this.video_name = name;
		this.video_url = url;
	}

	public void setVideo_bitmap_thumb(Bitmap video_bitmap_thumb) {
		this.video_bitmap_thumb = video_bitmap_thumb;
	}

	public Bitmap getVideo_bitmap_thumb() {
		return video_bitmap_thumb;
	}

	public String getVideo_name() {
		return video_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

}
