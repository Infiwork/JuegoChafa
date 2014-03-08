package com.mi.superjuego;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.android.gms.ads.AdView;

public class RequestHandler implements IReqHandler {

	protected AdView adV;
	protected AdView fadV;
	protected int adHeight = 10;

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;
	private final int SHOW_FULLADS = 3;
	private final int HIDE_FULLADS = 4;

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_ADS: {
				adV.setVisibility(View.VISIBLE);
				break;
			}
			case HIDE_ADS: {
				adV.setVisibility(View.GONE);
				break;
			}
			case SHOW_FULLADS: {
				fadV.setVisibility(View.VISIBLE);
				break;
			}
			case HIDE_FULLADS: {
				fadV.setVisibility(View.GONE);
				break;
			}
			}
		}
	};

	public void showFullAd(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_FULLADS : HIDE_FULLADS);
	}

	public RequestHandler(AdView adView, AdView fad) {
		this.adV = adView;
		this.fadV = fad;
	}

	public void showAd(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}

}
