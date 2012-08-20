package com.morgajel.ziggyswift;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SplashActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private ZiggyFace ziggy;
	
	private Timer timer;
	private int emotioncounter=0;
	private static int TIMER_WAIT=1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Context context= this.getApplicationContext();
        ziggy=new ZiggyFace(context);
     
        ImageView ziggyView= (ImageView) this.findViewById(R.id.splashface);

        ziggyView.setOnClickListener(this);
        ziggyView.setImageDrawable(ziggy.getRandomFace());
        timer = new Timer();
        timer.schedule(new UpdateTimeTask(this), TIMER_WAIT);
        Log.i("creation","complete");
    }
    
	@Override
	public void onClick(View arg0) {
    	Log.i("onclick","setdrawable");
    	ImageView ziggyView = (ImageView) this.findViewById(R.id.splashface);
    	ziggyView.setImageDrawable(ziggy.getRandomFace());
    	Log.i("onclick","complete");
	}
    
	class UpdateTimeTask extends TimerTask {
			Activity act;
			ImageView view;
			public UpdateTimeTask(Activity pAct){
				act=pAct;
				view=(ImageView) act.findViewById(R.id.splashface);
			} 
		   	public void run() {
			   Log.i("UpdateTimeTask","running with " + emotioncounter);
			   if (emotioncounter< 3){
				   emotioncounter++;
				   view.post(new Runnable() {
					   			public void run() {
					   				view.setImageDrawable(ziggy.getRandomFace());
					   			}
			            	});
				   timer.schedule(new UpdateTimeTask(act), TIMER_WAIT);

			   }else{
				   timer.cancel();
	               Intent intent = new Intent(SplashActivity.this,MenuActivity.class);
	               startActivity(intent);
 				   SplashActivity.this.finish();
			   }
		   }
		}
}
