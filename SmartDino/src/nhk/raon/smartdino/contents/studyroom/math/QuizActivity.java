package nhk.raon.smartdino.contents.studyroom.math;

import java.util.Random;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends Activity {
	private int gameType;
	private int gameLevel;
	private int gameState;		// 0: pre-answer	1: after-answer
	
	private int firstNumber;
	private int secondNumber;
	private int answer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_quiz);

        Intent intent = getIntent();
        if (intent.hasExtra("gameType"))
        	gameType = intent.getIntExtra("gameType", 0);
        if (intent.hasExtra("gameLevel"))
        	gameLevel = intent.getIntExtra("gameLevel", 0);
		
		makeQuiz();
		initLayout();
		
		TextView tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_answer);
		tv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(gameState==0) {
					TextView tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_answer);
					tv.setText(String.valueOf(answer));
					gameState=1;
				} else {
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					intent.putExtra("gameType", gameType);
					intent.putExtra("gameLevel", gameLevel);
					startActivity(intent);
					finish();
				}
				return false;
			}
		});
	}
	
	private void makeQuiz() {
		switch(gameLevel){
		case 0:
			firstNumber = randomNumber(1, 5);
			secondNumber = randomNumber(1, 5);
			
			switch(gameType) {
			case MathActivity.TYPE_PLUS:
				answer = firstNumber + secondNumber;
				break;
			case MathActivity.TYPE_MINUS:
				answer = firstNumber - secondNumber;
				break;
			case MathActivity.TYPE_MULTIPLICATION:
				answer = firstNumber * secondNumber;
				break;
			case MathActivity.TYPE_DIVISION:
				answer = firstNumber / secondNumber;
				break;
			}
//			Log.e("NHK", String.valueOf(firstNumber) + " + " + String.valueOf(secondNumber) + " = " + String.valueOf(answer));
			break;
			
		case 1:
			firstNumber = randomNumber(1, 9);
			secondNumber = randomNumber(1, 9);
			
			switch(gameType) {
			case MathActivity.TYPE_PLUS:
				answer = firstNumber + secondNumber;
				break;
			case MathActivity.TYPE_MINUS:
				answer = firstNumber - secondNumber;
				break;
			case MathActivity.TYPE_MULTIPLICATION:
				answer = firstNumber * secondNumber;
				break;
			case MathActivity.TYPE_DIVISION:
				answer = firstNumber / secondNumber;
				break;
			}
			break;
			
		case 2:
			firstNumber = randomNumber(10, 50);
			secondNumber = randomNumber(1, 9);
			
			switch(gameType) {
			case MathActivity.TYPE_PLUS:
				answer = firstNumber + secondNumber;
				break;
			case MathActivity.TYPE_MINUS:
				answer = firstNumber - secondNumber;
				break;
			case MathActivity.TYPE_MULTIPLICATION:
				answer = firstNumber * secondNumber;
				break;
			case MathActivity.TYPE_DIVISION:
				answer = firstNumber / secondNumber;
				break;
			}
			break;
			
		case 3:
			firstNumber = randomNumber(10, 50);
			secondNumber = randomNumber(10, 50);
			
			switch(gameType) {
			case MathActivity.TYPE_PLUS:
				answer = firstNumber + secondNumber;
				break;
			case MathActivity.TYPE_MINUS:
				answer = firstNumber - secondNumber;
				break;
			case MathActivity.TYPE_MULTIPLICATION:
				answer = firstNumber * secondNumber;
				break;
			case MathActivity.TYPE_DIVISION:
				answer = firstNumber / secondNumber;
				break;
			}
			break;
		}
	}
	
	private int randomNumber(int min, int max) {
		max++;
		return new Random().nextInt(max-min)+min;
	}
	
	private void setImage(int imageViewId, int number, int type) {
		ImageView iv = (ImageView) findViewById(imageViewId);
		
		switch(type) {
		// finger
		case 0:
			switch(number) {
			case 1:
				iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_finger0);
				break;
			case 2:
				iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_finger1);
				break;
			case 3:
				iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_finger2);
				break;
			case 4:
				iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_finger3);
				break;
			case 5:
				iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_finger4);
				break;
			}
			break;
			
			// orrange
		case 1:
			
			break;
			
			// apple
		case 2:
		}
	}
	
	private void initLayout() {
		ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_quiz_operator);
		TextView tv;
		switch(gameType) {
		case MathActivity.TYPE_PLUS:
			iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_plus);
			break;
			
		case MathActivity.TYPE_MINUS:
			iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_minus);
			break;

		case MathActivity.TYPE_MULTIPLICATION:
			iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_multiplication);
			break;

		case MathActivity.TYPE_DIVISION:
			iv.setImageResource(R.drawable.image_contents_studyroom_math_quiz_division);
			break;
		}
		
		switch(gameLevel) {
		case 0:
			setImage(R.id.image_contents_studyroom_math_quiz_firstnumber, firstNumber, 0);
			setImage(R.id.image_contents_studyroom_math_quiz_secondnumber, secondNumber, 0);
			break;
			
		case 1:
		case 2:
		case 3:
			tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_firstnumber);
			tv.setText(String.valueOf(firstNumber));
			tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_secondnumber);
			tv.setText(String.valueOf(secondNumber));
			break;
		}
	}
}
