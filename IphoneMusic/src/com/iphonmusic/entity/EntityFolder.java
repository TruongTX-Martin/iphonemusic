package com.iphonmusic.entity;

import java.io.File;

public class EntityFolder {

	private String folder_name;
	private String folder_url;
	private File folder_file;

	// /storage/emulated/0/DCIM/Tong hop

	public EntityFolder() {
	}

	public void setFolder_file(File folder_file) {
		this.folder_file = folder_file;
	}

	public File getFolder_file() {
		return folder_file;
	}

	public String getFolder_name() {
		if (folder_file != null) {
			String name = folder_file.getPath();
			if (name.contains("/")) {
				String[] array = name.split("/");
				folder_name = array[array.length - 1];
			}
		}
		return folder_name;
	}

	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}

	public String getFolder_url() {
		return folder_url;
	}

	public void setFolder_url(String folder_url) {
		this.folder_url = folder_url;
	}

}
