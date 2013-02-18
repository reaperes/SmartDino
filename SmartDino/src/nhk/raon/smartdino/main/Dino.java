package nhk.raon.smartdino.main;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Dino extends GraphicObject {

	private static final int LEFT=0;
	private static final int RIGHT=1;
	private int direction = LEFT;
	
	private Context context;
	
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
	
	public Dino(Context context, int type) {
		this.context = context;
		
		initCharacter(type);
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
			
//			Log.e("NHK", "Dino XY: " + String.valueOf(x) + " " + String.valueOf(y));
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (isMoving) {
			if(dx>0)
				canvas.drawBitmap(rightBitmap[animationCount], x - (rightBitmap[animationCount].getWidth() / 2), y - (rightBitmap[animationCount].getHeight() / 2), null);
			else
				canvas.drawBitmap(leftBitmap[animationCount], x - (leftBitmap[animationCount].getWidth() / 2), y - (leftBitmap[animationCount].getHeight() / 2), null);
		} else {
			if (direction == RIGHT)
				canvas.drawBitmap(rightBitmap[animationCount], x - (rightBitmap[animationCount].getWidth() / 2), y - (rightBitmap[animationCount].getHeight() / 2), null);
			else
				canvas.drawBitmap(leftBitmap[animationCount], x - (leftBitmap[animationCount].getWidth() / 2), y - (leftBitmap[animationCount].getHeight() / 2), null);
		}
	}
	
	public void setDstXY(int x, int y) {
		dst_x = x;
		dst_y = y;
		
		float gap_x = dst_x-this.x;
		float gap_y = dst_y-this.y;
		float gap_xy = (float) Math.sqrt(Math.pow(gap_x,2)+Math.pow(gap_y, 2));
		
		dx = maxSpeed * gap_x / gap_xy;
		dy = maxSpeed * gap_y / gap_xy;
		
		if(dx>0)
			direction=RIGHT;
		else
			direction=LEFT;
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
	
	private void initCharacter(int type) {
		switch(type) {
		case SmartDino.DINO_TYPE_0:
			setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.char0));
			leftBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char0_left0);
			leftBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char0_left1);
			rightBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char0_right0);
			rightBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char0_right1);
			break;
			
		case SmartDino.DINO_TYPE_1:
			setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.char1));
			leftBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char1_left0);
			leftBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char1_left1);
			rightBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char1_right0);
			rightBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char1_right1);
			break;
		
		case SmartDino.DINO_TYPE_2:
			setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.char2));
			leftBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char2_left0);
			leftBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char2_left1);
			rightBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char2_right0);
			rightBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char2_right1);
			break;

		case SmartDino.DINO_TYPE_3:
			setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.char3));
			leftBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char3_left0);
			leftBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char3_left1);
			rightBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char3_right0);
			rightBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.animation_char3_right1);
			break;
		}
	}
}
