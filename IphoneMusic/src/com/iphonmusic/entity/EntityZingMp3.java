package com.iphonmusic.entity;

import org.json.JSONObject;

public class EntityZingMp3 {

	private String zId;
	private String zTitle;
	private String zArtist;
	private String zAvatar;
	private String zUrlDownload;
	private String zLyricUrl;
	private String zUrlSource;
	private String zSiteId;
	private String zHostName;
	private JSONObject mJSON;

	// KEY FOR GET DATA
	private final String ID = "Id";
	private final String TITLE = "Title";
	private final String ARTIST = "Artist";
	private final String AVATAR = "Avatar";
	private final String URLDOWNLOAD = "UrlJunDownload";
	private final String LYRICSURL = "LyricsUrl";
	private final String URLSOURCE = "UrlSource";
	private final String SITEID = "SiteId";
	private final String HOSTNAME = "HostName";

	public void setJSON(JSONObject mJSON) {
		this.mJSON = mJSON;
	}

	public JSONObject getJSON() {
		return mJSON;
	}

	public String getzId() {
		zId = getData(ID);
		return zId;
	}

	public void setzId(String zId) {
		this.zId = zId;
	}

	public String getzTitle() {
		zTitle = getData(TITLE);
		return zTitle;
	}

	public void setzTitle(String zTitle) {
		this.zTitle = zTitle;
	}

	public String getzArtist() {
		zArtist = getData(ARTIST);
		return zArtist;
	}

	public void setzArtist(String zArtist) {
		this.zArtist = zArtist;
	}

	public String getzAvatar() {
		zAvatar = getData(AVATAR);
		return zAvatar;
	}

	public void setzAvatar(String zAvatar) {
		this.zAvatar = zAvatar;
	}

	public String getzUrlDownload() {
		zUrlDownload = getData(URLDOWNLOAD);
		return zUrlDownload;
	}

	public void setzUrlDownload(String zUrlDownload) {
		this.zUrlDownload = zUrlDownload;
	}

	public String getzLyricUrl() {
		zLyricUrl = getData(LYRICSURL);
		return zLyricUrl;
	}

	public void setzLyricUrl(String zLyricUrl) {
		this.zLyricUrl = zLyricUrl;
	}

	public String getzUrlSource() {
		zUrlSource = getData(URLSOURCE);
		return zUrlSource;
	}

	public void setzUrlSource(String zUrlSource) {
		this.zUrlSource = zUrlSource;
	}

	public String getzSiteId() {
		zSiteId = getData(SITEID);
		return zSiteId;
	}

	public void setzSiteId(String zSiteId) {
		this.zSiteId = zSiteId;
	}

	public String getzHostName() {
		zHostName = getData(HOSTNAME);
		return zHostName;
	}

	public void setzHostName(String zHostName) {
		this.zHostName = zHostName;
	}

	private String getData(String key) {
		try {
			if (mJSON != null && mJSON.has(key)) {
				return mJSON.getString(key);
			}
		} catch (Exception e) {
		}
		return null;
	}

}
