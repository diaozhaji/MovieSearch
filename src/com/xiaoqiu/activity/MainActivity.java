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
 * @author tianqiujie  �������
 */

public class MainActivity extends ListActivity {
	
	final static String TAG = "m_debug";
	final static int LOAD_DATA = 1;

	private ListView mlistView;// ��ȡ������Ϣ���б��ؼ�

	private InfoAdapter myAdapater;// ��������BaseAdapater
	
	private ImageView search_button;// ������ť
	public EditText editText;// ������
	public List<SingleEntity> aList;// MovieBriefPojo ���صķ���LIST
	
	SingleEntity mbp;// ���ݵ���¼��� ����

	public Spinner sp;// ѡ��Ҫ����������
	
	ProgressDialog proDialog;

	String[] types = { "�ѵ�Ӱ" };

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
		proDialog.setMessage("�������ĵȴ�...");
	}
	
	Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == LOAD_DATA)
			{
				String name = editText.getText().toString();
				String condition = "�ѵ�Ӱ";
				try {
					if (name.trim().length() < 1) {
						openOptionDialog("��������");
						return;
					} else {
						if (condition.contains("��Ӱ")) {
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
		// ����
		search_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				proDialog.show();
				mHandler.sendEmptyMessage(LOAD_DATA);
			}
		});
		
	}

	/**
	 * @author tianqiujie  �����Զ��岼����InfoAdapter
	 */
	
	public void showall() {
		mlistView = getListView();
		
		myAdapater = new InfoAdapter(this, mlistView);
		mlistView.setAdapter(myAdapater);
		mlistView.setOnScrollListener(mScrollListener);
		
		proDialog.dismiss();
	}
	
	/**
	 * @author tianqiujie  ��ת������Ӱ�Ľ��� ����ͼƬ���Ӻ͵������ֵ
	 */
	
	protected void onListItemClick(ListView l, View view, int position, long id) {
		mbp = aList.get(position);
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, DetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("url", mbp.getFirstUrl().toString());
		bundle.putString("imageurl", mbp.getImageUrl().toString());
		bundle.putString("type", "��Ӱ");
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * @author tianqiujie
	 * ��listView����ֹͣ�Ժ�ſ�ʼ�첽����ͼƬ
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
	
	// ��ʾ�򷽷�
		public void openOptionDialog(String string) {
			new AlertDialog.Builder(this).setTitle("��ʾ")
					.setMessage(string + "����Ϊ��!")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();
		}
}