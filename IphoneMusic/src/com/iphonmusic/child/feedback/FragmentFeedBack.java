package com.iphonmusic.child.feedback;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iphonmusic.base.fragment.BaseFragment;
import com.iphonmusic.base.manager.BaseManager;
import com.iphonmusic.config.Rconfig;
import com.iphonmusic.style.FloatLabel;

public class FragmentFeedBack extends BaseFragment {

	private EditText edt_content;
	private EditText edt_email;
	private Button btn_send;

	public static FragmentFeedBack newInstance() {
		FragmentFeedBack feedBack = new FragmentFeedBack();
		return feedBack;
	}

	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				Rconfig.getInstance().layout("layout_fragment_feedback"), null,
				false);
		edt_email = (EditText) view.findViewById(Rconfig.getInstance().id(
				"float_label_email"));
		edt_content = (EditText) view.findViewById(Rconfig.getInstance().id(
				"edt_content"));
		btn_send = (Button) view.findViewById(Rconfig.getInstance().id(
				"btn_submit"));
		handleEvent();
		return view;
	}

	void handleEvent() {
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (edt_email.getText().toString().length() > 0
						&& edt_email.getText().toString().matches(emailPattern)
						&& edt_content.getText().toString().length() > 0) {
					sendEmail();
				}
			}
		});
	}

	private void sendEmail(){
		 Intent email = new Intent(Intent.ACTION_SEND);
		  email.putExtra(Intent.EXTRA_EMAIL, new String[]{"truongbkit@gmail.com"});
		  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
		  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
		  email.putExtra(Intent.EXTRA_SUBJECT, "Customer Feedback:");
		  email.putExtra(Intent.EXTRA_TEXT, edt_content.getText().toString());

		  //need this to prompts email client only
		  email.setType("message/rfc822");
		  
		  startActivityForResult(Intent.createChooser(email, edt_email.getText().toString()), 111);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BaseManager.getIntance().getSlideMenuController().closeSlideMenu();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@SuppressLint("NewApi")
	private static class CustomLabelAnimator implements
			FloatLabel.LabelAnimator {
		/* package */static final float SCALE_X_SHOWN = 1f;
		/* package */static final float SCALE_X_HIDDEN = 2f;
		/* package */static final float SCALE_Y_SHOWN = 1f;
		/* package */static final float SCALE_Y_HIDDEN = 0f;

		@Override
		public void onDisplayLabel(View label) {
			final float shift = label.getWidth() / 2;
			label.setScaleX(SCALE_X_HIDDEN);
			label.setScaleY(SCALE_Y_HIDDEN);
			label.setX(shift);
			label.animate().alpha(1).scaleX(SCALE_X_SHOWN)
					.scaleY(SCALE_Y_SHOWN).x(0f);
		}

		@Override
		public void onHideLabel(View label) {
			final float shift = label.getWidth() / 2;
			label.setScaleX(SCALE_X_SHOWN);
			label.setScaleY(SCALE_Y_SHOWN);
			label.setX(0f);
			label.animate().alpha(0).scaleX(SCALE_X_HIDDEN)
					.scaleY(SCALE_Y_HIDDEN).x(shift);
		}
	}

}
