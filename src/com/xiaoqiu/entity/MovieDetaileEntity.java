package com.xiaoqiu.entity;

/**
 * 
 * @author tianqiujie 电影详细实体
 * 
 */

public class MovieDetaileEntity {
	private String title;// 电影名称
	private String author;// 导演
	private String writer;// 编剧
	private String imageUrl;// 图片位置
	private String summary;// 内容简介
	private String webSite;// 官网网址

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

}
