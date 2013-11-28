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
		Log.d(TAG, "constractor()  do thing");
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
			// 图片url
			movieDetailedPojo.setImage_medium(jsonObject.getString("image_medium"));
			// 简介
			movieDetailedPojo.setSummary(jsonObject.getString("summary"));			
			// 评分
			movieDetailedPojo.setRating_average(jsonObject.getString("rating_average"));
			Log.d(TAG, movieDetailedPojo.getRating_average());
			// 导演
			movieDetailedPojo.setDirectors(jsonObject.getString("directors"));
			Log.d(TAG, movieDetailedPojo.getDirectors());
			//年份
			movieDetailedPojo.setYear(jsonObject.getString("year"));
			Log.d(TAG, movieDetailedPojo.getYear());
			//xxx人看过---人气
			movieDetailedPojo.setCollect_count(jsonObject.getString("collect_count"));
			Log.d(TAG, movieDetailedPojo.getCollect_count());
			//地区
			movieDetailedPojo.setCountries(jsonObject.getString("countries"));
			Log.d(TAG, "countriey:"+movieDetailedPojo.getCountries());
			//类型
			movieDetailedPojo.setGenres(jsonObject.getString("genres"));
			Log.d(TAG, "类型:"+movieDetailedPojo.getGenres());
			//主演
			movieDetailedPojo.setCasts(jsonObject.getString("casts"));
			Log.d(TAG, "主演:"+movieDetailedPojo.getCasts());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieDetailedPojo;


		
	}

}
