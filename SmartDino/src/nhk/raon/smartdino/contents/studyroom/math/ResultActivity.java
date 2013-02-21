package nhk.raon.smartdino.contents.studyroom.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ResultActivity extends Activity {
	
	private int gameType;
	private int gameLevel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_result);

        Intent intent = getIntent();
        if (intent.hasExtra("gameType"))
        	gameType = intent.getIntExtra("gameType", 0);
        if (intent.hasExtra("gameLevel"))
        	gameLevel = intent.getIntExtra("gameLevel", 0);

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
	
	private void saveLevel() {
		boolean save=false;
		OutputStreamWriter osw = null;
		String FILE_NAME = "record00.txt";
		
		BufferedReader br;
		String str="0000", plus, minus, multiplication, division;
		
		try {
			br = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
			str = br.readLine();
        	br.close();
		} catch (Exception e) {
			Log.e("NHK", "read ERROR");
        }
		
		plus = String.valueOf(str.charAt(0));
		minus = String.valueOf(str.charAt(1));
		multiplication = String.valueOf(str.charAt(2));
		division = String.valueOf(str.charAt(3));
		
		switch(gameType) {
		case MathActivity.TYPE_PLUS:
			if(gameLevel != 3 && gameLevel==Integer.parseInt(plus)){
				plus = String.valueOf(gameLevel+1);
				save=true;
			}
			break;
		case MathActivity.TYPE_MINUS:
			if(gameLevel != 3 && gameLevel==Integer.parseInt(minus)){
				minus = String.valueOf(gameLevel+1);
				save=true;
			}
			break;
		case MathActivity.TYPE_MULTIPLICATION:
			if(gameLevel != 3 && gameLevel==Integer.parseInt(multiplication)){
				multiplication = String.valueOf(gameLevel+1);
				save=true;
			}
			break;
		case MathActivity.TYPE_DIVISION:
			if(gameLevel != 3 && gameLevel==Integer.parseInt(division)){
				division = String.valueOf(gameLevel+1);
				save=true;
			}
			break;
		}
		
		if(save){
			try {
				osw = new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE));
				osw.write(plus+minus+multiplication+division);
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
}
