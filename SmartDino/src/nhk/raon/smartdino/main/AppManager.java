package nhk.raon.smartdino.main;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class AppManager {

	private static AppManager _instance;
	
	private MainView m_mainview;
	private Resources m_resources;
	private Activity m_activity;
	
	private int m_width = 0;
	private int m_height = 0;
	
	public static AppManager getInstance() {
		if(_instance == null){
			_instance = new AppManager();
		}
		return _instance;
	}
	
	public void setGameView(MainView mainview) {
		m_mainview = mainview;
	}
	
	public void setResources(Resources resources) {
		m_resources = resources;
	}
	
	public void setActivity(Activity activity) {
		m_activity = activity;
	}	
	
	public Bitmap getBitmap(int r) {
		return BitmapFactory.decodeResource(m_resources, r);
	}
}
