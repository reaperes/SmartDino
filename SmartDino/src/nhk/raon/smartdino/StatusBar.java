package nhk.raon.smartdino;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;

public class StatusBar {
	private Context context;
	public int width = SmartDino.Device_Width;
	public int height = SmartDino.Device_Height/10; 

	// Level
	private Paint paintLevel = new Paint();
	private Bitmap btmLevel;
	private int xLevel;
	private int x2Level;
	private final int yLevel = 10;
	private final int y2Level = 63;
	private int yP;
	
	// Money
	private Paint paintMoney = new Paint();
	private Bitmap btmMoney;
	private int xMoney;
	private int x2Money;
	
	// Food
	private Bitmap btmFood;
	private int xFood;
	private int x2Food;
	
	// Hunger
	private Bitmap btmHunger;
	private Bitmap btmHungerBar;
	private int xHunger;
	private int x2Hunger;
	
	// Clean
	private Bitmap btmClean;
	private Bitmap btmCleanBar;
	private int xClean;
	private int x2Clean;
	private int x3Clean;
	
	// Settings
	private Bitmap btmSettings;
	private int xSettings;
	private int x2Settings;
	
	public StatusBar(Context context) {
		this.context = context;
		
		// level
		paintLevel.setTextSize(height/2);
		paintLevel.setARGB(255, 0, 0, 0);
		btmLevel = BitmapFactory.decodeResource(context.getResources(), R.drawable.status_exp);
		xLevel = width*80/1280;
		x2Level = width*353/1280;
		yP = height-yLevel-8;
		
		// money
		paintMoney.setTextSize(height/2);
		paintMoney.setARGB(255, 0, 0, 0);
		btmMoney = BitmapFactory.decodeResource(context.getResources(), R.drawable.status_money);
		xMoney = width*360/1280;
		x2Money = width*440/1280;
		
		// food
		btmFood = BitmapFactory.decodeResource(context.getResources(), R.drawable.status_food);
		xFood = width*576/1280;
		x2Food = width*652/1280;
		
		// Hunger
		btmHunger = BitmapFactory.decodeResource(context.getResources(), R.drawable.status_hunger_0);
		btmHungerBar = BitmapFactory.decodeResource(context.getResources(), R.drawable.status_bar);
		xHunger = width*788/1280;
		x2Hunger = width*848/1280;
		
		// Clean
		btmClean = BitmapFactory.decodeResource(context.getResources(), R.drawable.status_clean);
		btmCleanBar = btmHungerBar;
		xClean = width*1008/1280;
		x2Clean = width*1070/1280;
		x3Clean = width*1220/1280;
		
		// Settings
		btmSettings = BitmapFactory.decodeResource(context.getResources(), R.drawable.settings);
		xSettings = width*1225/1280;
		x2Settings = width*1275/1280;
	}
	
	public void draw(Canvas c) {
		Paint p = new Paint();
		p.setARGB(80, 255, 255, 255);
		c.drawRect(new Rect(0, 0, width, height), p);
		// Level
		c.drawText("Lv.1", 10, yP, paintLevel);
		c.drawBitmap(btmLevel, null, new Rect(xLevel, yLevel, x2Level, y2Level), null);
		// money
		c.drawBitmap(btmMoney, null, new Rect(xMoney, yLevel, xFood-5, y2Level), null);
		c.drawText("100", x2Money, yP, paintMoney);
		// food
		c.drawBitmap(btmFood, null, new Rect(xFood, yLevel, xHunger-5, y2Level), null);
		c.drawText("100", x2Food, yP, paintMoney);
		// Hunger
		c.drawBitmap(btmHunger, null, new Rect(xHunger, yLevel, x2Hunger-5, y2Level), null);
		c.drawBitmap(btmHungerBar, null, new Rect(x2Hunger, yLevel, xClean-5, y2Level), null);
		// Clean
		c.drawBitmap(btmClean, null, new Rect(xClean, yLevel, x2Clean-5, y2Level), null);
		c.drawBitmap(btmCleanBar, null, new Rect(x2Clean, yLevel, x3Clean, y2Level), null);
		// Settings
		c.drawBitmap(btmSettings, null, new Rect(xSettings, yLevel, x2Settings, y2Level), null);
	}
}
