package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

	private final boolean FPS = true;
	
	private MainViewThread _thread;
	
	public MainView(Context context) {
		super(context);

    	AppManager.getInstance().setGameView(this);
    	AppManager.getInstance().setResources(getResources());
		
    	Log.e("NHK", "width, height : " + String.valueOf(getWidth()) + " " + String.valueOf(getHeight()));
    	
		getHolder().addCallback(this);
		_thread = new MainViewThread(getHolder(),this);
	}
	
    @Override
    public void onDraw(Canvas canvas) {
    	canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.background_main), 0f, 0f, null);
    	
    	if(FPS) {
//    		canvas.drawText("fps: ", 5f, 5f, null);
    	}
    }
    
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		_thread.setRunning(true);
	    _thread.start();
	}
	
	public void Update() {
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder s) {
		boolean retry = true;
	    _thread.setRunning(false);
	    while (retry) {
	        try {
	            _thread.join();
	            retry = false;
	        } catch (InterruptedException e) {
	        }
	    }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return true;
	}
}
