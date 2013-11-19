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
 * @author tianqiujie �����Ӧ���ѡ��� XML��ַ ��ͼƬURL ������ӦXML �����ݴ�����ص�Ӱ��ʵ��
 */

public class DetailedInfoLoder {
	/**
	 * 
	 * @param singleurl
	 *            ������ϸ��Ϣ��XML��ַ
	 * @param imageUrl
	 *            ��������ͼƬ
	 * @return �������ݵķ���LIST
	 */
	@SuppressWarnings("unused")
	public List<MovieDetaileEntity> findMovieJsonTwo(String singleUrl,
			String imageUrl) throws IOException, ParserConfigurationException,
			SAXException {
		List<MovieDetaileEntity> result = new ArrayList<MovieDetaileEntity>();
		MovieDetaileEntity movieDetailedPojo = new MovieDetaileEntity();
		URL url = new URL(singleUrl);

		// ��ȡ���ݴ���StringBuilder����
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
				.readLine()) {
			stringBuilder.append(s);
		}

		JSONObject jsonObject = null;

		/*
		 * private String movieName;//��Ӱ���� private String author;//���� private
		 * String writer;//��� private String imageUrl;//ͼƬλ�� private String
		 * summary;//���ݼ�� private String webSite;//������ַ
		 */

		try {
			jsonObject = new JSONObject(stringBuilder.toString());
			// ��Ӱ����
			movieDetailedPojo.setTitle(jsonObject.getString("title"));

			JSONArray directors = jsonObject.getJSONArray("directors");
			String director = "";
			if (directors != null) {
				for (int i = 0; i < directors.length(); i++) {
					director += ((JSONObject) directors.opt(i))
							.getString("name") + " ";
				}
			} else {
				director = "����";
			}
			// ����
			movieDetailedPojo.setAuthor(director);

			JSONArray writers = null;
			String writer = "";
			if (writers != null) {
				for (int i = 0; i < writers.length(); i++) {
					writer += writers.optString(i) + " ";
				}
			} else {
				writer = "����";
			}
			// ���
			movieDetailedPojo.setWriter(writer);

			JSONObject images = jsonObject.getJSONObject("images");
			// ͼƬurl
			movieDetailedPojo.setImageUrl(images.getString("medium"));

			// ���
			movieDetailedPojo.setSummary(jsonObject.getString("summary"));

			// �ٷ���ַ
			// String website = jsonObject.getString("website");
			String website = null;
			if (website != null) {
				movieDetailedPojo.setWebSite(website);
			} else {
				movieDetailedPojo.setWebSite("����");
			}

			result.add(movieDetailedPojo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
