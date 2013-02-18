package nhk.raon.smartdino.contents.shop;

import nhk.raon.smartdino.R;
import nhk.raon.smartdino.SmartDino;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ShopActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_shop);
        
        LinearLayout sv = (LinearLayout) findViewById(R.id.layout_contents_shop_scrollview);
        
        ImageView[] iv = new ImageView[20];

        for(int i=0; i<20; i++) {
			iv[i] = new ImageView(this);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			iv[i].setLayoutParams(lp);
			iv[i].setImageResource(R.drawable.item_contents_shop_item);
			iv[i].setPadding(5, 5, 5, 5);
			sv.addView(iv[i]);
		}
        
        initLayout();
	}
	
	private void initLayout() {
		ImageView iv = (ImageView) findViewById(R.id.image_contents_shop_dino);
		
		switch(SmartDino.Dino_Type) {
		case SmartDino.DINO_TYPE_0:
			iv.setImageResource(R.drawable.char0);
			break;
		case SmartDino.DINO_TYPE_1:
			iv.setImageResource(R.drawable.char1);
			break;
		case SmartDino.DINO_TYPE_2:
			iv.setImageResource(R.drawable.char2);
			break;
		case SmartDino.DINO_TYPE_3:
			iv.setImageResource(R.drawable.char3);
			break;
		}
	}
}
