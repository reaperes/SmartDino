package nhk.raon.smartdino.login;

import java.io.OutputStreamWriter;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class CharMakeActivity extends Activity {
	
	private String FILE_NAME = "1";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charmake);
        
        Intent intent = getIntent();
        if (intent.hasExtra("fileName")) {
        	FILE_NAME = intent.getStringExtra("fileName");
        }
        
        ImageView button = (ImageView) findViewById(R.id.login_charmake_imagebutton_ok);
        
        final Object obj = new Object();
        button.setTag(obj);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getTag() == obj) {
					OutputStreamWriter osw = null;
					
					try {
						osw = new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE));
						
						EditText editText = (EditText)findViewById(R.id.login_charmake_edittext);
						osw.write(editText.getText().toString());
					} catch (Exception e) {
						Log.e("NHK", "write file Error");
					} finally {
						try {
							osw.close();
						} catch (Exception e) {
						}
					}
					
		    		Intent intent = new Intent().setClass(CharMakeActivity.this, CharSelectActivity.class);
		    		startActivity(intent);
		    		finish();
				}
			}
		});        
	}
}
