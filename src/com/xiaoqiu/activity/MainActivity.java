package com.xiaoqiu.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.xiaoqiu.loader.SimpleInfoLoder;
import com.xiaoqiu.main.R;
import com.xiaoqiu.adapder.InfoAdapter;
import com.xiaoqiu.entity.SingleEntity;

/**
 * 
 * @author tianqiujie  程序入口
 */

public class MainActivity extends ListActivity {
	
	final static String TAG = "m_debug";
	final static int LOAD_DATA = 1;

	private ListView mlistView;// 存取搜索信息的列表控件

	private InfoAdapter myAdapater;// 用来加载BaseAdapater
	
	private ImageView search_button;// 搜索按钮
	public EditText editText;// 搜索框
	public List<SingleEntity> aList;// MovieBriefPojo 返回的泛型LIST
	
	SingleEntity mbp;// 传递点击事件的 击点

	public Spinner sp;// 选择要搜索的类型
	
	ProgressDialog proDialog;

	String[] types = { "搜电影" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		loading();
	}
	
	private void initView() {
		mbp = new SingleEntity();

		search_button = (ImageView) findViewById(R.id.search_button);
		editText = (EditText) findViewById(R.id.edittext);

		initClick();
	}
	
	void loading()
	{
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("请您耐心等待...");
	}
	
	Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == LOAD_DATA)
			{
				String name = editText.getText().toString();
				String condition = "搜电影";
				try {
					if (name.trim().length() < 1) {
						openOptionDialog("搜索条件");
						return;
					} else {
						if (condition.contains("电影")) {
							aList = new SimpleInfoLoder().findXml(name);
						}
						showall();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			super.handleMessage(msg);
		}
		
	};
	
	private void initClick() {
		// 搜索
		search_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				proDialog.show();
				mHandler.sendEmptyMessage(LOAD_DATA);
			}
		});
		
	}

	/**
	 * @author tianqiujie  添加自定义布局器InfoAdapter
	 */
	
	public void showall() {
		mlistView = getListView();
		
		myAdapater = new InfoAdapter(this, mlistView);
		mlistView.setAdapter(myAdapater);
		mlistView.setOnScrollListener(mScrollListener);
		
		proDialog.dismiss();
	}
	
	/**
	 * @author tianqiujie  跳转单个电影的界面 传入图片链接和点击索引值
	 */
	
	protected void onListItemClick(ListView l, View view, int position, long id) {
		mbp = aList.get(position);
		Intent intent = new Intent();
		//intent.setClass(MainActivity.this, DetailActivity.class);
		intent.setClass(MainActivity.this, MovieDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("id", mbp.getFirstUrl().toString());
		bundle.putString("imageurl", mbp.getImageUrl().toString());
		//bundle.putString("type", "电影");
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * @author tianqiujie
	 * 当listView滚动停止以后才开始异步加载图片
	 */
	
	OnScrollListener mScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				myAdapater.setFlagBusy(true);
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				myAdapater.setFlagBusy(false);
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				myAdapater.setFlagBusy(false);
				break;
			default:
				break;
			}
			myAdapater.notifyDataSetChanged();
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}
	};
	
	// 提示框方法
		public void openOptionDialog(String string) {
			new AlertDialog.Builder(this).setTitle("提示")
					.setMessage(string + "不能为空!")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();
		}
}