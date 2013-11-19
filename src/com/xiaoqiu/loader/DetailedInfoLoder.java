package com.xiaoqiu.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.xiaoqiu.entity.MovieDetaileEntity;

/**
 * 
 * @author tianqiujie 传入对应点击选项的 XML地址 及图片URL 解析对应XML 将数据存入相关电影的实体
 */

public class DetailedInfoLoder {
	/**
	 * 
	 * @param singleurl
	 *            包含详细信息的XML地址
	 * @param imageUrl
	 *            被点击项的图片
	 * @return 存入数据的泛型LIST
	 */
	@SuppressWarnings("unused")
	public List<MovieDetaileEntity> findMovieJsonTwo(String singleUrl,
			String imageUrl) throws IOException, ParserConfigurationException,
			SAXException {
		List<MovieDetaileEntity> result = new ArrayList<MovieDetaileEntity>();
		MovieDetaileEntity movieDetailedPojo = new MovieDetaileEntity();
		URL url = new URL(singleUrl);

		// 获取数据存入StringBuilder里面
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
				.readLine()) {
			stringBuilder.append(s);
		}

		JSONObject jsonObject = null;

		/*
		 * private String movieName;//电影名称 private String author;//导演 private
		 * String writer;//编剧 private String imageUrl;//图片位置 private String
		 * summary;//内容简介 private String webSite;//官网网址
		 */

		try {
			jsonObject = new JSONObject(stringBuilder.toString());
			// 电影名称
			movieDetailedPojo.setTitle(jsonObject.getString("title"));

			JSONArray directors = jsonObject.getJSONArray("directors");
			String director = "";
			if (directors != null) {
				for (int i = 0; i < directors.length(); i++) {
					director += ((JSONObject) directors.opt(i))
							.getString("name") + " ";
				}
			} else {
				director = "不详";
			}
			// 导演
			movieDetailedPojo.setAuthor(director);

			JSONArray writers = null;
			String writer = "";
			if (writers != null) {
				for (int i = 0; i < writers.length(); i++) {
					writer += writers.optString(i) + " ";
				}
			} else {
				writer = "不详";
			}
			// 编剧
			movieDetailedPojo.setWriter(writer);

			JSONObject images = jsonObject.getJSONObject("images");
			// 图片url
			movieDetailedPojo.setImageUrl(images.getString("medium"));

			// 简介
			movieDetailedPojo.setSummary(jsonObject.getString("summary"));

			// 官方网址
			// String website = jsonObject.getString("website");
			String website = null;
			if (website != null) {
				movieDetailedPojo.setWebSite(website);
			} else {
				movieDetailedPojo.setWebSite("不详");
			}

			result.add(movieDetailedPojo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
