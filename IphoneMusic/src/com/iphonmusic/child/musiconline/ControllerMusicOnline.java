package com.iphonmusic.child.musiconline;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import com.iphonmusic.base.delegate.ModelDelegate;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.base.network.response.CoreResponse;
import com.iphonmusic.config.Constant;
import com.iphonmusic.config.Instance;
import com.iphonmusic.entity.EntityZingMp3;

public class ControllerMusicOnline {

	private DelegateMusicOnline mDelegate;
	private OnKeyListener onKeyListener;
	private String mSiteName;

	public void setDelegate(DelegateMusicOnline mDelegate) {
		this.mDelegate = mDelegate;
	}
	
	public void setSiteName(String mSiteName) {
		this.mSiteName = mSiteName;
	}

	public OnKeyListener getOnKeyListenerEditText() {
		return onKeyListener;
	}

	public ControllerMusicOnline() {
		initListener();
	}

	void initListener() {
		onKeyListener = new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SEARCH)
						&& (event.getAction() == KeyEvent.ACTION_DOWN)) {
					String query = mDelegate.getEditTextSearch().getText()
							.toString();
					BaseManager.getIntance().hideKeyBoard();
					if (query.length() > 0) {
						request(query.trim());
					}
					return true;
				}
				return false;
			}
		};
	}

	public void request(String query) {
		Instance.SEARCH = query;
		mDelegate.updateProgressBar(true);
		ModelMusicOnline  mModel = new ModelMusicOnline();
		mModel.setModelDelegate(new ModelDelegate() {
			
			@Override
			public void callBack(CoreResponse coreResponse, boolean isSuccess) {
				String result = coreResponse.getData();
				mDelegate.updateView(result);
				mDelegate.updateProgressBar(false);
			}
		});
		if(query.trim().contains(" ")){
			query = query.replace(" ", "+");
			mModel.addParam("k", query);
		}else{
			mModel.addParam("k", query);
		}
		if(mSiteName.equals(Constant.ITEM_MP3_ZING)){
			mModel.addParam("h", Constant.SITE_MP3_ZING);
		}
		if(mSiteName.equals(Constant.ITEM_NHACUATUI)){
			mModel.addParam("h", Constant.SITE_NHAC_CUA_TUI);
		}
		if(mSiteName.equals(Constant.ITEM_CHIASENHAC)){
			mModel.addParam("h", Constant.SITE_CHIA_SE_NHAC);
		}
		mModel.request();
	}
	
	private String getQuery(String query){
		return query.replace(" ", "+");
	}

	public void updateMusicOnline(EntityZingMp3 zingMp3){
		mDelegate.updateMusicOnline(zingMp3);
	}
}
