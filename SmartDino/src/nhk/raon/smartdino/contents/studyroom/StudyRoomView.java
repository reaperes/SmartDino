package nhk.raon.smartdino.contents.studyroom;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.StatusBar;
import nhk.raon.smartdino.main.Dino;
import nhk.raon.smartdino.main.GraphicObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class StudyRoomView extends SurfaceView implements SurfaceHolder.Callback {
	
	public static final int STATE_INIT			= 0;
	public static final int STATE_RUNNING		= STATE_INIT + 1;
	
	public int State = STATE_INIT;
	
//	public static final int GO_CONTENTS_STUDYROOM = 1;
//	public static final int GO_MAIN2 = GO_CONTENTS_STUDYROOM + 1;
//
	///////////////////////////////////////////////
	// StatusBar Variables
	public StatusBar statusBar;
	
	///////////////////////////////////////////////
	// Thread Variables
	public Object obj = new Object();
	
	public int threadSleepTime=50;
	
	private StudyRoomActivity activity;
	public StudyRoomViewThread thread;
	public Dino dino;
	
	private Bitmap background;
	public GraphicObject memory;
	public GraphicObject knowledge;
	public GraphicObject english;
	public GraphicObject math;
	
	public StudyRoomView(Context context, StudyRoomActivity activity) {
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
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e("NHK", "surfaceDestroyed");
		stop();
	}
	
	public void update() {
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
//		if(event.getAction()==MotionEvent.ACTION_DOWN) {
//			if(event.getX() > bathtub.x && event.getX() < (bathtub.x+bathtub.width) && event.getY() > bathtub.y && event.getY() < (bathtub.y+bathtub.height)) {
//				stop();
//				new AlertDialog.Builder(activity)
//    			.setTitle("목욕시키기")
//    			.setMessage("목욕 타월이 1개 있습니다. 목욕시키겠습니까?")
//    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
//    				@Override
//    				public void onClick(DialogInterface dialog, int which) {
////    					State = STATE_ANIMATION;
//    					start();
//    				}
//    			})
//    			.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//    				@Override
//    				public void onClick(DialogInterface dialog, int which) {
//    					start();
//    				}
//    			})
//    			.show();
//				
//				
//			} else if(event.getX() > food.x && event.getX() < (food.x+food.width) && event.getY() > food.y && event.getY() < (food.y+food.height)) {
//				stop();
//				new AlertDialog.Builder(activity)
//    			.setTitle("먹이주기")
//    			.setMessage("디노에게 먹이를 주겠습니까?")
//    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
//    				@Override
//    				public void onClick(DialogInterface dialog, int which) {
////    					State = STATE_ANIMATION;
//    					start();
//    				}
//    			})
//    			.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//    				@Override
//    				public void onClick(DialogInterface dialog, int which) {
//    					start();
//    				}
//    			})
//    			.show();
//			}
//		}
		
		return super.dispatchTouchEvent(event);
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
		thread = StudyRoomViewThread.getThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		
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
	}

	// the fps to be displayed
	private String avgFps;

	public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}

	public void render(Canvas canvas) {
		canvas.drawBitmap(background, 0, 0, null);
		memory.draw(canvas);
		knowledge.draw(canvas);
		english.draw(canvas);
		math.draw(canvas);

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
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background_contents_studyroom);
		
		statusBar = new StatusBar(activity);
		
		// create dino
		dino = new Dino(activity, SmartDino.Dino_Type);
		
		// create Graphic Objects
		memory = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_memory));
		knowledge = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_knowledge));
		english = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_english));
		math = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_math));

		// set layout
		int width = SmartDino.Device_Width;
		int height = SmartDino.Device_Height;
		dino.setXY(width/2, height/2);
		memory.setXY((float)0, (float)0);
		knowledge.setXY((float)memory.width*66/100, (float)0);
		english.setXY((float)width/5, (float)height*3/5);
		math.setXY((float)width*2/3, (float)height*3/5);
	}
}