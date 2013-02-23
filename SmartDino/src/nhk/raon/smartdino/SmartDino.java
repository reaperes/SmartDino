package nhk.raon.smartdino;

import android.util.DisplayMetrics;

public class SmartDino {
	public static final int DINO_TYPE_0 = 0;
	public static final int DINO_TYPE_1 = DINO_TYPE_0 + 1;
	public static final int DINO_TYPE_2 = DINO_TYPE_0 + 2;
	public static final int DINO_TYPE_3 = DINO_TYPE_0 + 3;
	
	public static int Dino_Type = DINO_TYPE_0;
	
	public static final int DEVICE_TYPE_XHDPI = DisplayMetrics.DENSITY_XHIGH;
	public static final int DEVICE_TYPE_HDPI = DisplayMetrics.DENSITY_HIGH;
	
	public static int Device_Type = DEVICE_TYPE_XHDPI;
	
	public static int Device_Width;
	public static int Device_Height;
	
	////////////////////////////////////////////////////////////////////////////////
	// Record Variables
	public static int Record_Number;
}
