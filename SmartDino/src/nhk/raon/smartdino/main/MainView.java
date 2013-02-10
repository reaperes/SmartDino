package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {
	
	public static final int STATE_INIT			= 0;
	public static final int STATE_RUNNING		= STATE_INIT + 1;
	public static final int STATE_ANIMATION		= STATE_INIT + 2;
	
	public int State = STATE_INIT;
	
	public static final int GO_CONTENTS_STUDYROOM = 1;
	public static final int GO_MAIN2 = GO_CONTENTS_STUDYROOM + 1;

	public Object obj = new Object();
	
	private MainActivity activity;
	public MainViewThread thread;
	private Dino dino;
	
	private Bitmap background;
	public GraphicObject bathtub;
	public GraphicObject food;

	public MainView(Context context, MainActivity activity) {
		super(context);
		this.activity = activity;

		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// load bitmap
		init_0_grahpicObject();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.e("NHK", "surfaceChanged");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		Log.e("NHK", "surfaceCreated");
		
		start();
		State = STATE_RUNNING;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e("NHK", "surfaceDestroyed");
		stop();
	}
	
	public void update() {
		switch(collisionCheck()) {
		case GO_CONTENTS_STUDYROOM:
			stop();
			activity.moveActivity(GO_CONTENTS_STUDYROOM);
			return ;
			
		case GO_MAIN2:
			stop();
			activity.moveActivity(GO_MAIN2);
			return ;
		}
		dino.update();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
			dino.handleActionDown((int) event.getX(), (int) event.getY());
		
		// for battery saving
		try {
			Thread.sleep(30);
		} catch(Exception e) {}

		return true;
	}
	
	public void start() {
		stop();

		// create the game loop thread
		thread = MainViewThread.getThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		
		thread.setRunning(true);
		thread.start();
	}
	
	public void stop() {
		if(thread==null)
			return ;
		Log.e("NHK", "Hey Thread stop");
		thread.setRunning(false);
		synchronized(this) {
			this.notify();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
	}

	// the fps to be displayed
	private String avgFps;

	public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}

	public void render(Canvas canvas) {
		canvas.drawBitmap(background, 0, 0, null);
		bathtub.draw(canvas);
		food.draw(canvas);
		dino.draw(canvas);
		
		// display fps
		displayFps(canvas, avgFps);
	}

	private void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 30, paint);
//			canvas.drawText("Thread Sleep Time: "+String.valueOf(activity.threadSleepTime), 10, 30, paint);
//			canvas.drawText("Dino speed: "+String.valueOf(droid.getSpeed()), 10, 60, paint);
		}
	}
	
	private void init_0_grahpicObject() {
		// create background
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background_main);
		
		// create dino
		dino = new Dino(activity, SmartDino.Dino_Type);
		
		// create Graphic Objects
		bathtub = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_main_bathtub));
		food = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_main_food));
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		dino.setXY(displayMetrics.widthPixels/2, displayMetrics.heightPixels/2);
		bathtub.setXY((float)(displayMetrics.widthPixels*0.1), (float)(displayMetrics.heightPixels*0.5));
		food.setXY((float)(displayMetrics.widthPixels*0.75), (float)(displayMetrics.heightPixels*0.66));
	}
	
	public int collisionCheck() {
		switch(SmartDino.Device_Type) {
		case DisplayMetrics.DENSITY_XHIGH:
			if(dino.x > 125 && dino.x < 270 && dino.y < 280 && dino.y > 75)
				return GO_CONTENTS_STUDYROOM;
			
			if(dino.x > 930 && dino.x < 1140 && dino.y < 255 && dino.y > 15)
				return GO_MAIN2;
			break;
			
		case DisplayMetrics.DENSITY_HIGH:
			if(dino.x > 78 && dino.x < 168 && dino.y < 168 && dino.y > 45)
				return GO_CONTENTS_STUDYROOM;
			
			if(dino.x > 720 && dino.x < 829 && dino.y < 153 && dino.y > 9)
				return GO_MAIN2;
			break;
		}
		
		return 0;
	}
}