package nhk.raon.smartdino.contents.studyroom;

import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class StudyRoomActivity extends Activity {
	
	private StudyRoomView studyRoomView;
	private boolean isMoving=false;
	
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
//		studyRoomView.stop();
		
		super.onStop();
	}
	
	@Override
	public void onPause() {
		Log.e("NHK", "onPause");
		
		super.onPause();
	}
	
//	@Override
//	public boolean dispatchTouchEvent (MotionEvent ev) {
//		return super.dispatchTouchEvent(ev);
//	}
	
	public void moveActivity(int n) {
//		switch(n) {
//		case StudyRoomView.GO_CONTENTS_STUDYROOM:
//			if(!isMoving) {
//	    		Intent intent = new Intent().setClass(StudyRoomActivity.this, StudyRoomActivity.class);
//	    		startActivity(intent);
//	    		isMoving = true;
//	    		finish();
//	    		return ;
//			}
//    		
//		case StudyRoomView.GO_MAIN2:
//			if(!isMoving) {
//	    		Intent intent2 = new Intent().setClass(StudyRoomActivity.this, StudyRoom2Activity.class);
//	    		startActivity(intent2);
//	    		isMoving = true;
//	    		finish();
//	    		return ;
//			}
//		}
	}
}
