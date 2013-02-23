package nhk.raon.smartdino.contents.studyroom;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import nhk.raon.smartdino.StatusBar;
import nhk.raon.smartdino.main.Dino;
import nhk.raon.smartdino.main.GraphicObject;
import nhk.raon.smartdino.main.MainViewThread;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

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
	private boolean isMemoryOn;
	private boolean isKnowledgeOn;
	private boolean isEnglishOn;
	private boolean isMathOn;
	
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

		isMemoryOn = false;
		isKnowledgeOn = false;
		isEnglishOn = false;
		isMathOn = false;
		start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e("NHK", "surfaceDestroyed");
		stop();
	}
	
	public void update() {
	}

	private boolean checkItem(GraphicObject image, MotionEvent event) {
		if(event.getX() > image.x && event.getX() < (image.x+image.width) && event.getY() > image.y && event.getY() < (image.y+image.height))
			return true;
		return false;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN) {
			if(checkItem(memory, event)) {
				if(!isMemoryOn) {
					setHighlighted(memory);
					return true;
				}
				stop();
				
				new AlertDialog.Builder(activity)
    			.setTitle("no data")
    			.setMessage("no data")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
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
				
				
			} else if(checkItem(knowledge, event)) {
				if(!isKnowledgeOn) {
					setHighlighted(knowledge);
					return true;
				}
				stop();
				new AlertDialog.Builder(activity)
    			.setTitle("no data")
    			.setMessage("no data")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
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
				
			} else if(checkItem(english, event)) {
				if(!isEnglishOn) {
					setHighlighted(english);
					return true;
				}
				stop();
				
				new AlertDialog.Builder(activity)
    			.setTitle("no data")
    			.setMessage("no data")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
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
				
			} else if(checkItem(math, event)) {
				if(!isMathOn) {
					setHighlighted(math);
					return true;
				}
				stop();
				
				new AlertDialog.Builder(activity)
//    			.setTitle("no data")
    			.setMessage("숫자 놀이를 할까요?")
    			.setPositiveButton("네", new DialogInterface.OnClickListener() {
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					activity.moveActivity(StudyRoomActivity.MATH);
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
		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
			dino.handleActionDown((int) event.getX(), (int) event.getY());
		
		// for battery saving
		try {
			Thread.sleep(30);
		} catch(Exception e) {}

		return true;
	}
	
	public void start() {
		Log.e("NHK", "start()");
		stop();

		// create the game loop thread
		thread = StudyRoomViewThread.getThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);

		Log.e("NHK", "Thread Start");
		thread.setRunning(true);
		thread.start();
	}
	
	public void stop() {
		Log.e("NHK", "stop()");
		if(thread==null) {
			Log.e("NHK", "stopping failed");
			return ;
		}
		
		Log.e("NHK", "stopping success");
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
		memory.draw(canvas, isMemoryOn);
		knowledge.draw(canvas, isKnowledgeOn);
		english.draw(canvas, isEnglishOn);
		math.draw(canvas, isMathOn);

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
	
	private void setHighlighted(GraphicObject obj) {
		if (obj == memory) {
			isMemoryOn = true;
			isKnowledgeOn = false;
			isEnglishOn = false;
			isMathOn = false;
		} else if (obj == knowledge) {
			isMemoryOn = false;
			isKnowledgeOn = true;
			isEnglishOn = false;
			isMathOn = false;
		} else if (obj == english) {
			isMemoryOn = false;
			isKnowledgeOn = false;
			isEnglishOn = true;
			isMathOn = false;
		} else if (obj == math) {
			isMemoryOn = false;
			isKnowledgeOn = false;
			isEnglishOn = false;
			isMathOn = true;
		}
	}
	
	private void init_0_grahpicObject() {
		// create background
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background_contents_studyroom);
		
		statusBar = new StatusBar(activity);
		
		// create dino
		dino = new Dino(activity, SmartDino.Dino_Type);
		
		// create Graphic Objects
		memory = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_memory), BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_memory_highlighted));
		knowledge = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_knowledge), BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_knowledge_highlighted));
		english = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_english), BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_english_highlighted));
		math = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_math), BitmapFactory.decodeResource(getResources(), R.drawable.item_contents_studyroom_math_highlighted));

		// set layout
		int width = SmartDino.Device_Width;
		int height = SmartDino.Device_Height;
		dino.setXY(width/2, height/2);
		memory.setXY((float)20, (float)0);
		knowledge.setXY((float)(memory.width+20)*64/100, (float)0);
		english.setXY((float)width/5, (float)height*3/5);
		math.setXY((float)width*2/3, (float)height*3/5);
	}
}