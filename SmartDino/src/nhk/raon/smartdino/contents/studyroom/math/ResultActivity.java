package nhk.raon.smartdino.contents.studyroom.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.Record;
import nhk.raon.smartdino.SmartDino;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ResultActivity extends Activity {
	
	private int gameType;
	private int gameLevel;
	private int correctAnswerCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_result);

        Intent intent = getIntent();
        if (intent.hasExtra("gameType"))
        	gameType = intent.getIntExtra("gameType", 0);
        if (intent.hasExtra("gameLevel"))
        	gameLevel = intent.getIntExtra("gameLevel", 0);
        if (intent.hasExtra("correctAnswerCount"))
        	correctAnswerCount = intent.getIntExtra("correctAnswerCount", 0);

        initDino();
        initStars();
        saveLevel();
        
        ImageView home = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_home);
        ImageView replay = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_replay);
        ImageView next = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_next);
        
        home.setOnClickListener(listener);
        replay.setOnClickListener(listener);
        next.setOnClickListener(listener);
	}
	
	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent;
			
			switch(v.getId()) {
			case R.id.image_contents_studyroom_math_result_home:
				intent = new Intent(ResultActivity.this, SelectLevelActivity.class);
				intent.putExtra("gameType", gameType);
				startActivity(intent);
				finish();
				break;
				
			case R.id.image_contents_studyroom_math_result_replay:
				intent = new Intent(ResultActivity.this, QuizActivity.class);
				intent.putExtra("gameType", gameType);
				intent.putExtra("gameLevel", gameLevel);
				startActivity(intent);
				finish();
				break;

			case R.id.image_contents_studyroom_math_result_next:
				if(gameLevel == 3)
					return ;
				
				gameLevel++;
				intent = new Intent(ResultActivity.this, QuizActivity.class);
				intent.putExtra("gameType", gameType);
				intent.putExtra("gameLevel", gameLevel);
				startActivity(intent);
				finish();
				break;
			}
		}
	};
	
	private void initDino() {
		ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_dino);
		switch(correctAnswerCount) {
		case 0:
		case 1:
		case 2:
			iv.setImageResource(R.drawable.image_contents_studyroom_math_result_bad);
			break;
		case 3:
			iv.setImageResource(R.drawable.image_contents_studyroom_math_result_notbad);
			break;
		case 4:
		case 5:			
			iv.setImageResource(R.drawable.image_contents_studyroom_math_result_good);
			break;
		}
	}
	
	private void initStars() {
		ImageView iv0 = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_star0);
		ImageView iv1 = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_star1);
		ImageView iv2 = (ImageView) findViewById(R.id.image_contents_studyroom_math_result_star2);
		switch(correctAnswerCount) {
		case 0:
		case 1:
		case 2:
			iv0.setVisibility(ImageView.INVISIBLE);
			iv1.setVisibility(ImageView.INVISIBLE);
			iv2.setVisibility(ImageView.VISIBLE);
			break;
		case 3:
			iv0.setVisibility(ImageView.INVISIBLE);
			iv1.setVisibility(ImageView.VISIBLE);
			iv2.setVisibility(ImageView.VISIBLE);
			break;
		case 4:
		case 5:			
			iv0.setVisibility(ImageView.VISIBLE);
			iv1.setVisibility(ImageView.VISIBLE);
			iv2.setVisibility(ImageView.VISIBLE);
			break;
		}
	}	
	
	private void saveLevel() {
		int highLevel;
		switch(gameType) {
		case MathActivity.TYPE_PLUS:
			highLevel = new Record(SmartDino.Record_Number).getRecord(this, Record.MATH_RECORD, 0);
			if(gameLevel != 3 && gameLevel==highLevel)
				new Record(SmartDino.Record_Number).setRecord(this, Record.MATH_RECORD, 0, gameLevel+1);
			break;
		case MathActivity.TYPE_MINUS:
			highLevel = new Record(SmartDino.Record_Number).getRecord(this, Record.MATH_RECORD, 1);
			if(gameLevel != 3 && gameLevel==highLevel)
				new Record(SmartDino.Record_Number).setRecord(this, Record.MATH_RECORD, 1, gameLevel+1);
			break;
		case MathActivity.TYPE_MULTIPLICATION:
			highLevel = new Record(SmartDino.Record_Number).getRecord(this, Record.MATH_RECORD, 2);
			if(gameLevel != 3 && gameLevel==highLevel)
				new Record(SmartDino.Record_Number).setRecord(this, Record.MATH_RECORD, 2, gameLevel+1);
			break;
		case MathActivity.TYPE_DIVISION:
			highLevel = new Record(SmartDino.Record_Number).getRecord(this, Record.MATH_RECORD, 2);
			if(gameLevel != 3 && gameLevel==highLevel)
				new Record(SmartDino.Record_Number).setRecord(this, Record.MATH_RECORD, 3, gameLevel+1);
			break;
		}
	}
}
