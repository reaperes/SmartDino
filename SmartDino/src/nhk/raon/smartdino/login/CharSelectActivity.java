package nhk.raon.smartdino.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;
import nhk.raon.smartdino.main.MainActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CharSelectActivity extends Activity implements View.OnClickListener {

	///////////////////////////////////
	// Pointer Variables
	private int pointerState;
	private final static int POINTER_NULL 	= 0;
	private final static int POINTER_0 		= POINTER_NULL+1;
	private final static int POINTER_1		= POINTER_NULL+2;
	private final static int POINTER_2		= POINTER_NULL+3;
	private ImageView image_point0;
	private ImageView image_point1;
	private ImageView image_point2;

	///////////////////////////////////
	// Dino Variables
	private ImageView image_char0;
	private ImageView image_char1;
	private ImageView image_char2;
	
	private TextView text_makeChar0;
	private TextView text_makeChar1;
	private TextView text_makeChar2;
	
	///////////////////////////////////
	// Button Variables
	private TextView text_delete;
	
	///////////////////////////////////
	// file Variables
	private final String strSaveFile0 = "data00.txt";
	private final String strSaveFile1 = "data01.txt";
	private final String strSaveFile2 = "data02.txt";
	
	private boolean isSaveFile0 = false;
	private boolean isSaveFile1 = false;
	private boolean isSaveFile2 = false;
	
	private int dino0_type;
	private int dino1_type;
	private int dino2_type;
	
//	private String name0;
//	private String name1;
//	private String name2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charselect);
        
        image_point0 = (ImageView) findViewById(R.id.login_charselect_image_pointer0);
        image_point1 = (ImageView) findViewById(R.id.login_charselect_image_pointer1);
        image_point2 = (ImageView) findViewById(R.id.login_charselect_image_pointer2);
        
        image_char0 = (ImageView) findViewById(R.id.login_charselect_image_char0);
        image_char1 = (ImageView) findViewById(R.id.login_charselect_image_char1);
        image_char2 = (ImageView) findViewById(R.id.login_charselect_image_char2);
        
        text_makeChar0 = (TextView) findViewById(R.id.login_charselect_text_char0);
        text_makeChar1 = (TextView) findViewById(R.id.login_charselect_text_char1);
        text_makeChar2 = (TextView) findViewById(R.id.login_charselect_text_char2);
        
        text_delete = (TextView) findViewById(R.id.login_charselect_text_delete);
        
        FrameLayout fl0 = (FrameLayout) findViewById(R.id.login_charselect_slot_0);
        FrameLayout fl1 = (FrameLayout) findViewById(R.id.login_charselect_slot_1);
        FrameLayout fl2 = (FrameLayout) findViewById(R.id.login_charselect_slot_2);

        fl0.setOnClickListener(this);
        fl1.setOnClickListener(this);
        fl2.setOnClickListener(this);
        text_delete.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		initPointer();
        loadData();
        loadImage();
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		// slot
		case R.id.login_charselect_slot_0:
			handlePointer(POINTER_0);
			break;
		case R.id.login_charselect_slot_1:
			handlePointer(POINTER_1);
			break;
		case R.id.login_charselect_slot_2:
			handlePointer(POINTER_2);
			break;
			
		// button
		case R.id.login_charselect_text_delete:
			handleText();
			break;
		}
	}
	
	private void goMain(int DinoType) {
		SmartDino.Dino_Type = DinoType;
		Intent intent = new Intent().setClass(CharSelectActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	private void handlePointer(int n) {
		switch(n) {
		case POINTER_0:
			if(pointerState == POINTER_0) {
				if(isSaveFile0) {
					SmartDino.Record_Number = 0;
					goMain(dino0_type);
				} else {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, CharMakeActivity.class);
		    		intent.putExtra("fileName", strSaveFile0);
		    		intent.putExtra("dataNumber", 0);
		    		startActivity(intent);
		    		finish();
				}
				return ;
			}
			
			pointerState = POINTER_0;
			image_point0.setVisibility(View.VISIBLE);
			image_point1.setVisibility(View.INVISIBLE);
			image_point2.setVisibility(View.INVISIBLE);
			break;
			
		case POINTER_1:
			if(pointerState == POINTER_1) {
				if(isSaveFile1) {
					SmartDino.Record_Number = 1;
					goMain(dino1_type);
				} else {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, CharMakeActivity.class);
		    		intent.putExtra("fileName", strSaveFile1);
		    		intent.putExtra("dataNumber", 1);
		    		startActivity(intent);
		    		finish();
				}
				return ;
			}
			
			pointerState = POINTER_1;
			image_point0.setVisibility(View.INVISIBLE);
			image_point1.setVisibility(View.VISIBLE);
			image_point2.setVisibility(View.INVISIBLE);
			break;
			
		case POINTER_2:
			if(pointerState == POINTER_2) {
				if(isSaveFile2) {
					SmartDino.Record_Number = 2;
					goMain(dino2_type);
				} else {
		    		Intent intent = new Intent().setClass(CharSelectActivity.this, CharMakeActivity.class);
		    		intent.putExtra("fileName", strSaveFile2);
		    		intent.putExtra("dataNumber", 2);
		    		startActivity(intent);
		    		finish();
				}
				return ;
			}
			
			pointerState = POINTER_2;
			image_point0.setVisibility(View.INVISIBLE);
			image_point1.setVisibility(View.INVISIBLE);
			image_point2.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	private void handleText() {
		if (pointerState == POINTER_NULL)
			return ;
		
		switch(pointerState) {
		case POINTER_0:
			if(!isSaveFile0)
				return ;
			new AlertDialog.Builder(this)
			.setTitle("저장 파일 지우기")
			.setMessage("정말로 지우실건가요?")
			.setPositiveButton("네", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteSaveFile(strSaveFile0);
					loadData();
					loadImage();
				}
			})
			.setNegativeButton("아니오", null)
			.show();
			break;
		
		case POINTER_1:
			if(!isSaveFile1)
				return ;
			new AlertDialog.Builder(this)
			.setTitle("저장 파일 지우기")
			.setMessage("정말로 지우실건가요?")
			.setPositiveButton("네", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteSaveFile(strSaveFile1);
					loadData();
					loadImage();
				}
			})
			.setNegativeButton("아니오", null)
			.show();
			break;

		case POINTER_2:
			if(!isSaveFile2)
				return ;
			new AlertDialog.Builder(this)
			.setTitle("저장 파일 지우기")
			.setMessage("정말로 지우실건가요?")
			.setPositiveButton("네", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteSaveFile(strSaveFile2);
					loadData();
					loadImage();
				}
			})
			.setNegativeButton("아니오", null)
			.show();
			break;
		}
	}
	
