package nhk.raon.smartdino.logo;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.login.CharSelectActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class LogoActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        
        ImageView iv = (ImageView) findViewById(R.id.logo_image_background);
        
        final Object obj = new Object();
        iv.setTag(obj);
        iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getTag() == obj) {
		    		Intent intent = new Intent().setClass(LogoActivity.this, CharSelectActivity.class);
		    		startActivity(intent);
		    		finish();
				}
			}
		});
        
        
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background_main, opts);
         
        Log.d("NHK", "image width = " + opts.outWidth 
                        + ", height = " + opts.outHeight + ", mime type = " + opts.outMimeType);
	}
}
