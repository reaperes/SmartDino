package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.StatusBar;
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

public class Main2View extends SurfaceView implements SurfaceHolder.Callback {
	
	public static final int GO_MAIN = 1;
	public static final int GO_CONTENTS_CATCHFRUITS = GO_MAIN + 1;
	public static final int GO_CONTENTS_JUNGLE = GO_MAIN + 2;
	public static final int GO_CONTENTS_SHOP = GO_MAIN + 3;

	public Object obj = new Object();

	public int threadSleepTime=50;
	
	private Main2Activity activity;
	public Main2ViewThread thread;
	private Dino dino;
	
	///////////////////////////////////////////////
	// StatusBar Variables
	public StatusBar statusBar;
	
	private Bitmap background;

	public Main2View(Context context, Main2Activity activity) {
		super(context);
		this.activity = activity;

		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// load bitmap
		init_0_grahpicObject();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		stop();
	}
	
	public void update() {
		int state = collisionCheck();
		if(state != 0) {
			stop();
			activity.moveActivity(state);
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
		thread = Main2ViewThread.getThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		
		thread.setRunning(true);
		thread.start();
	}
	
	public void stop() {
		if(thread==null)
			return ;
		Log.e("NHK", "Hey Stop Thread");			
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
		dino.draw(canvas);
		
		statusBar.draw(canvas);
		
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
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background_main2);
		
		// create dino
		dino = new Dino(activity, SmartDino.Dino_Type);
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		dino.setXY(displayMetrics.widthPixels/2, displayMetrics.heightPixels/2);
		
		// Status Bar
		statusBar = new StatusBar(activity);
	}
	
	public int collisionCheck() {
		switch(SmartDino.Device_Type) {
		case DisplayMetrics.DENSITY_XHIGH:
			if(dino.x > 136 && dino.x < 265 && dino.y > 100 && dino.y > 170)
				return GO_CONTENTS_JUNGLE;
			
			if(dino.x > 25 && dino.x < 420 && dino.y < 620 )
				return GO_CONTENTS_CATCHFRUITS;

			if(dino.x > 1100 && dino.y > 510)
				return GO_CONTENTS_SHOP;

			if(dino.x > 770 && dino.x < 945 && dino.y > 280 && dino.y < 370) {
				return GO_MAIN;
			}
			break;
			
		case DisplayMetrics.DENSITY_HIGH:
			if(dino.x > 85 && dino.x < 165 && dino.y > 60 && dino.y > 102)
				return GO_CONTENTS_JUNGLE;
			
			if(dino.x > 16 && dino.x < 262 && dino.y < 372 )
				return GO_CONTENTS_CATCHFRUITS;

			if(dino.x > 687 && dino.y > 306)
				return GO_CONTENTS_SHOP;

			if(dino.x > 481 && dino.x < 590 && dino.y > 168 && dino.y < 222) {
				return GO_MAIN;
			}
			break;
		}
		return 0;
	}
}