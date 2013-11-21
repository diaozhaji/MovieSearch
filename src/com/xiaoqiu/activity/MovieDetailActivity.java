package com.xiaoqiu.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoqiu.activity.DetailActivity.LoadData;
import com.xiaoqiu.entity.MovieDetailEntity;
import com.xiaoqiu.entity.MovieDetaileEntityOld;
import com.xiaoqiu.loader.DetailedInfoLoder;
import com.xiaoqiu.loader.MovieDetailInfoLoader;
import com.xiaoqiu.main.R;


public class MovieDetailActivity extends Activity{
	final static String TAG = "MovieDetailActivity";
	
	private String url;
	private String imageUrl;
	private ProgressDialog proDialog;
	
	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;
	//Views
	private TextView titleView;
	private TextView summaryView;
	private ImageView image;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity_test);
		
		initView();
		
		
		try {
			initData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		new Thread(new LoadData()).start();
		proDialog.show();
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		titleView = (TextView)findViewById(R.id.layout_title);
		image = (ImageView)findViewById(R.id.detail_activity_img);
		summaryView = (TextView)findViewById(R.id.detail_summary);
		
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("ÇëÄúÄÍÐÄµÈ´ý...");	
		
	}


	private void initData() throws IOException {
		//Bundle bundle = getIntent().getExtras();		
		//String id = bundle.getString("url");
		//imageUrl = bundle.getString("imageurl");
		String id = "3541415";
		imageUrl = "http://img3.douban.com/mpic/s4356687.jpg";
		
		url = "http://192.158.31.250/search/"+id+"/";
		
		Log.d(TAG, imageUrl);
		Log.d(TAG, url);
		
		
		movieInfo = new MovieDetailInfoLoader();
	}
	
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {
			
			titleView.setText(mMovie.getTitle());	
			
			try{
				String summary = mMovie.getSummary();
				int maxLen = 220;
				if (summary.length() > maxLen) {
					summaryView.setText("\t"+summary.substring(0, maxLen) + "...");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				URL aryURI = new URL(mMovie.getImage_medium());
				InputStream is = aryURI.openStream();
				Bitmap bm = BitmapFactory.decodeStream(is);
				if (bm == null) {
					image.setBackgroundColor(R.drawable.detail_img);
				}
				is.close();
				image.setImageBitmap(bm);
			} catch (Exception e) {
				// TODO: handle exception
			}
			proDialog.dismiss();
		}
	};

	
	
	class LoadData implements Runnable {

		@Override
		public void run() {
			int choice = 0;
			Log.d(TAG, "run()");
			try {
				mMovie = movieInfo.parserMovieJson(url);
					
				mHandler.sendEmptyMessage(choice);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
