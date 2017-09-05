package by.htp.testtask.news.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.htp.testtask.news.controller.model.Article;


public class ArticleConvertModel {
	
	private static final String DATE_FORMAT = "MM/dd/yyyy";

	public static Article fromActicleData(by.htp.testtask.news.dao.model.Article articleData) {
		Article article = new Article();
		article.setId(articleData.getId());
		article.setTitle(articleData.getTitle());
		article.setBrief(articleData.getBrief());
		article.setContent(articleData.getContent());
		
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String date = format.format(articleData.getDate());
		article.setDate(date);
		
		return article;
	}

	public static by.htp.testtask.news.dao.model.Article fromArticle(Article article) throws ParseException {
		by.htp.testtask.news.dao.model.Article articleData = new by.htp.testtask.news.dao.model.Article();
		articleData.setId(article.getId());
		articleData.setTitle(article.getTitle());
		articleData.setBrief(article.getBrief());
		articleData.setContent(article.getContent());

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Date date = format.parse(article.getDate());
		articleData.setDate(date);

		return articleData;
	}
}
