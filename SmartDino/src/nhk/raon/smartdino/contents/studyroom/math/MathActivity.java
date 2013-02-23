package nhk.raon.smartdino.contents.studyroom.math;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MathActivity extends Activity {

	public static final int TYPE_PLUS				= 1;
	public static final int TYPE_MINUS				= 2;
	public static final int TYPE_MULTIPLICATION		= 3;
	public static final int TYPE_DIVISION			= 4;
	
	private ImageView imageHomeButton;
	private ImageView imageButtonPlus;
	private ImageView imageButtonMinus;
	private ImageView imageButtonMultiplication;
	private ImageView imageButtonDivision;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_main);
		
		imageHomeButton = (ImageView) findViewById(R.id.image_contents_studyroom_math_main_homebutton);
		imageButtonPlus = (ImageView) findViewById(R.id.image_contents_studyroom_math_main_plus);
		imageButtonMinus = (ImageView) findViewById(R.id.image_contents_studyroom_math_main_minus);
		imageButtonMultiplication = (ImageView) findViewById(R.id.image_contents_studyroom_math_main_multiplication);
		imageButtonDivision = (ImageView) findViewById(R.id.image_contents_studyroom_math_main_division);
		
		imageHomeButton.setOnClickListener(homeListener);
		imageButtonPlus.setOnClickListener(plusListener);
		imageButtonMinus.setOnClickListener(minusListener);
		imageButtonMultiplication.setOnClickListener(multiplicationListener);
		imageButtonDivision.setOnClickListener(divisionListener);
	}
	
	private ImageView.OnClickListener homeListener = new ImageView.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MathActivity.this, StudyRoomActivity.class);
			startActivity(intent);
			finish();
		}
	};	

	private ImageView.OnClickListener plusListener = new ImageView.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MathActivity.this, SelectLevelActivity.class);
			intent.putExtra("gameType", TYPE_PLUS);
			startActivity(intent);
			finish();
		}
	};
	
	private ImageView.OnClickListener minusListener = new ImageView.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MathActivity.this, SelectLevelActivity.class);
			intent.putExtra("gameType", TYPE_MINUS);
			startActivity(intent);
			finish();
		}
	};

	private ImageView.OnClickListener multiplicationListener = new ImageView.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MathActivity.this, SelectLevelActivity.class);
			intent.putExtra("gameType", TYPE_MULTIPLICATION);
			startActivity(intent);
			finish();
		}
	};

	private ImageView.OnClickListener divisionListener = new ImageView.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MathActivity.this, SelectLevelActivity.class);
			intent.putExtra("gameType", TYPE_DIVISION);
			startActivity(intent);
			finish();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
