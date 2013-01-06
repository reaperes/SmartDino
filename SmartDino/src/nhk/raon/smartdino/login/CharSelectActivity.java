package nhk.raon.smartdino.login;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.main.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CharSelectActivity extends Activity implements View.OnClickListener {
	
	final Object obj1 = new Object();
	final Object obj2 = new Object();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charselect);
	
        ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_background);
        
        iv.setTag(obj1);
        iv.setOnClickListener(this);
        
        Button button = (Button) findViewById(R.id.charselect_button1);
        
        button.setTag(obj2);
        button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getTag() == obj1) {
    		Intent intent = new Intent().setClass(CharSelectActivity.this, MainActivity.class);
    		startActivity(intent);
    		finish();				
		} else if(v.getTag() == obj2) {
    		Intent intent = new Intent().setClass(CharSelectActivity.this, CharMakeActivity.class);
    		startActivity(intent);
    		finish();			
		}
	}
}
