package nhk.raon.smartdino.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private MainView mainView;
	
//    final Object obj1 = new Object();
//    final Object obj2 = new Object();
	
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent e) {
//		
//		super.dispatchTouchEvent(e);
//		return true;
//	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mainView = new MainView(this, MainActivity.this);
//        touchableArea = new ImageView(this);
//        touchableArea.setImageResource(R.drawable.background_touchable_main);
//        touchableArea.setId(R.id.main_background_touchable);
//        touchableArea.setOnClickListener(listener);
//
//		frameLayout = new FrameLayout(this);
////      FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//		frameLayout.addView(touchableArea);
//		frameLayout.addView(mainView);

		setContentView(mainView);
        
//        setContentView(R.layout.activity_main);
	
//        Button button1 = (Button) findViewById(R.id.main_button1);
//        Button button2 = (Button) findViewById(R.id.main_button2);
//        
//        button1.setTag(obj1);
//        button1.setOnClickListener(listener);
//        
//        button2.setTag(obj2);
//        button2.setOnClickListener(listener);
//        
//        ImageView iv = (ImageView) findViewById(R.id.main_image_background);
//        iv.setOnTouchListener(new View.OnTouchListener() {
//        	
//        	public boolean onTouch(View v, MotionEvent event) {
//        		
//        		if(event.getAction() == MotionEvent.ACTION_DOWN) {
//        			switch(v.getId()) {
//        			case R.id.main_image_background:
//        				Log.e("NHK", "ACTION_DOWN");
//        			}
//        		}
//        		
//        		return true;
//        	}
//        });
	}
	
	@Override
	public void onStop() {
		mainView.stop();
		
		super.onStop();
	}
}
