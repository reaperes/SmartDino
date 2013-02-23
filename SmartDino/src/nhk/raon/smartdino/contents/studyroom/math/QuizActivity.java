package nhk.raon.smartdino.contents.studyroom.math;

import java.util.Random;

import kr.robomation.peripheral.Pen;

import org.roboid.robot.Device;
import org.roboid.robot.Robot;
import org.smartrobot.android.SmartRobot;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends Activity implements SmartRobot.Callback {
	private int gameType;
	private int gameLevel;
	private int gameCount;
	private int gameState;		// 0: pre-answer	1: after-answer		2: go-Activity
	
	private int firstNumber;
	private int secondNumber;
	private int answer;
	private int answerCount;
	private String currentPlayerAnswer;
	private int answerInputCount;
	private int correctAnswerCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_quiz);

        Intent intent = getIntent();
        if (intent.hasExtra("gameType"))
        	gameType = intent.getIntExtra("gameType", 0);
        if (intent.hasExtra("gameLevel"))
        	gameLevel = intent.getIntExtra("gameLevel", 0);
		
        // Quiz
        gameCount = 1;
        correctAnswerCount = 0;
        displayQuiz();
        
        ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_quiz_result);
        iv.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if(gameState != 1)
					return ;
				if(gameCount < 5) {
					displayQuiz();
					gameCount++;
				} else {
					SmartRobot.deactivate();
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					intent.putExtra("gameType", gameType);
					intent.putExtra("gameLevel", gameLevel);
					intent.putExtra("correctAnswerCount", correctAnswerCount);
					startActivity(intent);
					finish();				
				}
			}
		});
        
//		for testing
//        
//		TextView tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_answer);
//		tv.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if(gameState==0) {
//					TextView tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_answer);
//					tv.setText(String.valueOf(answer));
//					gameState=1;
//				} else if(gameState==1) {
//					displayResult(true);
//					gameState=2;
//				} else {
//					if(gameCount < 5) {
//						displayQuiz();
//						gameCount++;
//					} else {
//						Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
//						intent.putExtra("gameType", gameType);
//						intent.putExtra("gameLevel", gameLevel);
//						intent.putExtra("correctAnswerCount", correctAnswerCount);
//						startActivity(intent);
//						finish();
//					}
//				}
//				return false;
//			}
//		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		SmartRobot.activate(this, this);
	}
	
	@Override
	protected void onPause() {
		SmartRobot.deactivate();
		
		super.onPause();
	}	
	
	private void displayQuiz() {
		initResult();
		makeQuiz();
		initLayout();
		gameState = 0;
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
		if (answer > 999)
			answerCount = 4;
		else if (answer > 99)
			answerCount = 3;
		else if (answer > 9)
			answerCount = 2;
		else
			answerCount = 1;
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
	
	private void setResult(String str) {
		TextView tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_answer);
		tv.setText(str);
	}
	
	private void initResult() {
		Runnable runnable;
		runnable = new Runnable() {
			public void run() {
				ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_quiz_result);
				iv.setVisibility(ImageView.INVISIBLE);
			}
		};
		runOnUiThread(runnable);
	}
	
	private void displayResult(boolean isCorrect) {
		Runnable runnable;
		if(isCorrect) {
			runnable = new Runnable() {
				public void run() {
					ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_quiz_result);
					iv.setImageResource(R.drawable.image_contents_studyroom_correct);
					iv.setVisibility(ImageView.VISIBLE);
					correctAnswerCount++;
				}
			};
		} else {
			runnable = new Runnable() {
				public void run() {
					ImageView iv = (ImageView) findViewById(R.id.image_contents_studyroom_math_quiz_result);
					iv.setImageResource(R.drawable.image_contents_studyroom_incorrect);
					iv.setVisibility(ImageView.VISIBLE);
				}
			};
		}
		runOnUiThread(runnable);
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
		
		// ? ?? ???
		tv = (TextView) findViewById(R.id.text_contents_studyroom_math_quiz_answer);
		if(answerCount == 4)
			tv.setText("????");
		else if(answerCount == 3)
			tv.setText("???");
		else if(answerCount == 2)
			tv.setText("??");
		else
			tv.setText("?");
		answerInputCount = 0;
	}
	
	private Device mOIDDevice;

	@Override
	public void onInitialized(Robot robot) {
		// TODO Auto-generated method stub
		mOIDDevice = robot.findDeviceById(1, Pen.EVENT_OID);
	}

	@Override
	public void onActivated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeactivated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisposed() {
		// TODO Auto-generated method stub
		
	}

	private boolean isTouched=false;
	@Override
	public void onExecute() {
		if (gameState == 0) {
			int event = -1;
			if (mOIDDevice.e()) // 이벤트가 발생하였다.
			{
				event = mOIDDevice.read();
				Log.e("NHK", String.valueOf(event));
				if (event > -1 && event < 10) {
					if(isTouched==true)
						return ;
					isTouched = true;
					inputNumber(event);
					runOnUiThread(new Runnable(){
						public void run() {
							setResult(currentPlayerAnswer);
						}
					});
					
					answerInputCount++;
					if(answerCount == answerInputCount) {
						if (answer == Integer.parseInt(currentPlayerAnswer)) {
							runOnUiThread(new Runnable(){
								public void run() {
									displayResult(true);
									gameState=1;						
								}
							});
						} else {
							runOnUiThread(new Runnable(){
								public void run() {
									displayResult(false);
									gameState=1;									
								}
							});							
						}
					}
				} else {
					isTouched = false;
				}
			}
		}
	}
	
	private void inputNumber(int num) {
		if(answerCount == 4) {
			switch(answerInputCount) {
			case 0:
				currentPlayerAnswer = String.valueOf(num) + "???";
				break;
			case 1:
				currentPlayerAnswer = currentPlayerAnswer.substring(0, 1);
				currentPlayerAnswer = currentPlayerAnswer + String.valueOf(num) + "??";
				break;
			case 2:
				currentPlayerAnswer = currentPlayerAnswer.substring(0, 2);
				currentPlayerAnswer = currentPlayerAnswer + String.valueOf(num) + "?";
				break;
			case 3:
				currentPlayerAnswer = currentPlayerAnswer.substring(0, 3);
				currentPlayerAnswer = currentPlayerAnswer + String.valueOf(num);
				break;
			}
		} else if(answerCount == 3) {
			switch(answerInputCount) {
			case 0:
				currentPlayerAnswer = String.valueOf(num) + "??";
				break;
			case 1:
				currentPlayerAnswer = currentPlayerAnswer.substring(0, 1);
				currentPlayerAnswer = currentPlayerAnswer + String.valueOf(num) + "?";
				break;
			case 2:
				currentPlayerAnswer = currentPlayerAnswer.substring(0, 2);
				currentPlayerAnswer = currentPlayerAnswer + String.valueOf(num);
				break;
			}
		} else if(answerCount == 2) {
			switch(answerInputCount) {
			case 0:
				currentPlayerAnswer = String.valueOf(num) + "?";
				break;
			case 1:
				currentPlayerAnswer = currentPlayerAnswer.substring(0, 1);
				currentPlayerAnswer = currentPlayerAnswer + String.valueOf(num);
				break;
			}
		} else {
			currentPlayerAnswer = String.valueOf(num);
		}
	}

	@Override
	public void onStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNameChanged(String name) {
		// TODO Auto-generated method stub
		
	}
}
