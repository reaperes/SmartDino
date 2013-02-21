package nhk.raon.smartdino.contents.studyroom.math;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class Ready2Activity extends Activity {
	
	private int gameType;
	private int gameLevel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents_studyroom_math_ready);

        Intent intent = getIntent();
        if (intent.hasExtra("gameType"))
        	gameType = intent.getIntExtra("gameType", 0);
        if (intent.hasExtra("gameLevel"))
        	gameLevel = intent.getIntExtra("gameLevel", 0);

        
        LinearLayout ll = (LinearLayout) findViewById(R.id.layout_contents_studyroom_math_ready);
        ll.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Ready2Activity.this, QuizActivity.class);
				intent.putExtra("gameType", gameType);
				intent.putExtra("gameLevel", gameLevel);
				startActivity(intent);
				finish();
			}
		});
	}
}
