package nhk.raon.smartdino.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {

	protected Bitmap 	bitmap;
	public int			width;
	public int			height;
	public float		x;
	public float		y;

	public GraphicObject() {
	}
	
	public GraphicObject(Bitmap bitmap) {
		setBitmap(bitmap);
//		this.x = 0;
//		this.y = 0;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		setWH();
	}
	
	public void setWH() {
		if(bitmap == null) {
			width = 0;
			height = 0;
			return ;
		}
		
		width = bitmap.getWidth();
		height = bitmap.getHeight();
	}

	public void setXY(float x, float y){
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}
}