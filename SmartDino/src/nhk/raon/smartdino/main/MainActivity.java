package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
    final Object obj1 = new Object();
    final Object obj2 = new Object();
	
	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getTag() == obj1) {
	    		Intent intent = new Intent().setClass(MainActivity.this, StudyRoomActivity.class);
	    		startActivity(intent);
			} else if(v.getTag() == obj2) {
	    		Intent intent = new Intent().setClass(MainActivity.this, Main2Activity.class);
	    		startActivity(intent);
	    		finish();
			}
		}
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(this));
        
        setContentView(R.layout.activity_main);
	
        Button button1 = (Button) findViewById(R.id.main_button1);
        Button button2 = (Button) findViewById(R.id.main_button2);
        
        button1.setTag(obj1);
        button1.setOnClickListener(listener);
        
        button2.setTag(obj2);
        button2.setOnClickListener(listener);
        
        ImageView iv = (ImageView) findViewById(R.id.main_image_background);
        iv.setOnTouchListener(new View.OnTouchListener() {
        	
        	public boolean onTouch(View v, MotionEvent event) {
        		
        		if(event.getAction() == MotionEvent.ACTION_DOWN) {
        			switch(v.getId()) {
        			case R.id.main_image_background:
        				Log.e("NHK", "ACTION_DOWN");
        			}
        		}
        		
        		return true;
        	}
        });
	}
}
