package nhk.raon.smartdino.contents.studyroom;

import nhk.raon.smartdino.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class StudyRoomActivity extends Activity {
	
	private final Object obj1 = new Object();
	private final Object obj2 = new Object();
	private final Object obj3 = new Object();
	private final Object obj4 = new Object();
	
	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getTag() == obj1) {
	    		new AlertDialog.Builder(StudyRoomActivity.this)
	    			.setTitle("기억력")
	    			.setIcon(R.drawable.ic_launcher)
	    			.setMessage("기억력 게임을 실행하겠습니까?")
	    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
	    				@Override
	    				public void onClick(DialogInterface dialog, int which) {
	    					Toast.makeText(StudyRoomActivity.this, "기기기기억력", Toast.LENGTH_SHORT).show();
	    				}
	    			})
	    			.setNegativeButton("아니오", null)
	    			.show();
			} else if(v.getTag() == obj2) {
	    		new AlertDialog.Builder(StudyRoomActivity.this)
    			.setTitle("상식")
    			.setIcon(R.drawable.ic_launcher)
    			.setMessage("상식 게임을 실행하겠습니까?")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					Toast.makeText(StudyRoomActivity.this, "사사사삼식아", Toast.LENGTH_SHORT).show();
    				}
    			})
    			.setNegativeButton("아니오", null)
    			.show();
			} else if(v.getTag() == obj3) {
	    		new AlertDialog.Builder(StudyRoomActivity.this)
    			.setTitle("영어")
    			.setIcon(R.drawable.ic_launcher)
    			.setMessage("영어 학습을 하겠습니까?")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					Toast.makeText(StudyRoomActivity.this, "ABCD", Toast.LENGTH_SHORT).show();
    				}
    			})
    			.setNegativeButton("아니오", null)
    			.show();
			} else if(v.getTag() == obj4) {
	    		new AlertDialog.Builder(StudyRoomActivity.this)
    			.setTitle("산수")
    			.setIcon(R.drawable.ic_launcher)
    			.setMessage("산수 학습을 하겠습니까?")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					Toast.makeText(StudyRoomActivity.this, "산수 산수산수산 수", Toast.LENGTH_SHORT).show();
    				}
    			})
    			.setNegativeButton("아니오", null)
    			.show();
			}
		}
	};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_studyroom);
        
        ImageView iv1 = (ImageView) findViewById (R.id.contents_studyroom_memory);
        ImageView iv2 = (ImageView) findViewById (R.id.contents_studyroom_knowledge);
        ImageView iv3 = (ImageView) findViewById (R.id.contents_studyroom_english);
        ImageView iv4 = (ImageView) findViewById (R.id.contents_studyroom_math);
        
        iv1.setTag(obj1);
        iv1.setOnClickListener(listener);
        
        iv2.setTag(obj2);
        iv2.setOnClickListener(listener);
        
        iv3.setTag(obj3);
        iv3.setOnClickListener(listener);
        
        iv4.setTag(obj4);
        iv4.setOnClickListener(listener);
    }
}
