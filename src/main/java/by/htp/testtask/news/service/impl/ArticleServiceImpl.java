package by.htp.testtask.news.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.testtask.news.controller.model.Article;
import by.htp.testtask.news.dao.ArticleDAO;
import by.htp.testtask.news.service.ArticleConvertModel;
import by.htp.testtask.news.service.ArticleService;
import by.htp.testtask.news.service.exception.ServiceException;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDAO articleDAO;
	
	@Override
	@Transactional
	public int create(Article article) throws ServiceException {
		by.htp.testtask.news.dao.model.Article articleData;
		
		try {
			articleData = ArticleConvertModel.fromArticle(article);
		} catch (ParseException e) {
			throw new ServiceException("Error format date");
		}
		return articleDAO.create(articleData);
	}

	@Override
	@Transactional
	public Article read(int id) {
		by.htp.testtask.news.dao.model.Article articleData = articleDAO.read(id);
		return ArticleConvertModel.fromActicleData(articleData);
	}

	@Override
	@Transactional
	public List<Article> readAll() {
		List<by.htp.testtask.news.dao.model.Article> listArticleData = articleDAO.readAll();
		List<Article> listArticle = new ArrayList<Article>(listArticleData.size());
		for (by.htp.testtask.news.dao.model.Article articleData : listArticleData) {
			Article article = ArticleConvertModel.fromActicleData(articleData);
			listArticle.add(article);
		}
		return listArticle;
	}

	@Override
	@Transactional
	public void update(Article article) throws ServiceException {
by.htp.testtask.news.dao.model.Article articleData;
		
		try {
			articleData = ArticleConvertModel.fromArticle(article);
		} catch (ParseException e) {
			throw new ServiceException("Error format date");
		}
		
		articleDAO.update(articleData);
	}

	@Override
	@Transactional
	public void delete(int id) {
		articleDAO.delete(id);
	}

	@Override
	@Transactional
	public void deleteList(String stringIDs) throws ServiceException {
		articleDAO.deleteList(stringIDs);
	}

}
