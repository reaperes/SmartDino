package nhk.raon.smartdino.contents.studyroom.math;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectLevelActivity extends Activity {
	
	private int gameType;
	private int ableLevel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_level);
		
        Intent intent = getIntent();
        if (intent.hasExtra("gameType")) {
        	gameType = intent.getIntExtra("gameType", 0);
        }

        initDataFile();
        initLayout();
        initLevel();
	}
	
	private void initDataFile() {
		OutputStreamWriter osw = null;
		String FILE_NAME = "record00.txt";
		
		try {
			openFileInput(FILE_NAME);
		} catch(FileNotFoundException fe) {
			Log.e("NHK", "no data file");
			try {
				osw = new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE));
				osw.write("0000");
			} catch (Exception e) {
				Log.e("NHK", "write file Error");
			} finally {
				try {
					osw.close();
				} catch (Exception e) {
				}
			}			
		}
	}
	
	private int readAbleLevel() {
		BufferedReader br;
//		StringBuffer sb = new StringBuffer();
		String str;
		String fileName = "record00.txt";
		
		try {
			br = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
			str = br.readLine();
//        	while((str = br.readLine()) != null) {
//        		sb.append(str+"\n");
//        	}
        	br.close();
		} catch (Exception e) {
			Log.e("NHK", "read level ERROR");
			str = "0000";
        }
		
		char c=0;
		switch(gameType) {
		case MathActivity.TYPE_PLUS:
			c = str.charAt(0);
			break;
		case MathActivity.TYPE_MINUS:
			c = str.charAt(1);
			break;
		case MathActivity.TYPE_MULTIPLICATION:
			c = str.charAt(2);
			break;
		case MathActivity.TYPE_DIVISION:
			c = str.charAt(3);
			break;
		}
		str = String.valueOf(c);
		return Integer.parseInt(str);
	}
	
	private void initLayout() {
		TextView tv = (TextView) findViewById(R.id.text_contents_studyroom_math_level_title);
		ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_level_type);
		
		switch(gameType) {
		// 0 is intent error
		case 0:
			Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
		case MathActivity.TYPE_PLUS:
			tv.setText("더하기");
			iv.setImageResource(R.drawable.item_contents_studyroom_math_main_plus);
			break;

		case MathActivity.TYPE_MINUS:
			tv.setText("빼기");
			iv.setImageResource(R.drawable.item_contents_studyroom_math_main_minus);
			break;

		case MathActivity.TYPE_MULTIPLICATION:
			tv.setText("곱하기");
			iv.setImageResource(R.drawable.item_contents_studyroom_math_main_multiplication);
			break;

		case MathActivity.TYPE_DIVISION:
			tv.setText("나누기");
			iv.setImageResource(R.drawable.item_contents_studyroom_math_main_division);
			break;
		}
	}
	
	private void initLevel() {
		ableLevel = readAbleLevel();
		ImageView iv;

		switch(ableLevel){
		case 3:
			iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_level3);
			iv.setOnClickListener(listener);
			
		case 2:
			iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_level2);
			iv.setOnClickListener(listener);
			
		case 1:
			iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_level1);
			iv.setOnClickListener(listener);
			
		case 0:
			iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_level0);
			iv.setOnClickListener(listener);
			break;
		}
	}
	
	private void goNextActivity(int gameLevel) {
		Intent intent = new Intent(SelectLevelActivity.this, ReadyActivity.class);
		intent.putExtra("gameType", gameType);
		intent.putExtra("gameLevel", gameLevel);
		startActivity(intent);
		finish();
	}
	
	private ImageView.OnClickListener listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.image_contents_studyroom_math_level0:
				goNextActivity(0);
				break;
			case R.id.image_contents_studyroom_math_level1:
				goNextActivity(1);
				break;
			case R.id.image_contents_studyroom_math_level2:
				goNextActivity(2);
				break;
			case R.id.image_contents_studyroom_math_level3:
				goNextActivity(3);
				break;
			}
		}
	};
}