//	private void goMain()
	
	private void deleteSaveFile(String str) {
		File file = new File("/data/data/nhk.raon.smartdino/files/" + str);
		if (file.exists()) {
			file.delete();
		}
	}
	
	private String readFile(String fileName) {
		BufferedReader br;
//		StringBuffer sb = new StringBuffer();
		String str;
		
		try {
			br = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

			str = br.readLine();
//        	while((str = br.readLine()) != null) {
//        		sb.append(str+"\n");
//        	}
        	br.close();
		} catch (Exception e) {
			str = null;
        }
		
		return str;
	}
	
	private void initPointer() {
		pointerState = POINTER_NULL;
		image_point0.setVisibility(View.INVISIBLE);
		image_point1.setVisibility(View.INVISIBLE);
		image_point2.setVisibility(View.INVISIBLE);
	}
	
	private void loadData() {
		String data0 = readFile(strSaveFile0);
		if(data0 == null) {
			isSaveFile0 = false;
		} else {
			isSaveFile0 = true;
			dino0_type = Integer.parseInt(data0);
		}
		
		String data1 = readFile(strSaveFile1);
		if(data1 == null) {
			isSaveFile1 = false;
		} else {
			isSaveFile1 = true;
			dino1_type = Integer.parseInt(data1);
		}
		
		String data2 = readFile(strSaveFile2);
		if(data2 == null) {
			isSaveFile2 = false;
		} else {
			isSaveFile2 = true;
			dino2_type = Integer.parseInt(data2);
		}
	}
	
	private void loadImage() {
		if(isSaveFile0) {
			loadDinoImage(image_char0, dino0_type);
			text_makeChar0.setVisibility(View.INVISIBLE);
		} else {
			image_char0.setImageDrawable(null);
			text_makeChar0.setVisibility(View.VISIBLE);
		}
		if(isSaveFile1) {
			loadDinoImage(image_char1, dino1_type);
			text_makeChar1.setVisibility(View.INVISIBLE);
		} else {
			image_char1.setImageDrawable(null);
			text_makeChar1.setVisibility(View.VISIBLE);
		}
		if(isSaveFile2) {
			loadDinoImage(image_char2, dino2_type);
			text_makeChar2.setVisibility(View.INVISIBLE);
		} else {
			image_char2.setImageDrawable(null);
			text_makeChar2.setVisibility(View.VISIBLE);
		}
	}
	
	private void loadDinoImage(ImageView image, int dino_type) {
		switch(dino_type) {
		case SmartDino.DINO_TYPE_0:
			image.setImageResource(R.drawable.char0);
			break;
		
		case SmartDino.DINO_TYPE_1:
			image.setImageResource(R.drawable.char1);
			break;
			
		case SmartDino.DINO_TYPE_2:
			image.setImageResource(R.drawable.char2);
			break;

		case SmartDino.DINO_TYPE_3:
			image.setImageResource(R.drawable.char3);
			break;
		}
	}
}