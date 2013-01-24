package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.contents.catchfruits.CatchFruitsActivity;
import nhk.raon.smartdino.contents.jungle.JungleActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends Activity {
	
    final Object obj1 = new Object();
    final Object obj2 = new Object();
    final Object obj3 = new Object();
    final Object obj4 = new Object();
	
	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getTag() == obj1) {
	    		Intent intent = new Intent().setClass(Main2Activity.this, CatchFruitsActivity.class);
	    		startActivity(intent);			
			} else if(v.getTag() == obj2) {
	    		Intent intent = new Intent().setClass(Main2Activity.this, JungleActivity.class);
	    		startActivity(intent);
			} else if(v.getTag() == obj3) {
//	    		Intent intent = new Intent().setClass(Main2Activity.this, JungleActivity.class);
//	    		startActivity(intent);
			} else if(v.getTag() == obj4) {
	    		Intent intent = new Intent().setClass(Main2Activity.this, MainActivity.class);
	    		startActivity(intent);
	    		finish();
			}
		}
	};	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
	
        Button button1 = (Button) findViewById(R.id.main_button1);
        Button button2 = (Button) findViewById(R.id.main_button2);
        Button button3 = (Button) findViewById(R.id.main_button3);
        Button button4 = (Button) findViewById(R.id.main_button4);
        
        button1.setTag(obj1);
        button1.setOnClickListener(listener);
        
        button2.setTag(obj2);
        button2.setOnClickListener(listener);
        
        button3.setTag(obj3);
        button3.setOnClickListener(listener);

        button4.setTag(obj4);
        button4.setOnClickListener(listener);
	}
}
