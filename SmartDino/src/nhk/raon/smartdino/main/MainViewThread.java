package nhk.raon.smartdino.main;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainViewThread extends Thread {
	
	private SurfaceHolder _surfaceHolder;
    private MainView _mainview;
    private boolean _run = false;
    
    public MainViewThread(SurfaceHolder surfaceHolder, MainView mainview) {
        _surfaceHolder = surfaceHolder;
        _mainview = mainview;
    }
    
    public void setRunning(boolean run) {
        _run = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (_run) {
        	
        	_mainview.Update();
        	
            c = null;
            try {
                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {
                	_mainview.onDraw(c);
                }
            } finally {
                if (c != null) {
                    _surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
