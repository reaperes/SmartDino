package nhk.raon.smartdino.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {

	protected Bitmap 	_bitmap;
	protected int		_x;
	protected int		_y;

	public GraphicObject(Bitmap bitmap){
		_bitmap = bitmap;
		_x = 0;
		_y = 0;
	}

	public void SetPosition(int x,int y){
		_x = x;
		_y = y;
	}

	public void Draw(Canvas canvas){
		canvas.drawBitmap(_bitmap,_x,_y,null);
	}
	public int GetX(){
		return _x;
	}
	public int GetY(){
		return _y;
	}
}
