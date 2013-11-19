package com.xiaoqiu.entity;

/**
 * 
 * @author tianqiujie
 * 电影概要实体
 *
 */
public class SingleEntity {
	private String authorName;//电影导演名称属性
	private String title;//电影名称属性
	private String firstUrl;//链接属性
	private String imageUrl;//图片链接属性
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getFirstUrl() {
		return firstUrl;
	}
	public void setFirstUrl(String firstUrl) {
		this.firstUrl = firstUrl;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getMovieName() {
		return title;
	}
	public void setMovieName(String movieName) {
		this.title = movieName;
	}

}
