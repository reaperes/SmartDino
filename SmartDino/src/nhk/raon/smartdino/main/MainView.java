package nhk.raon.smartdino.main;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.StatusBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Rect;
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

	///////////////////////////////////////////////
	// StatusBar Variables
	public StatusBar statusBar;
	
	///////////////////////////////////////////////
	// Thread Variables
	public Object obj = new Object();
	
	public int threadSleepTime=50;
	
	private MainActivity activity;
	public MainViewThread thread;
	public Dino dino;
	
	private Bitmap background;
	public GraphicObject bathtub;
	public GraphicObject food;
	
	///////////////////////////////////////////////
	// Animation Variables
	private Bitmap btmBath0;
	private Bitmap btmBath1;
	private Bitmap btmSponge;
	private int count=1;
	private Movie movieFood;
	private long movieFoodStart;
	
	// Set to false to use decodeByteArray
	private final boolean DECODE_STREAM = true;

	private byte[] streamToBytes(InputStream is) {
		ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = is.read(buffer)) >= 0) {
				os.write(buffer, 0, len);
			}
		} catch (java.io.IOException e) {
		}
		return os.toByteArray();
	}

	public MainView(Context context, MainActivity activity) {
		super(context);
		this.activity = activity;

		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// load bitmap
		init_0_grahpicObject();
		init_1_animationObject();
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
	public boolean dispatchTouchEvent(MotionEvent event) {
//		Log.e("NHK", "dispatchTouchEvent(MotionEvent event)");
		Log.e("NHK", String.valueOf(State));
		if(event.getAction()==MotionEvent.ACTION_DOWN) {
			if(event.getX() > bathtub.x && event.getX() < (bathtub.x+bathtub.width) && event.getY() > bathtub.y && event.getY() < (bathtub.y+bathtub.height)) {
				stop();
				new AlertDialog.Builder(activity)
    			.setTitle("목욕시키기")
    			.setMessage("목욕 타월이 1개 있습니다. 목욕시키겠습니까?")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
//    					State = STATE_ANIMATION;
    					start();
    				}
    			})
    			.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					start();
    				}
    			})
    			.show();
				
				
			} else if(event.getX() > food.x && event.getX() < (food.x+food.width) && event.getY() > food.y && event.getY() < (food.y+food.height)) {
				stop();
				new AlertDialog.Builder(activity)
    			.setTitle("먹이주기")
    			.setMessage("디노에게 먹이를 주겠습니까?")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
//    					State = STATE_ANIMATION;
    					start();
    				}
    			})
    			.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					start();
    				}
    			})
    			.show();
			}
		}
		
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(State == STATE_ANIMATION) {
			count++;
			Log.e("NHK", "?");
			invalidate();
//			postInvalidate();
			Log.e("NHK", "!");
			return true;
		}
		
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
		
		if(State != STATE_ANIMATION)
			State = STATE_RUNNING;
		
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
		if(State != STATE_ANIMATION)
			return ;
		
//		canvas.drawBitmap(background, 0, 0, null);
//		statusBar.draw(canvas);
		
//		canvas.drawBitmap(background, 0, 0, null);
//		long now = android.os.SystemClock.uptimeMillis();
//		if (movieFoodStart == 0) { // first time
//			movieFoodStart = now;
//		}
//		if (movieFood != null) {
//			int dur = movieFood.duration();
//			if (dur == 0) {
//				dur = 1000;
//			}
//			int relTime = (int) ((now - movieFoodStart) % dur);
//			movieFood.setTime(relTime);
//			movieFood.draw(canvas, getWidth() - movieFood.width(), getHeight() - movieFood.height());
//			invalidate();
//		}
	}

	// the fps to be displayed
	private String avgFps;

	public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}

	public void render(Canvas canvas) {
//		if(State == STATE_ANIMATION) {
//			if(count/5==1)
//				canvas.drawBitmap(btmBath0, null, new Rect(SmartDino.Device_Width/4, SmartDino.Device_Height/4, SmartDino.Device_Width*3/4, SmartDino.Device_Height*3/4), null);
//			else {
//				canvas.drawBitmap(btmBath1, null, new Rect(SmartDino.Device_Width/4, SmartDino.Device_Height/4, SmartDino.Device_Width*3/4, SmartDino.Device_Height*3/4), null);
//				count = 0;
//			}
//			return ;
//		}
		
		canvas.drawBitmap(background, 0, 0, null);
		bathtub.draw(canvas);
		food.draw(canvas);
		dino.draw(canvas);

		// display status
		statusBar.draw(canvas);
		
		// display fps
		displayFps(canvas, avgFps);
	}

	private void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			paint.setTextSize(20);
			canvas.drawText(fps, this.getWidth() - 100, 30, paint);
			canvas.drawText("Thread Sleep Time: "+String.valueOf(threadSleepTime), 10, 30, paint);
			canvas.drawText("Dino speed: "+String.valueOf(dino.getMaxSpeed()), 10, 60, paint);
		}
	}
	
	private void init_0_grahpicObject() {
		// create background
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background_main);
		
		statusBar = new StatusBar(activity);
		
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
	
	private void init_1_animationObject() {
		btmBath0 = BitmapFactory.decodeResource(getResources(), R.drawable.animation_main_bathtub0);
		btmBath1 = BitmapFactory.decodeResource(getResources(), R.drawable.animation_main_bathtub1);
		btmSponge = BitmapFactory.decodeResource(getResources(), R.drawable.image_main_bathtub_sponge);
		
		java.io.InputStream is;
		is = activity.getResources().openRawResource(R.drawable.gif_main_food);

		if (DECODE_STREAM) {
			movieFood = Movie.decodeStream(is);
		} else {
			byte[] array = streamToBytes(is);
			movieFood = Movie.decodeByteArray(array, 0, array.length);
		}
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