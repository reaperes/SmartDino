package nhk.raon.smartdino.main;

import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	
	private MainView mainView;
	private boolean isMoving=false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mainView = new MainView(this, MainActivity.this);

		setContentView(mainView);
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
		mainView.stop();
		
		super.onStop();
	}
	
	@Override
	public void onPause() {
		Log.e("NHK", "onPause");
		
		super.onPause();
	}
	
	@Override
	public boolean dispatchTouchEvent (MotionEvent ev) {
//		mainView
		
		super.dispatchTouchEvent(ev);
		return true;
	}
	
	public void moveActivity(int n) {
		switch(n) {
		case MainView.GO_CONTENTS_STUDYROOM:
			if(!isMoving) {
	    		Intent intent = new Intent().setClass(MainActivity.this, StudyRoomActivity.class);
	    		startActivity(intent);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
    		
		case MainView.GO_MAIN2:
			if(!isMoving) {
	    		Intent intent2 = new Intent().setClass(MainActivity.this, Main2Activity.class);
	    		startActivity(intent2);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
		}
	}
}
