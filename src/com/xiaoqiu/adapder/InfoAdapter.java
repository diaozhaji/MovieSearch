package com.xiaoqiu.adapder;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoqiu.activity.MainActivity;
import com.xiaoqiu.cache.ImageLoader;
import com.xiaoqiu.entity.SingleEntity;
import com.xiaoqiu.main.R;

public class InfoAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<SingleEntity> aList;
	private ImageLoader mImageLoader;
	private Context context;
	private String mText;

	private boolean mBusy = false;

	public InfoAdapter(Context context, ListView listView) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
		
		aList = ((MainActivity) context).aList;
		mText = "电影";
		mImageLoader = new ImageLoader(context);
	}

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public int getCount() {
		return aList.size();
	}

	public Object getItem(int position) {
		if (position >= getCount()) {
			return null;
		}
		return aList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.all_single_show, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.all_title);
			viewHolder.contentTextView = (TextView)convertView
					.findViewById(R.id.all_content);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.allimageview);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		SingleEntity movieBriefPojo = aList.get(position);
		
		String str = "";
		if (mText.equals("电影")) {
			str = "导演:";
		}
		viewHolder.mTextView.setText(movieBriefPojo.getMovieName());
		viewHolder.contentTextView.setText("导演：" + movieBriefPojo.getAuthorName());
		
		viewHolder.mImageView.setBackgroundResource(R.drawable.rc_item_bg);
		/*
		viewHolder.mTextView.setText(position + 1 + "." + movieBriefPojo.getMovieName() + "\n" + str
				+ movieBriefPojo.getAuthorName());
		 */
		
		
		String url = movieBriefPojo.getImageUrl();
		
		if (!mBusy) {
			mImageLoader.DisplayImage(url, viewHolder.mImageView, false);
		} else {
			mImageLoader.DisplayImage(url, viewHolder.mImageView, false);
		}
		
		return convertView;
	}

	static class ViewHolder {
		TextView mTextView;
		TextView contentTextView;
		ImageView mImageView;
	}

}