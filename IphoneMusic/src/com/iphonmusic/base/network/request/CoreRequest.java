package com.iphonmusic.base.network.request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.widget.Toast;

import com.iphonmusic.base.delegate.NetworkDelegate;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.base.network.response.CoreResponse;
import com.iphonmusic.base.network.volley.AppController;
import com.iphonmusic.base.network.volley.Request.Method;
import com.iphonmusic.base.network.volley.Response;
import com.iphonmusic.base.network.volley.VolleyError;
import com.iphonmusic.base.network.volley.customrequest.CustomRequest;
import com.iphonmusic.config.Constant;

public class CoreRequest {

	private NetworkDelegate mDelegate;

	public CoreRequest(NetworkDelegate delegate) {
		this.mDelegate = delegate;

	}

	public void request(HashMap<String, Object> hashMap) {
		String url = getUrlFromParam(hashMap);
		System.out.println(url);
		CustomRequest request = new CustomRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						excuteResult(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(
								BaseManager.getIntance().getCurrentContext(),
								"Please check internet connection?",
								Toast.LENGTH_SHORT).show();
					}
				});
		request.setShouldCache(false);
		AppController.getInstance().addToRequestQueue(request, "tagjson");
	}

	private String getUrlFromParam(HashMap<String, Object> hashMap) {
		String url = Constant.BASE_URL + "?code=[" + Constant.KEYCODE + "]";
		if (!hashMap.isEmpty()) {
			Iterator<Entry<String, Object>> iterator = hashMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String key = String.valueOf(entry.getKey());
				String value = entry.getValue().toString();
				url += "&" + key + "=" + value;
			}
		}
		return url;
	}

	private void excuteResult(String result) {
		CoreResponse response = new CoreResponse();
		response.setData(result);
		mDelegate.callBack(response, true);
	}

}
