package nhk.raon.smartdino.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Dino extends GraphicObject {

	private Bitmap[] leftBitmap = new Bitmap[2];
	private Bitmap[] rightBitmap = new Bitmap[2];
	private int animationCount;
	
	private float dst_x;
	private float dst_y;
	
	private float dx;
	private float dy;
//	private float maxSpeed = 10f;
	private float maxSpeed = 20f;
	private boolean isMoving;
	
	public Dino(Bitmap bitmap, Bitmap[] leftBitmap, Bitmap[] rightBitmap) {
		super(bitmap);
		
		this.leftBitmap = leftBitmap;
		this.rightBitmap = rightBitmap;
	}
	
	public void update() {
		if(isMoving) {
			if ( Math.abs(dst_x-x) > maxSpeed ) {
				x += dx;
			} else {
				x = dst_x;
				dx = 0;
			}
			
			if ( Math.abs(dst_y-y) > maxSpeed ) {
				y += dy;
			} else {
				y = dst_y;
				dy = 0;
			}
			
			if ((dx==0) && (dy==0)) {
				isMoving=false;
			}
		
			if(++animationCount > 1)
				animationCount = 0;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(dx>0)
			canvas.drawBitmap(rightBitmap[animationCount], x - (rightBitmap[animationCount].getWidth() / 2), y - (rightBitmap[animationCount].getHeight() / 2), null);
		else
			canvas.drawBitmap(leftBitmap[animationCount], x - (leftBitmap[animationCount].getWidth() / 2), y - (leftBitmap[animationCount].getHeight() / 2), null);
	}
	
	public void setDstXY(int x, int y) {
		dst_x = x;
		dst_y = y;
		
		float gap_x = dst_x-this.x;
		float gap_y = dst_y-this.y;
		float gap_xy = (float) Math.sqrt(Math.pow(gap_x,2)+Math.pow(gap_y, 2));
		
		dx = maxSpeed * gap_x / gap_xy;
		dy = maxSpeed * gap_y / gap_xy;
		
		isMoving = true;
	}
	
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	
	public void handleActionDown(int eventX, int eventY) {
		if ( (eventX < (x - bitmap.getWidth()/2)) || (eventX > (x + bitmap.getWidth()/2)) || (eventY < (y - bitmap.getHeight()/2)) || (eventY > (y + bitmap.getHeight()/2)))
			setDstXY(eventX, eventY);
		else
			isMoving = false;
	}
}
