package com.iphonmusic.base.delegate;

import com.iphonmusic.base.network.response.CoreResponse;

public interface ModelDelegate {
	public abstract void callBack(CoreResponse coreResponse, boolean isSuccess);
}
