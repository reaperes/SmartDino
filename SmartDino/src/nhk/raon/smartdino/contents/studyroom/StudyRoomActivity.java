package nhk.raon.smartdino.contents.studyroom;

import nhk.raon.smartdino.contents.studyroom.math.MathActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class StudyRoomActivity extends Activity {

	public static final int MEMORY = 1;
	public static final int KNOWLEDGE = 2;
	public static final int ENGLISH = 3;
	public static final int MATH = 4;

	private StudyRoomView studyRoomView;
	private boolean isMoving = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		studyRoomView = new StudyRoomView(this, StudyRoomActivity.this);

		setContentView(studyRoomView);
	}

	@Override
	public void onStart() {
		Log.e("NHK", "onStart");

		super.onStart();
	}

	@Override
	public void onResume() {
		Log.e("NHK", "onResume");

		super.onResume();
	}

	@Override
	public void onStop() {
		Log.e("NHK", "onStop");
		// studyRoomView.stop();

		super.onStop();
	}

	@Override
	public void onPause() {
		Log.e("NHK", "onPause");

		super.onPause();
	}

	// @Override
	// public boolean dispatchTouchEvent (MotionEvent ev) {
	// return super.dispatchTouchEvent(ev);
	// }

	public void moveActivity(int n) {
		switch (n) {
		case MEMORY:
		case KNOWLEDGE:
		case ENGLISH:
		case MATH:
			if (!isMoving) {
				Intent intent = new Intent().setClass(StudyRoomActivity.this, MathActivity.class);
				startActivity(intent);
				finish();
				return;
			}
			break;
		}
	}
}
