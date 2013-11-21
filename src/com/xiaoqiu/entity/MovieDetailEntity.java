package com.xiaoqiu.entity;
/**
 * @author jiyuan
 * Movie Entity
 * 
 * */


public class MovieDetailEntity {
	
	private String title;			//名称
	private String subject_id;		//电影id
	private String rating_stars;	//星级
	private String countries;		//国家
	private String rating_average;	//评分
	private String genres;			//电影类型
	private String image_medium;	//中图片链接	
	private String casts;			//演员
	private String collect_count;	//
	private String summary;			//描述
	private String directors;		//导演
	private String comments_count;	//
	private String ratings_count;	//
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getRating_stars() {
		return rating_stars;
	}
	public void setRating_stars(String rating_stars) {
		this.rating_stars = rating_stars;
	}
	public String getCountries() {
		return countries;
	}
	public void setCountries(String countries) {
		this.countries = countries;
	}
	public String getRating_average() {
		return rating_average;
	}
	public void setRating_average(String rating_average) {
		this.rating_average = rating_average;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getImage_medium() {
		return image_medium;
	}
	public void setImage_medium(String image_medium) {
		this.image_medium = image_medium;
	}
	public String getCasts() {
		return casts;
	}
	public void setCasts(String casts) {
		this.casts = casts;
	}
	public String getCollect_count() {
		return collect_count;
	}
	public void setCollect_count(String collect_count) {
		this.collect_count = collect_count;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getComments_count() {
		return comments_count;
	}
	public void setComments_count(String comments_count) {
		this.comments_count = comments_count;
	}
	public String getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}
	
	
}
