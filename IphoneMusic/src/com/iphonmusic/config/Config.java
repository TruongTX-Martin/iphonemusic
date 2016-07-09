package com.iphonmusic.config;

import java.util.Map;

import android.graphics.Color;

public class Config {

	private static Config instance;

	private String mThemeColor = "#3498DB";
	private String section_text_color = "#000000";
	private String section_color = "#E0E0E0";
	private String key_color = "#3498DB";
	private Map<String, String> mLanguages;
	
	private boolean isPlay = false;
	private boolean isRepeat ;
	private boolean isShuffle;
	private boolean isPlayOnline;
	private boolean isRepeatOnline;
	
	private String url_mp3zing = "http://mp3.zing.vn/";

	public static Config getInstance() {
		if (null == instance) {
			instance = new Config();
		}

		return instance;
	}
	
	public void setRepeatOnline(boolean input){
		this.isRepeatOnline = input;
	}
	public boolean getIsRepeatOnline(){
		return isRepeatOnline;
	}
	public void setPlayOnline(boolean play){
		this.isPlayOnline = play;
	}
	public boolean getPlayOnline(){
		return isPlayOnline;
	}
	
	public String getUrl_mp3zing() {
		return url_mp3zing;
	}
	public void setShuffle(boolean input){
		this.isShuffle = input;
	}
	public boolean getShuffle(){
		return isShuffle;
	}
	public void setRepeat(boolean input) {
		this.isRepeat  = input;
	}
	
	public boolean getIsRepeat() {
		return isRepeat;
	}
	public void setPlay(boolean input){
		this.isPlay = input;
	}
	public boolean getIsPlay(){
		return isPlay;
	}

	public int getColorMain() {
		return Color.parseColor(mThemeColor);
	}

	public int getSection_text_color() {
		return Color.parseColor(section_text_color);
	}

	public void setSection_text_color(String section_text_color) {
		this.section_text_color = section_text_color;
	}
	public String getSection_color() {
		return section_color;
	}

	public void setSection_color(String section_color) {
		this.section_color = section_color;
	}

	public int getKey_color() {
		return Color.parseColor(key_color);
	}

	public void setKey_color(String key_color) {
		this.key_color = key_color;
	}

}
