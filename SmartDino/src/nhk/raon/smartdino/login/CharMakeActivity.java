package nhk.raon.smartdino.login;

import java.io.OutputStreamWriter;
import java.util.Random;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.Record;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class CharMakeActivity extends Activity {
	
	private String FILE_NAME;
	private int dataNumber;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charmake);
        
        Intent intent = getIntent();
        if (intent.hasExtra("fileName")) {
        	FILE_NAME = intent.getStringExtra("fileName");
        	dataNumber = intent.getIntExtra("dataNumber", 10);
        	
        	if(dataNumber == 10) {
	    		Intent intent2 = new Intent().setClass(CharMakeActivity.this, CharSelectActivity.class);
	    		startActivity(intent2);
	    		finish();
        	}
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
						
						osw.write(String.valueOf(randomDino()));
						Record record = new Record(dataNumber);
						record.initRecord(CharMakeActivity.this);
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
	
	public int randomDino() {
		// it returns a random integer between 0 (inclusive) and 4 (exclusive). ex) 0, 1, 2, 3
		return new Random().nextInt(4-0)+0;
	}
}
