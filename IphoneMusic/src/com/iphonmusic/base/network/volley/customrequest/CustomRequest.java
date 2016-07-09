package com.iphonmusic.base.network.volley.customrequest;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import android.util.Log;

import com.iphonmusic.base.network.volley.AppController;
import com.iphonmusic.base.network.volley.AuthFailureError;
import com.iphonmusic.base.network.volley.NetworkResponse;
import com.iphonmusic.base.network.volley.Response;
import com.iphonmusic.base.network.volley.Response.ErrorListener;
import com.iphonmusic.base.network.volley.Response.Listener;
import com.iphonmusic.base.network.volley.toolbox.StringRequest;
import com.iphonmusic.config.Config;

public class CustomRequest extends StringRequest {

	private Map<String, String> mHeaders = new HashMap<>();
	private String url = "";

	public CustomRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
		this.url = url;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return mHeaders;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {

		return super.parseNetworkResponse(response);
	}
}
