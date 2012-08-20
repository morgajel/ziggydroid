package com.morgajel.ziggyswift;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CueballActivity extends Activity implements OnClickListener,
		SensorEventListener {
	private ZiggyFace ziggy;
	private SensorManager sensorManager;

	private float xAccel;
	private float yAccel;
	private float zAccel;
	/* And here the previous ones */
	private float xPreviousAccel;
	private float yPreviousAccel;
	private float zPreviousAccel;

	/* Used to suppress the first shaking */
	private boolean firstUpdate = true;

	/* What acceleration difference would we assume as a rapid movement? */
	private final float shakeThreshold = 1.5f;

	/* Has a shaking motion been started (one direction) */
	private boolean shakeInitiated = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.cueball);
		Context context = this.getApplicationContext();
		ziggy = new ZiggyFace(context);
		View v = this.findViewById(R.id.cueballface);
		ImageView ziggyView = (ImageView) v;
		ziggyView.setOnClickListener(this);
		ziggyView.setImageDrawable(ziggy.getRandomFace());
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // (1)
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL); // (2)

	}

	@Override
	public void onClick(View arg0) {
	}

	public void onSensorChanged(SensorEvent se) {
		updateAccelParameters(se.values[0], se.values[1], se.values[2]); // (1)
		if ((!shakeInitiated) && isAccelerationChanged()) { // (2)
			shakeInitiated = true;
			//Shaking started, 
			// if eyes are still, ziggyView.setImageDrawable(ziggy.rollEonyes());
		} else if ((shakeInitiated) && isAccelerationChanged()) { // (3)
			//Stuff is still shaking
		} else if ((shakeInitiated) && (!isAccelerationChanged())) { // (4)
			shakeInitiated = false;
			executeShakeAction();

		}
	}

	/* Store the acceleration values given by the sensor */
	/*
	 * If the values of acceleration have changed on at least two axises, we are
	 * probably in a shake motion
	 */
	private boolean isAccelerationChanged() {
		float deltaX = Math.abs(xPreviousAccel - xAccel);
		float deltaY = Math.abs(yPreviousAccel - yAccel);
		float deltaZ = Math.abs(zPreviousAccel - zAccel);
		return (deltaX > shakeThreshold && deltaY > shakeThreshold)
				|| (deltaX > shakeThreshold && deltaZ > shakeThreshold)
				|| (deltaY > shakeThreshold && deltaZ > shakeThreshold);
	}

	private void executeShakeAction() {
		ImageView ziggyView = (ImageView) this.findViewById(R.id.cueballface);
		ziggyView.setImageDrawable(ziggy.getRandomFace());
		TextView text = (TextView) this.findViewById(R.id.cueballtext);
		text.setText(ziggy.response(R.array.cueballstrings));
	}

	private void updateAccelParameters(float xNewAccel, float yNewAccel,
			float zNewAccel) {
		/*
		 * we have to suppress the first change of acceleration, it results from
		 * first values being initialized with 0
		 */
		if (firstUpdate) {
			xPreviousAccel = xNewAccel;
			yPreviousAccel = yNewAccel;
			zPreviousAccel = zNewAccel;
			firstUpdate = false;
		} else {
			xPreviousAccel = xAccel;
			yPreviousAccel = yAccel;
			zPreviousAccel = zAccel;
		}
		xAccel = xNewAccel;
		yAccel = yNewAccel;
		zAccel = zNewAccel;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}