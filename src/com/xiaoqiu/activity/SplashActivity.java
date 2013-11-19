package com.xiaoqiu.activity;


import com.xiaoqiu.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class SplashActivity extends Activity{
	private ImageView imageview;
	private TextView tv;
	private int displayWidth;
	private int displayHeight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//取得屏幕分辨率，以在特效播放中使用
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        displayWidth=dm.widthPixels;
        displayHeight=dm.heightPixels;
        float density = dm.density;
        System.out.println(displayHeight);
        System.out.println(displayWidth);
        System.out.println(density);
        
        setContentView(R.layout.splash);
		tv = (TextView)findViewById(R.id.splash_text);
        tv.setText("your screen height = "+displayHeight +"\n"
        			+"width = "+displayWidth+"\n"
        			+"density = "+density);
        
        imageview = (ImageView)findViewById(R.id.splash_img);
		
		imageview.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
    			intent.setClass(SplashActivity.this,MainActivity.class);
    			startActivity(intent);          		
    			SplashActivity.this.finish();
			}
			
		});
				
	}
	

}
