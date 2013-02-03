package nhk.raon.smartdino.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {

	protected Bitmap 	bitmap;
	public float		x;
	public float		y;

	public GraphicObject(Bitmap bitmap){
		this.bitmap = bitmap;
		this.x = 0;
		this.y = 0;
	}

	public void setXY(float x, float y){
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}
}