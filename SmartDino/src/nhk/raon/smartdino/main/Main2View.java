package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
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
	
	private Main2Activity activity;
	public Main2ViewThread thread;
	private Dino dino;
	
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
//		Log.e("NHK", "surface view EVENT");
		
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
			
		thread.setRunning(false);
		synchronized(this) {
			this.notify();
		}
		Log.d("NHK", "Thread has shut down cleanly");
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
		Bitmap[] leftBitmap = new Bitmap[2];
		Bitmap[] rightBitmap = new Bitmap[2];
		leftBitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_left0);
		leftBitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_left1);
		rightBitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_right0);
		rightBitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_right1);
		dino = new Dino(BitmapFactory.decodeResource(getResources(), R.drawable.char_0), leftBitmap, rightBitmap);
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		dino.setXY(displayMetrics.widthPixels/2, displayMetrics.heightPixels/2);
	}
	
	public int collisionCheck() {
		if(dino.x > 136 && dino.x < 265 && dino.y > 100 && dino.y > 170)
			return GO_CONTENTS_JUNGLE;
		
		if(dino.x > 25 && dino.x < 420 && dino.y > 620 )
			return GO_CONTENTS_CATCHFRUITS;

		if(dino.x > 1100 && dino.y > 510)
			return GO_CONTENTS_SHOP;

		if(dino.x > 770 && dino.x < 945 && dino.y > 280 && dino.y < 370) {
			Log.e("NHK", "Dino x,y: " + String.valueOf(dino.x) + " " + String.valueOf(dino.y) );
			return GO_MAIN;
		}
		
		return 0;
	}
}