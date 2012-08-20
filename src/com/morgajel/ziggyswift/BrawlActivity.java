package com.morgajel.ziggyswift;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BrawlActivity extends Activity implements OnClickListener {
	private ZiggyFace ziggy;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brawl);
        Context context= this.getApplicationContext();
        ziggy=new ZiggyFace(context);
        View v =this.findViewById(R.id.brawlface);
        ImageView ziggyView= (ImageView) v;
        ziggyView.setOnClickListener(this);
        ziggyView.setImageDrawable(ziggy.getRandomFace());
    }
    
	@Override
	public void onClick(View arg0) {
    	ImageView ziggyView = (ImageView) this.findViewById(R.id.brawlface);
    	ziggyView.setImageDrawable(ziggy.getRandomFace());
    	TextView text = (TextView) this.findViewById(R.id.brawltext);
    	text.setText(ziggy.response(R.array.brawlstrings));
	}
     
} 