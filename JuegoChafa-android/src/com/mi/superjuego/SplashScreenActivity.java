package com.mi.superjuego;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;

public class SplashScreenActivity extends Activity {

  private long splashDelay = 6000; //a qui esperas el tiempo
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getWindow().setFlags(
    		WindowManager.LayoutParams.FLAG_FULLSCREEN,
    		WindowManager.LayoutParams.FLAG_FULLSCREEN);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_splash_screen);
    
    

    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();//Se supone que esto es para que no  pueda quitarse
      }
    };

    Timer timer = new Timer();
    timer.schedule(task, splashDelay);//Pasando los 6 segundos
    
  }

}