package nhk.raon.smartdino.contents.studyroom.math;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.Record;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;
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

        initLayout();
        initLevel();
        
        ImageView imageHomeButton = (ImageView) findViewById(R.id.image_contents_studyroom_math_level_returnbutton);
        imageHomeButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
    			Intent intent = new Intent(SelectLevelActivity.this, MathActivity.class);
    			startActivity(intent);
    			finish();
        	}
        });
	}
	
	private int readAbleLevel() {
		int point = 0;
		switch(gameType) {
		case MathActivity.TYPE_PLUS:
			point = 0;
			break;
		case MathActivity.TYPE_MINUS:
			point = 1;
			break;
		case MathActivity.TYPE_MULTIPLICATION:
			point = 2;
			break;
		case MathActivity.TYPE_DIVISION:
			point = 3;
			break;
		}
		
		Record record = new Record(SmartDino.Record_Number);
		int level=3;
		try {
			level = record.getRecord(this, Record.MATH_RECORD, point);
		} catch (Exception e) {
			Log.e("NHK_SelectLevelActivity", "failed to get record");
		}
		return level;
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
