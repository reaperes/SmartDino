package nhk.raon.smartdino.login;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.main.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CharMakeActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charmake);
	
        ImageView iv = (ImageView) findViewById(R.id.login_charmake_image_background);
        
        final Object obj = new Object();
        iv.setTag(obj);
        iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getTag() == obj) {
		    		Intent intent = new Intent().setClass(CharMakeActivity.this, MainActivity.class);
		    		startActivity(intent);
		    		finish();
				}
			}
		});        
	}
}
