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

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

	public Object obj = new Object();
	
	private MainActivity activity;
	public MainViewThread thread;
	private Dino dino;
	
	private Bitmap background;
	private GraphicObject bathtub;
	private GraphicObject food;

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
		thread = MainViewThread.getThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		
		thread.setRunning(true);
		thread.start();
	}
	
	public void stop() {
		if(thread==null) {
			Log.e("NHK", "here?");
			return ;
		}
			
		thread.setRunning(false);
		synchronized(this) {
			this.notify();
		}
		Log.d("NHK", "Thread was shut down cleanly");
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
		Bitmap[] leftBitmap = new Bitmap[2];
		Bitmap[] rightBitmap = new Bitmap[2];
		leftBitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_left0);
		leftBitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_left1);
		rightBitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_right0);
		rightBitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.animation_char_dino_right1);
		dino = new Dino(BitmapFactory.decodeResource(getResources(), R.drawable.image_dino), leftBitmap, rightBitmap);
		
		// create Graphic Objects
		bathtub = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_main_bathtub));
		food = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_main_food));
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		dino.setXY(displayMetrics.widthPixels/2, displayMetrics.heightPixels/2);
		bathtub.setXY((float)(displayMetrics.widthPixels*0.1), (float)(displayMetrics.heightPixels*0.5));
		food.setXY((float)(displayMetrics.widthPixels*0.75), (float)(displayMetrics.heightPixels*0.66));
	}
}
