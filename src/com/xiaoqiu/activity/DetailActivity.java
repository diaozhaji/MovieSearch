package com.xiaoqiu.activity;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoqiu.loader.DetailedInfoLoder;
import com.xiaoqiu.main.R;
import com.xiaoqiu.utils.DebugUtil;
import com.xiaoqiu.entity.MovieDetaileEntity;

/**
 * 
 * @author tianqiujie ������ϸ��Ϣ��ʾ��ҳ��
 * 
 */

public class DetailActivity extends Activity {
	private final static int LOAD_BOOK = 1;
	private final static int LOAD_MUSIC = 2;
	private final static int LOAD_MOVIE = 3;

	private TextView singleTextView;// ��ʾ ���� ���ݵ���Ϣ�� �ı��ؼ�
	private TextView singletext;// ��ʾ���ݼ����ı��ؼ�
	private ImageView imageView;// ��ʾͼƬ��ͼƬ�ؼ�
	//private Button button;// "���� "��ť
	private List<MovieDetaileEntity> moiveList; // ��Ӱ������Ϣ�ķ���LIST
	private ProgressDialog proDialog;

	private String imageUrl;// ͼƬ�ĵ�ַ
	private String url;// Entity�ľ����ַ

	private DetailedInfoLoder detailedInfo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_show);
		initView();
		initData();
		addListener();
		
		new Thread(new LoadData()).start();
		proDialog.show();
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message message) {
			int value = message.what;
			switch (value) {
			case LOAD_MOVIE:
				for (MovieDetaileEntity m : moiveList) {
					if (m.getWebSite() == null) {// ��ַΪ��ʱ: Ϊ "����"
						m.setWebSite("����");
					}
					if (m.getWriter() == null) {// ���Ϊ��ʱ: Ϊ"����"
						m.setWriter("����");
					}
					if (m.getAuthor() == null) {// ����Ϊ��ʱ:Ϊ"����"
						m.setAuthor("����");
					}

					singleTextView.setText("Ӱ��:" + m.getTitle() + "\n����:"
							+ m.getAuthor() + "\n���:" + m.getWriter() + ".."
							+ "\n�ٷ���վ:" + m.getWebSite());
					String summary = m.getSummary();
					if (summary.length() > 264) {
						singletext.setText("��飺\n" + summary.substring(0, 264) + "...");
					}
					
					try {
						URL aryURI = new URL(m.getImageUrl());
						InputStream is = aryURI.openStream();
						Bitmap bm = BitmapFactory.decodeStream(is);
						if (bm == null) {
							imageView.setBackgroundColor(R.drawable.nopicture);
						}
						is.close();
						imageView.setImageBitmap(bm);
						proDialog.dismiss();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				break;

			default:
				break;
			}
		}
	};

	private void initView() {
		singleTextView = (TextView) findViewById(R.id.singletextview);
		singletext = (TextView) findViewById(R.id.singletext);
		imageView = (ImageView) findViewById(R.id.singleimageview);
		//button = (Button) findViewById(R.id.button);

		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("�������ĵȴ�...");
	}

	private void initData() {
		Bundle bundle = getIntent().getExtras();
		String type = bundle.getString("type");
		String id = bundle.getString("url");
		
		if (type.equals("��Ӱ")) {
			url = "https://api.douban.com/v2/movie/subject/" + id;
		}else if(type.equals("����")){
			url = "https://api.douban.com/v2/music/" + id;
		}else if(type.equals("�鼮"))
		{
			url = "https://api.douban.com/v2/book/" + id;
		}
		
		DebugUtil.error(url);
		
		imageUrl = bundle.getString("imageurl");

		detailedInfo = new DetailedInfoLoder();
	}

	class LoadData implements Runnable {

		@Override
		public void run() {
			int choice = 0;
			try {
				if (url.contains("movie")) {
					moiveList = detailedInfo.findMovieJsonTwo(url, imageUrl);
					choice = LOAD_MOVIE;
				} 
				mHandler.sendEmptyMessage(choice);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void addListener() {
		
	}
}
