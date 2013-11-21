package com.xiaoqiu.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.xiaoqiu.entity.MovieDetailEntity;
import com.xiaoqiu.entity.MovieDetaileEntityOld;

import android.util.Log;

public class MovieDetailInfoLoader {
	private static final String TAG = "MovieDetailInfoLoader";
	
	public MovieDetailInfoLoader(){
		Log.d(TAG, "constractor");
	}
	
	public MovieDetailEntity parserMovieJson(String singleUrl)
				throws IOException, ParserConfigurationException,SAXException {
		List<MovieDetailEntity> result = new ArrayList<MovieDetailEntity>();
		MovieDetailEntity movieDetailedPojo = new MovieDetailEntity();
		
		URL url = new URL(singleUrl);
		Log.d(TAG, singleUrl);
		
		// 获取数据存入StringBuilder里面
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
				.readLine()) {
			stringBuilder.append(s);
		}		

		JSONObject jsonObject = null;
		
		try {
			jsonObject = new JSONObject(stringBuilder.toString());
			Log.d(TAG, stringBuilder.toString());
			// 电影名称
			movieDetailedPojo.setTitle(jsonObject.getString("title"));
			Log.d(TAG, jsonObject.getString("title"));
			
			// 图片url
			movieDetailedPojo.setImage_medium(jsonObject.getString("image_medium"));
			Log.d(TAG, jsonObject.getString("image_medium"));
			// 简介
			movieDetailedPojo.setSummary(jsonObject.getString("summary"));
			Log.d(TAG, jsonObject.getString("summary"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieDetailedPojo;


		
	}

}
