package nhk.raon.smartdino.contents.studyroom.math;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

public class ReadyActivity extends Activity {
	
	private int gameType;
	private int gameLevel;
	
	private AnimationDrawable readyAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_ready);

        Intent intent = getIntent();
        if (intent.hasExtra("gameType"))
        	gameType = intent.getIntExtra("gameType", 0);
        if (intent.hasExtra("gameLevel"))
        	gameLevel = intent.getIntExtra("gameLevel", 0);

		ImageView imageView = (ImageView) findViewById(R.id.image_contents_studyroom_math_ready);
		imageView.setBackgroundResource(R.drawable.animation_contents_studyroom_math_ready);
		readyAnimation = (AnimationDrawable) imageView.getBackground();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		if(hasFocus==true) {
			readyAnimation.start();
			
			int timeBetweenChecks = 300*15 + 300;
			Handler h = new Handler();
	        h.postDelayed(new Runnable(){
	            public void run(){
	            	while(true) {
		                if (readyAnimation.getCurrent() == readyAnimation.getFrame(readyAnimation.getNumberOfFrames() - 1)) {
		                	goReady2Activity();
		                    break;
		                }
	            	}
	            }
	        }, timeBetweenChecks);
		}
	}
	
	private void goReady2Activity() {
		Intent intent = new Intent(ReadyActivity.this, Ready2Activity.class);
		intent.putExtra("gameType", gameType);
		intent.putExtra("gameLevel", gameLevel);
		startActivity(intent);
		finish();
	}
}
