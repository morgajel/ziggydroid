package com.morgajel.ziggyswift;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import java.lang.Math;

public class ZiggyFace {

	public static int LAYER_BASE = 0;
	public static int LAYER_EYEBASE = 1;
	public static int LAYER_PUPIL = 2;
	public static int LAYER_EYELIDS = 3;
	public static int LAYER_TEARS = 4;
	public static int LAYER_BROW = 5;
	public static int LAYER_MOUTH = 6;
	public static int MAX_LAYERS = 7;
	public static int[] EYELIDS = { R.drawable.eyes_centersquint,
			R.drawable.eyes_closed, R.drawable.eyes_inslant,
			R.drawable.eyes_lowerlid, R.drawable.eyes_lowersquint,
			R.drawable.eyes_outslant, R.drawable.eyes_upperlid,
			R.drawable.eyes_uppersquint };
	public static int[] TEARS = { R.drawable.tears_brimmed,
			R.drawable.tears_single, R.drawable.tears_well };
	public static int[] BROW = { R.drawable.brow_angry,
			R.drawable.brow_enraged, R.drawable.brow_confused,
			R.drawable.brow_skeptical, R.drawable.brow_sly,
			R.drawable.brow_straight, R.drawable.brow_tough };
	public static int[] MOUTH = { R.drawable.mouth_estatic,
			R.drawable.mouth_frown, R.drawable.mouth_grin,
			R.drawable.mouth_nada, R.drawable.mouth_oh, R.drawable.mouth_smile,
			R.drawable.mouth_smirk, R.drawable.mouth_snob,
			R.drawable.mouth_somuchwin, R.drawable.mouth_static,
			R.drawable.mouth_talking, R.drawable.mouth_uh,
			R.drawable.mouth_unamused, R.drawable.mouth_unsure };

	private Context context;
	private LayerDrawable face;
	private Random rand = new Random();

	public ZiggyFace(Context pContext) {
		context = pContext;
		Drawable[] facelayers = new Drawable[MAX_LAYERS];
		for (int i = 0; i < MAX_LAYERS; i++) {
			facelayers[i] = context.getResources().getDrawable(
					android.R.color.transparent);
		}
		face = new LayerDrawable(facelayers);
		for (int i = 0; i < MAX_LAYERS; i++) {
			face.setId(i, i);
		}
	}
	public String response(int responsetype){
    	String[] randomtexts = context.getResources().getStringArray(responsetype);
    	return randomtexts[rand.nextInt(randomtexts.length)];
		
	}

//	stupid face doesn't work
//	public LayerDrawable getRandomFace() {
//		Resources r = context.getResources();
//
//		face.setDrawableByLayerId(face.getId(LAYER_BASE), r.getDrawable(R.drawable.base));
//		face.setDrawableByLayerId(face.getId(LAYER_EYEBASE),
//				r.getDrawable(R.drawable.eyes_base));
//		face.setDrawableByLayerId(face.getId(LAYER_PUPIL),
//				r.getDrawable(R.drawable.eyes_pupils));
//		face.setDrawableByLayerId(face.getId(LAYER_EYELIDS), randomlayer(3, EYELIDS));
//		face.setDrawableByLayerId(face.getId(LAYER_TEARS), randomlayer(6, TEARS));
//
//		face.setDrawableByLayerId(face.getId(LAYER_BROW),
//				r.getDrawable(BROW[rand.nextInt(BROW.length)]));
//		face.setDrawableByLayerId(face.getId(LAYER_MOUTH),
//				r.getDrawable(MOUTH[rand.nextInt(MOUTH.length)]));
//		Rect bounds = movepupils(face.getDrawable(LAYER_PUPIL));
//		face.setLayerInset(LAYER_PUPIL, bounds.left, bounds.top, bounds.right,
//				bounds.bottom);
//		
//		Log.i("ziggyface", face.toString());
//		return face;
//
//	}

	
	
	
//	This is functional
	public LayerDrawable getRandomFace() {
		Resources r = context.getResources();
		Drawable[] facelayers = new Drawable[MAX_LAYERS];
		
		facelayers[LAYER_BASE]= 	r.getDrawable(R.drawable.base);
		facelayers[LAYER_EYEBASE]=	r.getDrawable(R.drawable.eyes_base);
		facelayers[LAYER_PUPIL]=	r.getDrawable(R.drawable.eyes_pupils);

		facelayers[LAYER_EYELIDS]= 	randomlayer(3, EYELIDS);
		facelayers[LAYER_TEARS]= 	randomlayer(6, TEARS);
		facelayers[LAYER_BROW]=		r.getDrawable(BROW[rand.nextInt(BROW.length)]);
		facelayers[LAYER_MOUTH]=	r.getDrawable(MOUTH[rand.nextInt(MOUTH.length)]);
		face = new LayerDrawable(facelayers);
		
		Rect bounds = movepupils(face.getDrawable(LAYER_PUPIL));
		face.setLayerInset(LAYER_PUPIL, bounds.left, bounds.top, bounds.right,
				bounds.bottom);


		Log.i("ziggyface", face.toString());
		return face;

	}
	
	
	public void rollEyes(){
		//so what needs to happen here?
		//I think an animation onthe pupil layer to transition them
		face.getDrawable(LAYER_PUPIL);
		//create animation object
		//apply to pupils
		//start animation
		
	}
		
