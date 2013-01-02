package nhk.raon.smartdino;

import kr.robomation.physical.Albert;

import org.roboid.robot.Device;
import org.roboid.robot.Robot;
import org.smartrobot.android.RobotActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends RobotActivity {

	private final int MAX_COUNT = 100;
	private final int MAX_V = 100;
	
	private Device mLeftWheelDevice;
	private Device mRightWheelDevice;
	
	private boolean mIsMove = false;
	private int mVCount=MAX_COUNT;
	private int mLeftV;
	private int mRightV;
	
	TextView tv;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.button4);
        Button btn5 = (Button)findViewById(R.id.button5);
        Button btn6 = (Button)findViewById(R.id.button6);
        Button btn7 = (Button)findViewById(R.id.button7);
        Button btn8 = (Button)findViewById(R.id.button8);
        Button btn9 = (Button)findViewById(R.id.button9);
        
        tv = (TextView)findViewById(R.id.text1);
        
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);        
    }
	
	Button.OnClickListener listener = new Button.OnClickListener(){
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.button1:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = 0;
				mRightV = -MAX_V/2;				
				break;
			case R.id.button2:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = -MAX_V;
				mRightV = -MAX_V;				
				break;
			case R.id.button3:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = -MAX_V/2;
				mRightV = MAX_V;				
				break;
			case R.id.button4:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = 0;
				mRightV = MAX_V;
				break;
			case R.id.button5:
				mIsMove = false;
				tv.setText("false");
				mVCount = 0;
				mLeftV = 0;
				mRightV = 0;				
				break;
			case R.id.button6:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = MAX_V;
				mRightV = 0;
				break;
			case R.id.button7:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = MAX_V/2;
				mRightV = MAX_V;				
				break;
			case R.id.button8:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = MAX_V;
				mRightV = MAX_V;
				break;
			case R.id.button9:
				mIsMove = true;
				tv.setText("true");
				mVCount = MAX_COUNT;
				mLeftV = MAX_V;
				mRightV = MAX_V/2;				
				break;
			}
		}
	};
	
	@Override
	public void onInitialized(Robot robot) {
		mLeftWheelDevice = robot.findDeviceById(Albert.EFFECTOR_LEFT_WHEEL);
		mRightWheelDevice = robot.findDeviceById(Albert.EFFECTOR_RIGHT_WHEEL);
	}
	
	@Override
	public void onExecute() {
		if (!mIsMove) {
			mLeftWheelDevice.write(0);
			mRightWheelDevice.write(0);
			return ;
		}
		
		if(mVCount > 100) {
			mLeftWheelDevice.write(mLeftV);
			mRightWheelDevice.write(mRightV);
			mVCount--;
		} else if (mVCount > 40) {
			mLeftWheelDevice.write(mLeftV/2);
			mRightWheelDevice.write(mRightV/2);
			mVCount--;
		} else if (mVCount > 20) {
			mLeftWheelDevice.write(mLeftV/3);
			mRightWheelDevice.write(mRightV/3);
			mVCount--;
		} else if (mVCount > 0) {
			mLeftWheelDevice.write(mLeftV/4);
			mRightWheelDevice.write(mRightV/4);
			mVCount--;
		} else
			mIsMove = false;
	}
}