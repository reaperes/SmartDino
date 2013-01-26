package nhk.raon.smartdino.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.main.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CharSelectActivity extends Activity implements View.OnClickListener {
	
	private final String strSaveFile0 = "data00.txt";
	private final String strSaveFile1 = "data01.txt";
	private final String strSaveFile2 = "data02.txt";
	
	private final Object obj0 = new Object();
	private final Object obj1 = new Object();
	private final Object obj2 = new Object();
	private final Object obj3 = new Object();
	
	private boolean isSaveFile0 = false;
	private boolean isSaveFile1 = false;
	private boolean isSaveFile2 = false;
	
	private String name0;
	private String name1;
	private String name2;
	
	private boolean isDelete = false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charselect);
        
        ImageView iv0 = (ImageView) findViewById(R.id.login_charselect_image_char1);
        ImageView iv1 = (ImageView) findViewById(R.id.login_charselect_image_char2);
        ImageView iv2 = (ImageView) findViewById(R.id.login_charselect_image_char3);
        
        iv0.setTag(obj0);
        iv0.setOnClickListener(this);

        iv1.setTag(obj1);
        iv1.setOnClickListener(this);

        iv2.setTag(obj2);
        iv2.setOnClickListener(this);

        Button button = (Button) findViewById(R.id.charselect_button1);
        
        button.setTag(obj3);
        button.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
        loadData();
        loadImage();
	}
	
	@Override
	public void onClick(View v) {
		if(v.getTag() == obj0) {
			if(isDelete) {
				File file = new File("/data/data/nhk.raon.smartdino/files/" + strSaveFile0);
				if(file.exists()) {
					file.delete();
					
					loadData();
					loadImage();
					
					isDelete = false;
				}
			} else {
				if(isSaveFile0) {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, MainActivity.class);
		    		startActivity(intent);
		    		finish();
				} else {
					Intent intent = new Intent(this, CharMakeActivity.class);
		    		intent.putExtra("fileName", strSaveFile0);
		    		startActivity(intent);
		    		finish();
				}
			}
		} else if(v.getTag() == obj1) {
			if(isDelete) {
				File file = new File("/data/data/nhk.raon.smartdino/files/" + strSaveFile1);
				if(file.exists()) {
					file.delete();
					
					loadData();
					loadImage();
					
					isDelete = false;
				}
			} else {			
				if(isSaveFile1) {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, MainActivity.class);
		    		startActivity(intent);
		    		finish();
				} else {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, CharMakeActivity.class);
		    		intent.putExtra("fileName", strSaveFile1);
		    		startActivity(intent);
		    		finish();
				}
			}
		} else if(v.getTag() == obj2) {
			if(isDelete) {
				File file = new File("/data/data/nhk.raon.smartdino/files/" + strSaveFile2);
				if(file.exists()) {
					file.delete();
					
					loadData();
					loadImage();
					
					isDelete = false;
				}
			} else {
				if(isSaveFile2) {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, MainActivity.class);
		    		startActivity(intent);
		    		finish();
				} else {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, CharMakeActivity.class);
		    		intent.putExtra("fileName", strSaveFile2);
		    		startActivity(intent);
		    		finish();
				}
			}
		} else if(v.getTag() == obj3) {
			Toast.makeText(this, "어떤 공룡을 삭제하겠습니까?", Toast.LENGTH_SHORT).show();
			isDelete = true;
		}
	}
	
	private void loadData() {
        BufferedReader br = null;
        
        StringBuffer sb = new StringBuffer();
        
        try {
        	br = new BufferedReader(new InputStreamReader(openFileInput(strSaveFile0)));
        	String str = null;
        	
        	while((str = br.readLine()) != null) {
        		sb.append(str+"\n");
        		name0 = str;
        	}
        	isSaveFile0 = true;
        	br.close();
        } catch (Exception e) {
        	isSaveFile0 = false;
        	Log.e("NHK", "file: " + strSaveFile0 + " error");
        }
        
        try {
        	br = new BufferedReader(new InputStreamReader(openFileInput(strSaveFile1)));
        	String str = null;
        	
        	while((str = br.readLine()) != null) {
        		sb.append(str+"\n");
        		name1 = str;
        	}
        	isSaveFile1 = true;
        	br.close();
        } catch (Exception e) {
        	isSaveFile1 = false;
        	Log.e("NHK", "file: " + strSaveFile1 + " error");
        }
        
        try {
        	br = new BufferedReader(new InputStreamReader(openFileInput(strSaveFile2)));
        	String str = null;
        	
        	while((str = br.readLine()) != null) {
        		sb.append(str+"\n");
        		name2 = str;
        	}
        	isSaveFile2 = true;
        	br.close();
        } catch (Exception e) {
        	isSaveFile2 = false;
        	Log.e("NHK", "file: " + strSaveFile2 + " error");
        }
	}
	
	private void loadImage() {
		if(isSaveFile0) {
			ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_char1);
			iv.setImageResource(R.drawable.dino);
			
			TextView tv = (TextView) findViewById(R.id.login_charselect_text_char1);
			tv.setText(name0);
		} else {
			ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_char1);
			iv.setImageResource(R.drawable.image_char_egg);
			
			TextView tv = (TextView) findViewById(R.id.login_charselect_text_char1);
			tv.setText("새로 시작하기");
		}
		if(isSaveFile1) {
			ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_char2);
			iv.setImageResource(R.drawable.dino);
			
			TextView tv = (TextView) findViewById(R.id.login_charselect_text_char2);
			tv.setText(name1);
		} else {
			ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_char2);
			iv.setImageResource(R.drawable.image_char_egg);
			
			TextView tv = (TextView) findViewById(R.id.login_charselect_text_char2);
			tv.setText("새로 시작하기");
		}
		if(isSaveFile2) {
			ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_char3);
			iv.setImageResource(R.drawable.dino);
			
			TextView tv = (TextView) findViewById(R.id.login_charselect_text_char3);
			tv.setText(name2);
		} else {
			ImageView iv = (ImageView) findViewById(R.id.login_charselect_image_char3);
			iv.setImageResource(R.drawable.image_char_egg);
			
			TextView tv = (TextView) findViewById(R.id.login_charselect_text_char3);
			tv.setText("새로 시작하기");
		}
	}
}
