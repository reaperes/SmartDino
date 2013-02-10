package nhk.raon.smartdino.main;

import nhk.raon.smartdino.contents.catchfruits.CatchFruitsActivity;
import nhk.raon.smartdino.contents.jungle.JungleActivity;
import nhk.raon.smartdino.contents.shop.ShopActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main2Activity extends Activity {
	
	private Main2View main2View;
	private boolean isMoving=false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        main2View = new Main2View(this, Main2Activity.this);

		setContentView(main2View);
	}
	
	@Override
	public void onStop() {
		main2View.stop();
		
		super.onStop();
	}
	
	public void moveActivity(int n) {
		switch(n) {
		case Main2View.GO_MAIN:
			if(!isMoving) {
	    		Intent intent = new Intent().setClass(Main2Activity.this, MainActivity.class);
	    		startActivity(intent);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
    		
		case Main2View.GO_CONTENTS_CATCHFRUITS:
			if(!isMoving) {
	    		Intent intent2 = new Intent().setClass(Main2Activity.this, CatchFruitsActivity.class);
	    		startActivity(intent2);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
			
		case Main2View.GO_CONTENTS_JUNGLE:
			if(!isMoving) {
	    		Intent intent2 = new Intent().setClass(Main2Activity.this, JungleActivity.class);
	    		startActivity(intent2);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
			
		case Main2View.GO_CONTENTS_SHOP:
			if(!isMoving) {
	    		Intent intent2 = new Intent().setClass(Main2Activity.this, ShopActivity.class);
	    		startActivity(intent2);
	    		isMoving = true;
	    		finish();
	    		return ;
			}
		}
	}
}
