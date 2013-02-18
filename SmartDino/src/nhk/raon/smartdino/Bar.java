package nhk.raon.smartdino;

import android.R.color;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bar {
	public Paint pBound = new Paint();
	public int boundBorder;
	public Paint p = new Paint();
	public Paint p2 = new Paint();
	public int x;
	public int y;
	
	public int width;
	public int height;
	
	public int maxValue;
	public int minValue;
	public int currentValue;
	
	public Bar(int x, int y, int width, int height, int minValue, int maxValue, int currentValue) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		pBound.setColor(Color.BLACK);
		p.setColor(Color.BLUE);
		p.setAlpha(255);
		p2.setAlpha(0);
		boundBorder = 2;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.currentValue = currentValue;
	}
	
	public void draw(Canvas c) {
		int n = x+(width-boundBorder*2)*currentValue/(maxValue-minValue);
		c.drawRect(x, y, x+width, y+height, pBound);
		c.drawRect(x+boundBorder, y+boundBorder, n, y+height-boundBorder, p);
		c.drawRect(n, y+boundBorder, x+width-boundBorder, y+height-boundBorder, p2);
	}
}
