package com.morgajel.ziggyswift;

import java.util.Random;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class WwzdActivity  extends Activity implements OnClickListener {
	private ZiggyFace ziggy;
	private Random rand=new Random();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wwzd);
        Context context= this.getApplicationContext();
        ziggy=new ZiggyFace(context);
        View v =this.findViewById(R.id.wwzdface);
        ImageView ziggyView= (ImageView) v;
        ziggyView.setOnClickListener(this);
        ziggyView.setImageDrawable(ziggy.getRandomFace());
    }
    
	@Override
	public void onClick(View arg0) {
    	ImageView ziggyView = (ImageView) this.findViewById(R.id.wwzdface);
    	ziggyView.setImageDrawable(ziggy.getRandomFace());
    	TextView text = (TextView) this.findViewById(R.id.wwzdtext);
    	text.setText(ziggy.response(R.array.wwzdstrings));
    	
	
	}
}