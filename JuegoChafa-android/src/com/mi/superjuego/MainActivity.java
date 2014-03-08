package com.mi.superjuego;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AndroidApplication{
	
	protected AdView adView; //small ad
	protected AdView fullAdView; //big ad
	
	private final String ADCODE = "ca-app-pub-6567038627299791/2475647667";
    private final String FULLADCODE = "ca-app-pub-6567038627299791/4290458065";
    
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
    	
    	AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        RelativeLayout layout = new RelativeLayout(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //no title is needed
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        
        
        adView = new AdView(this); // Put in your secret key here
        adView.setAdUnitId(ADCODE);
        adView.setAdSize(AdSize.SMART_BANNER);
        
        fullAdView = new AdView(this); // Put in your secret key here
        fullAdView.setAdUnitId(FULLADCODE);
        fullAdView.setAdSize(AdSize.BANNER);
        
   
        
        //adView.loadAd(new AdRequest.Builder().build());
        //fullAdView.loadAd(new AdRequest.Builder().build());
        
        View gameView = initializeForView(new ChafaGame(new RequestHandler(adView, fullAdView)), cfg);
       
  
        
        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams fadParams = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
        
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        fadParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        
        layout.addView(adView, adParams);
        layout.addView(fullAdView, fadParams);
        

    // Start loading the ad in the background.
        
        adView.loadAd(new AdRequest.Builder().build());
        setContentView(layout);
    }
}