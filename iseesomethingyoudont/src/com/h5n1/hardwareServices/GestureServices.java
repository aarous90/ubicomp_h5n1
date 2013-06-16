package com.h5n1.hardwareServices;

import java.util.Timer;
import java.util.TimerTask;

import com.ubicomp.iseesomethingyoudont.EventToSpeechSynthesis;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class GestureServices extends GestureDetector.SimpleOnGestureListener {
	private static final String DEBUG_TAG = "Gestures";
	private TextView gestureText;
	private HapticalFeedbackServices vibrator;
	private Activity activity;
	private EventToSpeechSynthesis ttsengine;

	private long lastTapTime = 0;
	private int taps = 0;
	private Timer timer;
	private TimerTask task;

	public GestureServices(TextView gestureText,
			HapticalFeedbackServices vibrator,
			EventToSpeechSynthesis eventToSpeechSynthesis, Activity activity) {

		this.vibrator = vibrator;
		this.gestureText = gestureText;
		this.activity = activity;
		ttsengine = eventToSpeechSynthesis;
		timer = new Timer();
	}

	private TimerTask createTask() {
		timer.purge();
		return new TimerTask() {

			@Override
			public void run() {
				cancel();
				onMultiTap(taps);

			}
		};
	}

	@Override
	public boolean onDown(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDown: " + event.toString());
		gestureText.setText("Down".toCharArray(), 0, "Down".length());
		// vibrator.vibrateSpecificTime(100);
		return true;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
			float velocityX, float velocityY) {
		Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
		gestureText.setText("Fling".toCharArray(), 0, "Fling".length());
		// vibrator.vibrateSpecificTime(100);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
		gestureText.setText("LongPress".toCharArray(), 0, "LongPress".length());
		vibrator.vibrateSpecificTime(500);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
		gestureText.setText("Scroll".toCharArray(), 0, "Scroll".length());
		// vibrator.vibrateSpecificTime(100);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
		gestureText.setText("ShowPress".toCharArray(), 0, "ShowPress".length());
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
//		gestureText.setText("SingleTapUp".toCharArray(), 0,
//				"SingleTapUp".length());
		checkTap();
		// vibrator.vibrateSpecificTime(100);
		return true;
	}

	private void checkTap() {
		long currentTapTime = System.currentTimeMillis();

		long diff = currentTapTime - lastTapTime;

		if (diff < 800) {
			taps++;
			if (task == null)
				timer.schedule(task = createTask(), 1000);
			else {
				
				synchronized (task) {
					task.cancel();
				}
			}
		} else {

		}
		System.out.println("Taps: " + taps);
		lastTapTime = currentTapTime;
	}

	private void onMultiTap(int counter) {
		System.out.println("MultiTaps: " + counter);
		this.task = null;
		this.taps = 0;
	}

	public boolean onDoubleTap(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
		gestureText.setText("DoubleTap".toCharArray(), 0, "DoubleTap".length());
		// vibrator.vibrateSpecificTime(100);
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
		gestureText.setText("DoubleTapEvent".toCharArray(), 0,
				"DoubleTapEvent".length());
		vibrator.vibrateSpecificTime(100);
		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent event) {
		Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
		gestureText.setText("SingleTapConfirmed".toCharArray(), 0,
				"SingleTapConfirmed".length());

		ttsengine.stopSpeaking();
		ttsengine.speakTest("Ein Druck");


		
		return true;
	}

	// Anzeige nur zu DEBUG ZWECKEN!!
	private void showToast(String text){
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(activity.getApplicationContext(), text, duration);
		toast.show();
	}
	
}
