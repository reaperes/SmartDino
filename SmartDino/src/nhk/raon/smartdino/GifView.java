package nhk.raon.smartdino;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View {
	private Context context;
	private Movie mMovie;
	private long mMovieStart;

	public GifView(Context context) {
	    super(context);
	    this.context = context;
	    setGifResource(0);
	    setFocusable(true);
	}

	public GifView(Context context, AttributeSet attrSet) {
	    super(context, attrSet);
	    this.context = context;
	    setGifResource(0);
	    setFocusable(true);
	}

	public GifView(Context context, AttributeSet attrSet, int defStyle) {
	    super(context, attrSet, defStyle);
	    this.context = context;
	    setGifResource(0);
	    setFocusable(true);
	}
	
	public void setGifResource(int id) {
		if(id==0)
			return ;
		
	    java.io.InputStream is;
	    is = context.getResources().openRawResource(id);
	    mMovie = Movie.decodeStream(is);
	}

	@Override
	protected void onDraw(Canvas canvas) {
	    canvas.drawColor(0x00000000);

	    Paint p = new Paint();
	    p.setAntiAlias(true);

	    long now = android.os.SystemClock.uptimeMillis();
	    if (mMovieStart == 0) { // first time
	        mMovieStart = now;
	    }
	    if (mMovie != null) {
	        int dur = mMovie.duration();
	        if (dur == 0) {
	            dur = 1000;
	        }
	        int relTime = (int) ((now - mMovieStart) % dur);
	        mMovie.setTime(relTime);
	        mMovie.draw(canvas, getWidth() / 2 - mMovie.width() / 2,
	                getHeight() / 2 - mMovie.height() / 2);
	        invalidate();
	    }
	}
}
