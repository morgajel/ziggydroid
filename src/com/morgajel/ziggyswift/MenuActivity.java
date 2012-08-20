package com.morgajel.ziggyswift;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnItemClickListener, OnClickListener {
    /** Called when the activity is first created. */
	ZiggyFace ziggy;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ziggy = new ZiggyFace(getApplicationContext());
        setContentView(R.layout.main);
		ListView list = (ListView) this.findViewById(R.id.menuListView);
        
        list.setAdapter(
        		new ArrayAdapter<String>(this,
        				android.R.layout.simple_list_item_1, 
        				this.getResources().getStringArray(R.array.menu)
        				)
        		);
        list.setOnItemClickListener(this);
    	TextView greetings = (TextView) this.findViewById(R.id.textGreetings);
    	greetings.setText(ziggy.response(R.array.greetingstrings));
    	greetings.setOnClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int selectedId, long arg3) {
		TextView text = (TextView) arg1;
		Log.i("onItemClick","int "+selectedId);
		Resources res = this.getResources();
		
		if ( text.getText().equals(res.getString(R.string.menu_facts)) ){
    		Intent intent = new Intent(MenuActivity.this, FactsActivity.class);
			startActivity(intent);
		} else if ( text.getText().equals(res.getString(R.string.menu_wwzd)) ){
			Intent intent = new Intent(MenuActivity.this, WwzdActivity.class);
			startActivity(intent);
		} else if ( text.getText().equals(res.getString(R.string.menu_brawl)) ){
			Intent intent = new Intent(MenuActivity.this, BrawlActivity.class);
			startActivity(intent);
		} else if ( text.getText().equals(res.getString(R.string.menu_cueball)) ){
			Intent intent = new Intent(MenuActivity.this, CueballActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void onClick(View v) {
		TextView text = (TextView) v;
		Resources res = this.getResources();
		text.setText(ziggy.response(R.array.greetingstrings));	
	}

}