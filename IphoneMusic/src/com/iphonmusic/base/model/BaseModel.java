package com.iphonmusic.base.model;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.iphonmusic.base.delegate.ModelDelegate;
import com.iphonmusic.base.delegate.NetworkDelegate;
import com.iphonmusic.base.network.request.CoreRequest;
import com.iphonmusic.base.network.response.CoreResponse;
import com.iphonmusic.config.Constant;

public class BaseModel {

	private NetworkDelegate mNetworkDelegate;
	private CoreRequest mCoreRequest;
	private ModelDelegate mModelDelegate;
	private HashMap<String, Object> mHashMap;

	public String url_request = Constant.BASE_URL;

	public BaseModel() {
		mHashMap = new HashMap<String, Object>();
	}

	public void addParam(String tag, String value) {
		mHashMap.put(tag, value);

	}

	public void addParam(String tag, JSONArray value) {
		mHashMap.put(tag, value);
	}

	public void addParam(String tag, JSONObject value) {
		mHashMap.put(tag, value);
	}

	private void initDelegate() {
		mNetworkDelegate = new NetworkDelegate() {

			@Override
			public void callBack(CoreResponse coreResponse, boolean isSuccess) {
				if (isSuccess) {

				}
				mModelDelegate.callBack(coreResponse, isSuccess);
			}
		};
	}

	public void request() {
		initRequest();
		mCoreRequest = new CoreRequest(mNetworkDelegate);
		mCoreRequest.request(mHashMap);
	}

	private void initRequest() {
		initDelegate();
		setUrl_request();
	}

	public void setModelDelegate(ModelDelegate delegate) {
		this.mModelDelegate = delegate;
	}

	public void setUrl_request() {

	}

}
