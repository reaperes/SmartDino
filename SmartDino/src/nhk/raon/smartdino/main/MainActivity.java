package nhk.raon.smartdino.main;

import java.util.Random;

import kr.robomation.peripheral.Pen;
import kr.robomation.physical.Albert;
import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.contents.studyroom.StudyRoomActivity;

import org.roboid.robot.Device;
import org.roboid.robot.Robot;
import org.smartrobot.android.SmartRobot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity implements SmartRobot.Callback {
	
	private MainView mainView;
	private boolean isMoving=false;
	
	private int count = 0;
	private ImageView imageBathtub0;
	private ImageView imageBathtub1;
	private ImageView sponge;
	
	private int state = 0;
	private static final int NULL 		= 0;
	private static final int BATHTUB 	= NULL + 1;
	private static final int FOOD 		= NULL + 2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mainView = new MainView(this, MainActivity.this);
        state = 0;
		setContentView(mainView);
	}

	@Override
	public void onStart() {
		Log.e("NHK", "onStart");
		
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.e("NHK", "onResume");
		
		super.onResume();
	}
	
	@Override
	public void onStop() {
		Log.e("NHK", "onStop");
//		mainView.stop();
		
		super.onStop();
	}
	
	@Override
	public void onPause() {
		Log.e("NHK", "onPause");
		
		super.onPause();
	}
	
	@Override
	public boolean dispatchTouchEvent (MotionEvent ev) {
//		temporary code
		if(mainView.State == MainView.STATE_ANIMATION)
			return super.dispatchTouchEvent(ev);
		
		switch(ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(ev.getX() > SmartDino.Device_Width-100 && ev.getY() < 100) {
				mainView.dino.setMaxSpeed(mainView.dino.getMaxSpeed()+1);
				return true;
			} else if(ev.getX() > SmartDino.Device_Width-100 && ev.getY() > SmartDino.Device_Height-100) {
				mainView.dino.setMaxSpeed(mainView.dino.getMaxSpeed()-1);
				return true;
			} else if(ev.getX() < 100 && ev.getY() < 100) {
				mainView.threadSleepTime = mainView.threadSleepTime + 10;
				mainView.thread.SLEEP_TIME = mainView.threadSleepTime; 
				return true;
			} else if(ev.getX() < 100 && ev.getY() > SmartDino.Device_Height-100) {
				mainView.threadSleepTime = mainView.threadSleepTime - 10;
				mainView.thread.SLEEP_TIME = mainView.threadSleepTime;
				return true;
			}
			break;
		}
		
		return super.dispatchTouchEvent(ev);
	}
	
	public void setBathtubView() {
		state = BATHTUB;
		setContentView(R.layout.layout_main_bathtub);
		FrameLayout fl = (FrameLayout) findViewById(R.id.layout_main_bathtub);
		fl.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				runOnUiThread(runnable);
				return false;
			}
		});
		
		imageBathtub0 = (ImageView) findViewById(R.id.image_main_bathtub0);
		imageBathtub1 = (ImageView) findViewById(R.id.image_main_bathtub1);
		sponge = new ImageView(this);
		sponge.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		sponge.setImageResource(R.drawable.image_main_bathtub_sponge);
		fl.addView(sponge);
		
		boolean is = SmartRobot.activate(this, this);
		if(is)
			Log.e("NHK", "Albert is connected");
		else
			Log.e("NHK", "Albert is NOT connected");
	}
	
	public void moveActivity(int n) {
		switch(n) {
		case MainView.GO_CONTENTS_STUDYROOM:
			if(!isMoving) {
	    		Intent intent = new Intent().setClass(MainActivity.this, StudyRoomActivity.class);
	    		startActivity(intent);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
    		
		case MainView.GO_MAIN2:
			if(!isMoving) {
	    		Intent intent2 = new Intent().setClass(MainActivity.this, Main2Activity.class);
	    		startActivity(intent2);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
		}
	}
	
	///////////////////////////////////////////////
	// Animation
	private AnimationDrawable mframeAnimation;
	private Device mFrontOIDDevice;
	private Device mOIDDevice;			// for testing variable
	
	public void playFood() {
		state = FOOD;
		setContentView(R.layout.layout_main_food);
		ImageView img = (ImageView) findViewById(R.id.image_main_foodAnimation);
		
		BitmapDrawable frame0 = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.animation_main_food0));
		BitmapDrawable frame1 = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.animation_main_food1));
		BitmapDrawable frame2 = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.animation_main_food2));
		BitmapDrawable frame3 = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.animation_main_food3));
		
		int duration = 1000;
		mframeAnimation = new AnimationDrawable();
		mframeAnimation.setOneShot(true);
		mframeAnimation.addFrame(frame0, duration);
		mframeAnimation.addFrame(frame1, duration);
		mframeAnimation.addFrame(frame2, duration);
		mframeAnimation.addFrame(frame3, duration);
		
		img.setImageDrawable(mframeAnimation);
		SmartRobot.activate(this, this);
	}
	
	public Runnable runnable = new Runnable() {
		public void run(){
			if(count++%2 == 0) {
				imageBathtub0.setVisibility(View.VISIBLE);
				imageBathtub1.setVisibility(View.INVISIBLE);
			} else {
				imageBathtub0.setVisibility(View.INVISIBLE);
				imageBathtub1.setVisibility(View.VISIBLE);
			}
			Log.e("NHK", "shower");
//			new Random().nextInt(max-min)+min;
			sponge.animate().x(new Random().nextInt(SmartDino.Device_Width*3/4-SmartDino.Device_Width*1/4)+SmartDino.Device_Width*1/4).y(new Random().nextInt(SmartDino.Device_Height*3/4-SmartDino.Device_Height*1/4)+SmartDino.Device_Height*1/4);
			if(count > 10) {
				FrameLayout fl = (FrameLayout) findViewById(R.id.layout_main_bathtub);
				fl.setOnTouchListener(null);
				setContentView(mainView);
				count = 0;
				state = NULL;
				SmartRobot.deactivate();
			}
		}
	};
	
	public Runnable runnable2 = new Runnable() {
		public void run(){
			mframeAnimation.start();
			
			int timeBetweenChecks = 5000;
			Handler h = new Handler();
	        h.postDelayed(new Runnable(){
	            public void run(){
	            	while(true) {
		                if (mframeAnimation.getCurrent() == mframeAnimation.getFrame(mframeAnimation.getNumberOfFrames() - 1)) {
		                	state = NULL;
		                	setContentView(mainView);
		                	SmartRobot.deactivate();
		                    break;
		                }
	            	}
	            }
	        }, timeBetweenChecks);
		}
	};
	
    private Device mLeftProximityDevice;
    private Device mRightProximityDevice;
    private boolean bathOn;
	
	@Override
	public void onExecute() {
		switch(state) {
		case BATHTUB:
			if(bathOn) {
				if(mLeftProximityDevice.read() + mRightProximityDevice.read() < 90) {
					bathOn = false;
				}
			} else {
				if(mLeftProximityDevice.read() + mRightProximityDevice.read() > 110) {
					bathOn = true;
					runOnUiThread(runnable);
				}
			}
			break;
			
		case FOOD:
	         int frontOID = -1;
	         if(mFrontOIDDevice.e()) // 이벤트가 발생하였다.
	         {
	             frontOID = mFrontOIDDevice.read();
	             if(frontOID == 1) {
	            	 runOnUiThread(runnable2);		
	            	 break;
	             }
	         }
	         // temporary code
	         if(mOIDDevice.e()) // 이벤트가 발생하였다.
	         {
	             frontOID = mOIDDevice.read();
	             Log.e("NHK", String.valueOf(frontOID));
	             if(frontOID == 1) {
	            	 runOnUiThread(runnable2);
	            	 break;
	             }
	         }
		}
	}
	
	private void startFood() {
			
	}

	@Override
	public void onInitialized(Robot robot) {
		Log.e("NHK", "onInitialized()");
        mLeftProximityDevice = robot.findDeviceById(Albert.SENSOR_LEFT_PROXIMITY);
        mRightProximityDevice = robot.findDeviceById(Albert.SENSOR_RIGHT_PROXIMITY);
        mFrontOIDDevice = robot.findDeviceById(Albert.EVENT_FRONT_OID);
        mOIDDevice = robot.findDeviceById(1, Pen.EVENT_OID);
    }

	@Override
	public void onActivated() {
		// TODO Auto-generated method stub
		Log.e("NHK", "onActivated()");
		bathOn = false;
	}

	@Override
	public void onDeactivated() {
		// TODO Auto-generated method stub
		Log.e("NHK", "onDeactivated()");
	}

	@Override
	public void onDisposed() {
		// TODO Auto-generated method stub
		Log.e("NHK", "onDisposed()");
	}

	@Override
	public void onStateChanged(int state) {
		// TODO Auto-generated method stub
		Log.e("NHK", "onStateChanged()");
	}

	@Override
	public void onNameChanged(String name) {
		// TODO Auto-generated method stub
		Log.e("NHK", "onNameChanged()");
	}
}
