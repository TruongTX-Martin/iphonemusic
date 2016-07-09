package com.iphonmusic.child.musiconline;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.iphonmusic.R;
import com.iphonmusic.adapter.AdapterZingMp3;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.child.detailonline.FragmentMusicOnlineDetail;
import com.iphonmusic.config.Config;
import com.iphonmusic.config.Constant;
import com.iphonmusic.config.Instance;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.entity.EntityZingMp3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BlockMusicOnline implements DelegateMusicOnline {

	private View mRootView;
	private Context mContext;

	private ListView mListView;
	private TextView mTextMessage;
	private EditText mEdtSearch;
	private ProgressBar progressBar;

	private ArrayList<EntityZingMp3> mZingMp3 = new ArrayList<EntityZingMp3>();
	private String mSiteName;
	private ImageView img_banner;

	private RelativeLayout rlt_playmusic;
	private ImageView img_playmusic;
	private TextView txt_song;
	private TextView txt_singer;
	private ImageView img_play;
	private ImageView img_next;

	public void setOnKeyEdittext(OnKeyListener listener) {
		mEdtSearch.setOnKeyListener(listener);
	}

	public void setSiteName(String mSiteName) {
		this.mSiteName = mSiteName;
	}

	public BlockMusicOnline(View view, Context context) {
		this.mRootView = view;
		this.mContext = context;
	}

	public void initView() {
		mListView = (ListView) mRootView.findViewById(Rconfig.getInstance().id(
				"listview"));
		mTextMessage = (TextView) mRootView.findViewById(Rconfig.getInstance()
				.id("txt_message"));
		mEdtSearch = (EditText) mRootView.findViewById(Rconfig.getInstance()
				.id("edt_search"));
		mEdtSearch.setText(Instance.SEARCH);
		progressBar = (ProgressBar) mRootView.findViewById(Rconfig
				.getInstance().id("progressbar"));
		updateView();
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EntityZingMp3 entity = (EntityZingMp3) parent
						.getItemAtPosition(position);
				FragmentMusicOnlineDetail detail = FragmentMusicOnlineDetail
						.newInstance();
				detail.setIsNewPlay(true);
				BaseManager.getIntance().setCurrentOnline(entity);
				BaseManager.getIntance().replaceFragment(detail);
			}
		});
		img_banner = (ImageView) mRootView.findViewById(Rconfig.getInstance()
				.id("img_banner"));
		if (mSiteName.equals(Constant.ITEM_MP3_ZING)) {
			Glide.with(mContext).load("").centerCrop()
			.placeholder(R.drawable.ic_banner_zingmp3).into(img_banner);		}
		if (mSiteName.equals(Constant.ITEM_NHACUATUI)) {
			Glide.with(mContext).load("").centerCrop()
			.placeholder(R.drawable.ic_banner_nhaccuattui).into(img_banner);
		}
		if (mSiteName.equals(Constant.ITEM_CHIASENHAC)) {
			img_banner.setImageResource(R.drawable.ic_banner_chiasenhac);
			Glide.with(mContext).load("").centerCrop()
			.placeholder(R.drawable.ic_banner_chiasenhac).into(img_banner);
		}

		img_playmusic = (ImageView) mRootView.findViewById(Rconfig
				.getInstance().id("img_icon_bottom"));
		img_play = (ImageView) mRootView.findViewById(Rconfig.getInstance().id(
				"img_icon_play"));
		img_next = (ImageView) mRootView.findViewById(Rconfig.getInstance().id(
				"img_icon_next"));
		txt_song = (TextView) mRootView.findViewById(Rconfig.getInstance().id(
				"txt_song_name"));
		txt_singer = (TextView) mRootView.findViewById(Rconfig.getInstance()
				.id("txt_singer"));
		rlt_playmusic = (RelativeLayout) mRootView.findViewById(Rconfig
				.getInstance().id("layout_playmusic"));
		if (BaseManager.getIntance().getCurrentOnline() != null) {
			updateMusicOnline(BaseManager.getIntance().getCurrentOnline());
		}
		img_play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Config.getInstance().getPlayOnline()) {
					Config.getInstance().setPlayOnline(false);
					BaseManager.getIntance().pauseMusicOnline();
				} else {
					Config.getInstance().setPlayOnline(true);
					BaseManager.getIntance().continueMusicOnline();
				}
			}
		});
		img_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseManager.getIntance().nextSongOnline();
			}
		});
		rlt_playmusic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentMusicOnlineDetail detail = FragmentMusicOnlineDetail
						.newInstance();
				BaseManager.getIntance().replaceFragment(detail);
			}
		});
		if (Config.getInstance().getPlayOnline()) {
			img_play.setImageResource(Rconfig.getInstance().drawable("ic_play"));
		} else {
			img_play.setImageResource(Rconfig.getInstance()
					.drawable("ic_pause"));
		}

	}

	private void updateView() {
		if (mZingMp3.size() > 0) {
			mListView.setVisibility(View.VISIBLE);
			mTextMessage.setVisibility(View.GONE);
			AdapterZingMp3 adapter = new AdapterZingMp3(mContext, mZingMp3);
			mListView.setAdapter(adapter);
			BaseManager.getIntance().setListEntityOnline(mZingMp3);
		} else {
			mListView.setVisibility(View.GONE);
			mTextMessage.setVisibility(View.VISIBLE);
			mTextMessage.setText("Không tìm thấy bài hát nào");
		}
	}

	@Override
	public EditText getEditTextSearch() {
		return mEdtSearch;
	}

	@Override
	public void updateView(String result) {
		mZingMp3.clear();
		try {
			if (result != null) {
				JSONArray array = new JSONArray(result);
				if (array.length() > 0) {
					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						EntityZingMp3 entity = new EntityZingMp3();
						entity.setJSON(object);
						mZingMp3.add(entity);
					}
					updateView();
				} else {
					updateView();
				}
			}
		} catch (Exception e) {
			Log.e("Block Zing Mp3:", e.getMessage());
		}
	}

	@Override
	public void updateProgressBar(boolean input) {
		if (input) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void updateMusicOnline(EntityZingMp3 mp3) {
		rlt_playmusic.setVisibility(View.VISIBLE);
		txt_song.setText(mp3.getzTitle());
		txt_singer.setText(mp3.getzArtist());
		Glide.with(mContext).load(mp3.getzAvatar()).centerCrop()
				.placeholder(R.drawable.ic_music_item).into(img_playmusic);
		if (Config.getInstance().getPlayOnline()) {
			img_play.setImageResource(Rconfig.getInstance().drawable("ic_play"));
		} else {
			img_play.setImageResource(Rconfig.getInstance()
					.drawable("ic_pause"));
		}
	}

}