	// How complicated could it possibly be?
	public Rect movepupils(Drawable pupils) {
		Resources r = context.getResources();
		// get my image size and pupil size
		int imagewidth = r.getDrawable(R.drawable.eyes_base).getMinimumWidth();
		int imageheight = r.getDrawable(R.drawable.eyes_base)
				.getMinimumHeight();
		int pupilwidth = r.getDrawable(R.drawable.eyes_pupils)
				.getMinimumWidth(); // 120
		int pupilheight = r.getDrawable(R.drawable.eyes_pupils)
				.getMinimumHeight();// 50
		// will center the eyes;
		int pupilwidthoffset = 50;
		int pupilheightoffset = 150;

		// width and height of the eyesocket
		int socketwidthoffset = 40;
		int socketheightoffset = 52;

		// random movement within the socket
		int pupilwidthmove = rand.nextInt(socketwidthoffset)
				- socketwidthoffset / 2;
		int pupilheightmove = rand.nextInt(socketheightoffset)
				- socketheightoffset / 2;
		// but the socket is rounded, so we need to keep the pupil within the
		// socket
		// if the pupil moves to the top of the eye, no problem, but if the
		// pupil tries to move to
		// the left, we need to lower the height as the pupil moves further
		// left.
		// likewise, if the pupil moves to the left, we need to bring it towards
		// the center as the
		// pupil moves up.
		float percentheightmoved = pupilheightmove * 100 / socketheightoffset
				* 2;
		float percentwidthmoved = pupilwidthmove * 100 / socketwidthoffset * 2;
		// adjust width by height, height by width
		float widthadjust = Math.abs(percentheightmoved / 100 / 2);
		float heightadjust = Math.abs(percentwidthmoved / 100 / 2);
		// use all the values above to move our pupils around and have them
		// right-sized.
		Rect bounds = new Rect(pupilwidthoffset
				+ new Float(pupilwidthmove * widthadjust).intValue(),
				pupilheightoffset
						+ new Float(pupilheightmove * heightadjust).intValue(),
				imagewidth - pupilwidth - pupilwidthoffset
						- new Float(pupilwidthmove * widthadjust).intValue(),
				imageheight - pupilheight - pupilheightoffset
						- new Float(pupilheightmove * heightadjust).intValue());
		return bounds;
	}

	public Drawable randomlayer(int modulus, int[] feature) {
		int length = feature.length;
		int newplace = rand.nextInt(length);
		Resources r = context.getResources();
		if (rand.nextInt(modulus) == 1) {
			return r.getDrawable(feature[newplace]);
		} else {
			return r.getDrawable(android.R.color.transparent);
		}
	}


}