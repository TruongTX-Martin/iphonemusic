package com.iphonmusic.base.network.response;

import org.jsoup.nodes.Document;

public class CoreResponse {

	private String data;

	private Document document;

	public void setDocument(Document document) {
		this.document = document;
	}

	public Document getDocument() {
		return document;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

}
